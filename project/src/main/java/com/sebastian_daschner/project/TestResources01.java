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
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

/**
 * These sources are solely used for test purposes and not meant for deployment.
 */
@Path("/test01")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestResources01 {

    @Inject
    private Manager02 testStore;

    @GET
    public List<? extends Model01> get() {
        return this.testStore.getModels();
    }

    @POST
    public Response post(String string) {
        final Model01 managedModel = this.testStore.getModel(string);

        final URI uri = URI.create("/test/" + managedModel.getId());

        return Response.created(uri).build();
    }

    @PUT
    public Response put(final Model01 model) {
        this.testStore.addModel(model);

        return Response.accepted().build();
    }

    @GET
    @Path("{id}")
    public Model01 getId(@PathParam("id") final String id) {
        synchronized (this) {
            return this.testStore.getModel(id);
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteId(@PathParam("id") final String id) {
        Logger.getLogger("").info("deleted " + id);
    }

    @GET
    @Path("01")
    @Produces(MediaType.TEXT_HTML)
    public Response get01() {
        return Response.ok("hi", MediaType.TEXT_PLAIN_TYPE).build();
    }

    public <T extends Comparable<? super T>> T foobar() {
        return null;
    }

}
