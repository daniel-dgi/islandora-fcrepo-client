package ca.islandora.fcrepo.client.response;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

public class FcrepoResponse implements IFcrepoResponse {

    private HttpRequestBase request;
    private HttpResponse response;

    public FcrepoResponse(HttpRequestBase request, HttpResponse response) {
        this.request = request;
        this.response = response;
    }

    public String getRequestUri() {
        return request.getURI().toString();
    }

    public int getStatusCode() {
        return response.getStatusLine().getStatusCode();
    }

    public String getReasonPhrase() {
        return response.getStatusLine().getReasonPhrase();
    }

    public InputStream getBody() {
        final HttpEntity entity = response.getEntity();

        if (entity == null) {
            return null;
        }

        try {
            return entity.getContent();
        } catch (IOException ex) {
            return null;
        }
    }

    public String getLocation() {
        if (response.containsHeader("Location")) {
            return response.getFirstHeader("Location").getValue();
        }
        return null;
    }
}

