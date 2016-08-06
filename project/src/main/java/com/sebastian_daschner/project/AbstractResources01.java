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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * These sources are solely used for test purposes and not meant for deployment.
 */
public abstract class AbstractResources01 {

    @PUT
    public Response put() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("{id}")
    public Response getId(@PathParam("id") final String id) {
        return Response.status(Response.Status.NOT_IMPLEMENTED).header("X-Info", id + " is not implemented").build();
    }

    @GET
    @Path("02")
    @Produces(MediaType.APPLICATION_JSON)
    public Object get02() {
        return new Object();
    }

}
