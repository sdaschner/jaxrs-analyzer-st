/*
 * Copyright (C) 2015 Sebastian Daschner, sebastian-daschner.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sebastian_daschner.project;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * These sources are solely used for test purposes and not meant for deployment.
 */
@Stateless
@Path("test03")
public class TestResources03 extends AbstractResources01 implements Resources01 {

    @Context
    ResourceContext rc;

    @Inject
    Manager01<Integer> manager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<String> get() {
        final ArrayList<String> strings = new ArrayList<>();
        strings.add("hi");
        strings.add("hello");
        return strings;
    }

    @Override
    public Response getId(final String id) {
        return Response.ok().header("X-Info", manager.getInstance(String.class, id.length()) + " is complex").build();
    }

    @Override
    public String get01() {
        return "status";
    }

    @Override
    public String get02() {
        return "hello";
    }

    /**
     * A sub resource. This comment should be ignored.
     */
    @Path("03")
    public Resources02 sub03() {
        return createSomeSubResource();
    }

    @Path("04")
    public Resources02 sub04() {
        return rc.initResource(new TestResources04("foobar"));
    }

    @Path("05")
    public Resources02 sub05() {
        // just for testing, this would fail due to missing default constructor
        return rc.getResource(TestResources04.class);
    }

    private Resources02 createSomeSubResource() {
        return new TestResources04("foobar");
    }

}
