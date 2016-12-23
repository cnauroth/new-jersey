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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.core.header.InBoundHeaders;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseWriter;
import com.sun.jersey.spi.container.WebApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a custom Jersey container that hosts the New Jersey web application.
 * It supports manually dispatching an inbound HTTP request to the corresponding
 * JAX-RS annotated handler without requiring a separate HTTP call.
 */
public final class NewJerseyContainer {

    private static final Logger LOG =
        LoggerFactory.getLogger(NewJerseyContainer.class);

    private final WebApplication webapp;

    /**
     * Creates a new NewJerseyContainer.
     *
     * @param webapp web application
     */
    public NewJerseyContainer(WebApplication webapp) {
        LOG.info("Creating " + getClass().getSimpleName());
        this.webapp = webapp;
    }

    /**
     * Dispatch a method call through the Jersey container's web application.
     * To keep the example simple, this does not allow the caller to specify any
     * headers or request body, and it is assumed that the called method writes
     * a response body consisting of just a string easily stored in a buffer.
     * The web application root is assumed to be "/".
     *
     * @param method HTTP method
     * @param method URI path
     * @return response body
     * @throws IOException if there is an I/O error
     * @throws URISyntaxException if the URI path is invalid
     * @throws UnsupportedEncodingException if the platform can't handle UTF-8
     */
    public String dispatch(String method, String path)
            throws IOException, URISyntaxException,
            UnsupportedEncodingException {
        InBoundHeaders jerseyHeaders = new InBoundHeaders();
        InputStream reqIn = null;
        ContainerRequest jerseyReq = new ContainerRequest(webapp, method,
                new URI("/"), new URI(path), jerseyHeaders, reqIn);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        this.webapp.handleRequest(jerseyReq, new ContainerResponseWriter() {

            /**
             * This is a callback triggered by Jersey as soon as dispatch has
             * completed to the point of knowing what kind of response to
             * return.  The response body is not necessarily ready yet.  Jersey
             * will use the stream returned from this method for writing the
             * response body later when it becomes available.
             *
             * @param contentLength length of response
             * @param jerseyResp HTTP response returned by Jersey
             * @return OutputStream for Jersey to use for writing the response
             *         body
             */
             @Override
             public OutputStream writeStatusAndHeaders(long contentLength,
                     ContainerResponse jerseyResp) {
                 return out;
             }

            /**
             * This is a callback triggered by Jersey after it has completed
             * writing the response body to the stream.
             *
             * @throws IOException if there is an I/O error
             */
             @Override
             public void finish() throws IOException {
                 out.close();
             }
        });
        String resp = new String(out.toByteArray(), "UTF-8");
        LOG.info("dispatch, method={}, path=\"{}\", resp = \"{}\"",
                method, path, resp);
        return resp;
    }
} 
