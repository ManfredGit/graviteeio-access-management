/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.am.gateway.core.http;

import io.gravitee.common.spring.factory.SpringFactoriesLoader;
import org.springframework.beans.factory.FactoryBean;

import java.util.Collection;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class HttpServerFactory extends SpringFactoriesLoader<HttpServer> implements FactoryBean<HttpServer> {

    @Override
    public HttpServer getObject() throws Exception {
        Collection<? extends HttpServer> httpServers = getFactoriesInstances();

        if (httpServers.isEmpty()) {
            logger.error("No HTTP Server can be found in classpath.");
            throw new IllegalStateException("No HTTP Server can be found in classpath.");
        } else if(httpServers.size() < 1) {
            logger.error("Multiple implementations of HTTP Server found.");
            throw new IllegalStateException("Multiple implementations of HTTP Server found.");
        }

        return httpServers.iterator().next();
    }

    @Override
    public Class<HttpServer> getObjectType() {
        return HttpServer.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
