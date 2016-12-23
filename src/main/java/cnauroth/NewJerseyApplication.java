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

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.core.Application;

/**
 * New Jersey Application.  This is used to register the targeted JAX-RS
 * annotated classes that become candidates for dispatch.
 */
public final class NewJerseyApplication extends Application {

  /**
   * Use this method to return a reference to every JAX-RS annotated class that
   * you want to use as a target for dispatch.
   *
   * @return set of JAX-RS annotated classes
   */
  @Override
  public Set<Class<?>> getClasses() {
    return Collections.<Class<?>>singleton(NewJerseyHandler.class);
  }

  /**
   * If appropriate, you can instead return instances of JAX-RS annotated
   * classes here, and Jersey will reuse that instance for every request handled
   * instead of creating a new instance for each request.  Instances returned
   * from here likely need to enforce thread safety in their implementation, so
   * that concurrent request execution through the same instances doesn't cause
   * harm.
   *
   * @return instances of JAX-RS annotated classes
   */
  @Override
  public Set<Object> getSingletons() {
    return Collections.emptySet();
  }
}
