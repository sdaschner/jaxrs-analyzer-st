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

/**
 * These sources are solely used for test purposes and not meant for deployment.
 */
public class Model03 {

    private String name;
    private Model03 child;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Model03 getChild() {
        return child;
    }

    public void setChild(final Model03 child) {
        this.child = child;
    }

}
