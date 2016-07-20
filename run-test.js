#!/usr/bin/env jjs

load("mustache.js");

var Paths = java.nio.file.Paths,
    Files = java.nio.file.Files,
    testPassed = true,
    index = null,
    config = JSON.parse(readFully("config.json"));

config.scenarios.map(function (scenario, i) {
    try {

        index = (i + 1 < 10) ? '0' + (i + 1) : i + 1;
        var mavenOutputFile = "project/target/jaxrs-analyzer/" + getBackendFileName(scenario.backend),
            actualFile = "scenarios/${index}_actual",
            expectedFile = "scenarios/${index}_expected";

        scenario.version = config.version;

        generatePom(scenario);

        executeMaven();

        copyMavenOutput(mavenOutputFile);

        var actual = readContent(expectedFile),
            expected = readContent(actualFile);

        assertOutput(expected, actual);

        var command = generateCli(scenario);

        executeCli(command);

        actual = readContent(expectedFile);

        assertOutput(expected, actual);

        cleanUp(actualFile);

    } catch (e) {
        print(e);
        testPassed = false;
    }
});

print("\nTests ${testPassed ? '': 'NOT '}passed");

exit(testPassed ? 0 : 1);

//
// Functions
//

function readContent(file) {
    return new java.lang.String(Files.readAllBytes(Paths.get(file)));
}

function generatePom(scenario) {
    var pomMustache = readContent("project/pom.xml.mustache"),
        pom = Mustache.render(pomMustache, scenario);

    Files.write(Paths.get("project/pom.xml"), pom.getBytes(), java.nio.file.StandardOpenOption.TRUNCATE_EXISTING, java.nio.file.StandardOpenOption.CREATE);
}

function executeMaven() {
    print("running Maven for ${index}");

    var mavenOutput = $EXEC("mvn -f project/ clean install");

    if (mavenOutput.contains("WARNING") || mavenOutput.contains("ERROR")) {
        print(mavenOutput);
        throw "warning or error in Maven output";
    }
}

function copyMavenOutput(mavenOutputFile) {
    var actualFile = "scenarios/${index}_actual";

    Files.copy(Paths.get(mavenOutputFile), Paths.get(actualFile), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
}

function assertOutput(expected, actual) {
    if (expected !== actual)
        throw "contents of expected and actual files differ for scenario ${index}";
}

function generateCli(scenario) {
    var commandMustache = new java.lang.String(Files.readAllBytes(Paths.get("project/command.mustache")));
    return Mustache.render(commandMustache, scenario);
}

function executeCli(command) {
    print("running standalone for ${index}");

    var cliOutput = $EXEC(command);

    if (cliOutput) {
        print(cliOutput);
        throw "non-empty CLI output";
    }
}

function getBackendFileName(backend) {
    switch (backend) {
        case "swagger":
            return "swagger.json";
        case "plaintext":
            return "rest-resources.txt";
        case "asciidoc":
            return "rest-resources.adoc";
        default:
            throw "no known backend: " + backend;
    }
}

function cleanUp(actualFile) {
    Files.delete(Paths.get(actualFile));
}
