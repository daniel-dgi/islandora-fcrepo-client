/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.islandora.fcrepo.client;

import ca.islandora.fcrepo.client.request.GetResourceRequest;
import ca.islandora.fcrepo.client.request.CreateResourceRequest;
import ca.islandora.fcrepo.client.request.SaveResourceRequest;
import ca.islandora.fcrepo.client.request.ModifyResourceRequest;
import ca.islandora.fcrepo.client.request.DeleteResourceRequest;
import ca.islandora.fcrepo.client.request.IFcrepoRequest;
import ca.islandora.fcrepo.client.response.IFcrepoResponse;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.fcrepo.camel.FcrepoOperationFailedException;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class FcrepoClient implements IFcrepoClient {

    protected String baseUri;
    protected CloseableHttpClient httpClient;

    private static final Logger LOGGER = getLogger(FcrepoClient.class);

    public FcrepoClient(final String baseUri) {
        this.baseUri = baseUri;
        httpClient = HttpClients.createDefault();
    }

    @Override
    public GetResourceRequest getResource(final String uri) {
        return new GetResourceRequest(httpClient, uri);
    }

    @Override
    public CreateResourceRequest createResource(final String uri) {
        return new CreateResourceRequest(httpClient, uri);
    }

    @Override
    public SaveResourceRequest saveResource(final String uri) {
        return new SaveResourceRequest(httpClient, uri);
    }

    @Override
    public ModifyResourceRequest modifyResource(final String uri, final String sparql) {
        return new ModifyResourceRequest(httpClient, uri, sparql);
    }

    @Override
    public DeleteResourceRequest deleteResource(final String uri) {
        return new DeleteResourceRequest(httpClient, uri);
    }

    @Override
    public IFcrepoResponse createTransaction() throws FcrepoOperationFailedException {
        try {
            URIBuilder uriBuilder = new URIBuilder(baseUri);
            uriBuilder.setPath(uriBuilder.getPath() + "/fcr:tx");
            final String uri = uriBuilder.build().toString();
            final IFcrepoRequest request = new CreateResourceRequest(httpClient, uri);
            return request.execute();
        } catch (URISyntaxException ex) {
            throw new FcrepoOperationFailedException(null, -1, ex.getMessage());
        }
    }

    @Override
    public IFcrepoResponse extendTransaction(final String uri) throws FcrepoOperationFailedException {
        try {
            URIBuilder uriBuilder = new URIBuilder(uri);
            uriBuilder.setPath(uriBuilder.getPath() + "/fcr:tx");
            final String fullUri = uriBuilder.build().toString();
            final IFcrepoRequest request = new CreateResourceRequest(httpClient, fullUri);
            return request.execute();
        } catch (URISyntaxException ex) {
            throw new FcrepoOperationFailedException(null, -1, ex.getMessage());
        }
    }

    @Override
    public IFcrepoResponse commitTransaction(final String uri) throws FcrepoOperationFailedException {
        try {
            URIBuilder uriBuilder = new URIBuilder(baseUri);
            uriBuilder.setPath(uriBuilder.getPath() + "/fcr:tx/fcr:commit");
            final String fullUri = uriBuilder.build().toString();
            final IFcrepoRequest request = new CreateResourceRequest(httpClient, fullUri);
            return request.execute();
        } catch (URISyntaxException ex) {
            throw new FcrepoOperationFailedException(null, -1, ex.getMessage());
        }
    }

    @Override
    public IFcrepoResponse rollbackTransaction(final String uri) throws FcrepoOperationFailedException {
        try {
            URIBuilder uriBuilder = new URIBuilder(baseUri);
            uriBuilder.setPath(uriBuilder.getPath() + "/fcr:tx/fcr:rollback");
            final String fullUri = uriBuilder.build().toString();
            final IFcrepoRequest request = new CreateResourceRequest(httpClient, fullUri);
            return request.execute();
        } catch (URISyntaxException ex) {
            throw new FcrepoOperationFailedException(null, -1, ex.getMessage());
        }
    }
}
