/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cnauroth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * A sample JAX-RS annotated handler that we'll use as the target for dispatched
 * methods.
 */
@Path("/")
public final class NewJerseyHandler {

    /**
     * Prints a message containing "foo".
     *
     * @return message containing "foo"
     */
    @Path("/foo")
    @GET
    public String foo() {
        return "You called foo!";
    }

    /**
     * Prints a message containing "bar".
     *
     * @return message containing "bar"
     */
    @Path("/bar")
    @GET
    public String bar() {
        return "You called bar!";
    }
}
