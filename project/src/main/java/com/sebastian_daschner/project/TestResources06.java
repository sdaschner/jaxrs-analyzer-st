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

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * These sources are solely used for test purposes and not meant for deployment.
 */
@Path("test06")
public class TestResources06 {

    /**
     * Query param to filter.
     */
    @QueryParam("query")
    String param;

    /**
     * Gets a test.
     *
     * @return
     */
    @GET
    public JsonObject get() {
        return Json.createObjectBuilder().add("key", "value").add("duke", 42).build();
    }

    /**
     * Creates a new test
     *
     * @param jsonObject The body with parameters
     * @return
     */
    @POST
    public Response post(JsonObject jsonObject) {
        if (jsonObject.getString("").equals(""))
            return Response.accepted(Json.createObjectBuilder().add("key", "value").build()).build();
        if ("a".equals("b"))
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        return Response.ok(Json.createArrayBuilder().add("duke").add(42).build()).build();
    }

    /**
     * Returns a sub test.
     *
     * @param id The sub identifier
     * @return
     */
    @GET
    @Path("{id}")
    public Response get01(@PathParam("id") long id) {
        return Response.ok(Json.createObjectBuilder().add("key", "value").add("duke", "42").add("hello", "world")).build();
    }

}
