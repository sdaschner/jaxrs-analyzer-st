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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;

/**
 * These sources are solely used for test purposes and not meant for deployment.
 */
@Path("test07")
public class TestResources07 {

    @Context
    ResourceContext rc;

    @GET
    public Model03 get() {
        final Model03 model03 = new Model03();
        model03.setChild(new Model03());
        model03.setName("foo");
        return model03;
    }

    /**
     * This should be ignored.
     */
    @Path("01")
    public SubResources01 sub01() {
        return rc.getResource(SubResources01.class);
    }

    /**
     * This should be ignored.
     */
    @Path("02")
    public SubResources02 sub02() {
        return rc.getResource(SubResources02.class);
    }

}
