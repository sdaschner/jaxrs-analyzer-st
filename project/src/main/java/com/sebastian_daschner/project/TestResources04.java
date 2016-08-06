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

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * These sources are solely used for test purposes and not meant for deployment.
 */
@Path("04")
public class TestResources04 implements Resources02 {

    private final String name;

    @QueryParam("query")
    private String query;

    public TestResources04(final String name) {
        this.name = name;
    }

    @POST
    public Response post(final String entity) {
        System.out.println("posted new: " + entity + " q: " + query);
        return Response.accepted().header("X-Info", "Added " + entity).build();
    }

    @GET
    @Path("{id}")
    public String getId(@PathParam("id") final String id) {
        return this.name + id;
    }

}
