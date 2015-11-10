package ca.islandora.fcrepo.client.request;

import ca.islandora.fcrepo.client.response.IFcrepoResponse;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;

import org.fcrepo.camel.FcrepoOperationFailedException;

import java.io.InputStream;
import java.io.IOException;

public class GetResourceRequest extends AFcrepoRequest {

    protected String range;
    protected String accept;
    protected String ifNoneMatch;
    protected String ifModifiedSince;
    protected String prefer;

    public GetResourceRequest(final CloseableHttpClient httpClient, final String uri) {
        super(httpClient, uri);
    }

    public GetResourceRequest setRange(String value) {
         this.range = value;
         return this;
    }

    public GetResourceRequest setAccept(String value) {
         this.accept = value;
         return this;
    }

    public GetResourceRequest setIfNoneMatch(String value) {
         this.ifNoneMatch = value;
         return this;
    }

    public GetResourceRequest setIfModifiedSince(String value) {
         this.ifModifiedSince = value;
         return this;
    }

    public GetResourceRequest setPrefer(String value) {
         this.prefer = value;
         return this;
    }

    @Override
    public IFcrepoResponse execute() throws FcrepoOperationFailedException {
        final HttpRequestBase request = new HttpGet(uri);

        if (accept != null) {
            request.setHeader("Accept", accept);
        }

        if (range != null) {
            request.setHeader("Range", range);
        }

        if (prefer != null) {
            request.setHeader("Prefer", prefer);
        }

        if (ifNoneMatch != null) {
            request.setHeader("If-None-Match", ifNoneMatch);
        }

        if (ifModifiedSince != null) {
            request.setHeader("If-Modified-Since", ifModifiedSince);
        }

        return executeRequest(request);
    }
}


