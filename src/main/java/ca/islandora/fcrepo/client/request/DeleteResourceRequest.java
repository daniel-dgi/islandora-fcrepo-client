package ca.islandora.fcrepo.client.request;

import ca.islandora.fcrepo.client.response.IFcrepoResponse;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;

import org.fcrepo.camel.FcrepoOperationFailedException;

public class DeleteResourceRequest extends AFcrepoRequest {

    public DeleteResourceRequest(final CloseableHttpClient httpClient, final String uri) {
        super(httpClient, uri);
    }

    @Override
    public IFcrepoResponse execute() throws FcrepoOperationFailedException {
        final HttpRequestBase request = new HttpDelete(uri);
        return executeRequest(request);
    }
}

