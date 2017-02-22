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
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * These sources are solely used for test purposes and not meant for deployment.
 */
@Path("/test02")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestResources02 {

    @Inject
    private Manager02 manager;

    /**
     * Returns the model result.
     *
     * @param id The path param
     */
    @GET
    @Path("{id}")
    public ModelResult getId(@PathParam("id") final String id) {
        synchronized (this) {
            return new ModelResult(manager.getModel(id));
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteId(@PathParam("id") final Map<String, List<String>> id) {
        try {
            this.manager.delete("id");
            return Response.noContent().build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).header("X-Message", "The entity with identifier " + id + " was not found.").build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("{id}/01")
    public Response deleteId01(@PathParam("id") final String id, @QueryParam("query") final int query) {
        try {
            this.manager.delete(id);
            return Response.noContent().build();
        } finally {
            Logger.getLogger("").info("deleted");
        }
    }

    public static class ModelResult extends Model02<Model01> {
        public ModelResult(Model01 data) {
            super(data);
        }
    }

}
