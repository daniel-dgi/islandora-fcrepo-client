package ca.islandora.fcrepo.client.request;

import ca.islandora.fcrepo.client.response.IFcrepoResponse;
import ca.islandora.fcrepo.client.response.FcrepoResponse;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;

import org.fcrepo.camel.FcrepoOperationFailedException;

public abstract class AFcrepoRequest implements IFcrepoRequest {

    protected CloseableHttpClient httpClient;
    protected String uri;

    public AFcrepoRequest(CloseableHttpClient httpClient, String uri) {
        this.httpClient = httpClient;
        this.uri = uri;
    }

    protected IFcrepoResponse executeRequest(HttpRequestBase request) throws FcrepoOperationFailedException {
        try {
            IFcrepoResponse response = new FcrepoResponse(request, httpClient.execute(request));
            int status = response.getStatusCode();
            if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_BAD_REQUEST) {
                return response;
            } else {
                throw new FcrepoOperationFailedException(request.getURI(), status,
                        response.getReasonPhrase());
            }
        } catch (IOException ex) {
            throw new FcrepoOperationFailedException(request.getURI(), -1, ex.getMessage());
        }
    }

}

