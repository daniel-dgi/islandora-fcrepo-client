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
import ca.islandora.fcrepo.client.response.IFcrepoResponse;

import org.fcrepo.camel.FcrepoOperationFailedException;

public interface IFcrepoClient {

    public GetResourceRequest getResource(final String uri);
    public CreateResourceRequest createResource(final String uri);
    public SaveResourceRequest saveResource(final String uri);
    public ModifyResourceRequest modifyResource(final String uri, final String sparql);
    public DeleteResourceRequest deleteResource(final String uri);
    public IFcrepoResponse createTransaction() throws FcrepoOperationFailedException;
    public IFcrepoResponse extendTransaction(final String uri) throws FcrepoOperationFailedException;
    public IFcrepoResponse commitTransaction(final String uri) throws FcrepoOperationFailedException;
    public IFcrepoResponse rollbackTransaction(final String uri) throws FcrepoOperationFailedException;

}
