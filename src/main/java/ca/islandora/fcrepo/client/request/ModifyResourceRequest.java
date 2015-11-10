package ca.islandora.fcrepo.client.request;

import ca.islandora.fcrepo.client.response.IFcrepoResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.HttpResponse;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import org.fcrepo.camel.FcrepoOperationFailedException;

import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ModifyResourceRequest extends AFcrepoRequest {

    protected InputStream body;
    protected String ifMatch;
    protected String ifUnmodifiedSince;
    protected String contentLocation;

    public ModifyResourceRequest(final CloseableHttpClient httpClient, final String uri, final String sparql) {
        super(httpClient, uri);
        this.body = IOUtils.toInputStream(sparql, StandardCharsets.UTF_8);
    }

    public ModifyResourceRequest setIfMatch(String value) {
         ifMatch = value;
         return this;
    }

    public ModifyResourceRequest setIfUnmodifiedSince(String value) {
         ifUnmodifiedSince = value;
         return this;
    }

    public ModifyResourceRequest setContentLocation(String value) {
         contentLocation = value;
         return this;
    }

    @Override
    public IFcrepoResponse execute() throws FcrepoOperationFailedException {
        final HttpEntityEnclosingRequestBase request = (HttpEntityEnclosingRequestBase)new HttpPatch(uri);

        if (ifMatch != null) {
            request.setHeader("If-Match", ifMatch);
        }

        if (ifUnmodifiedSince != null) {
            request.setHeader("If-Unmodified-Since", ifUnmodifiedSince);
        }

        if (contentLocation != null) {
            request.setHeader("Content-Location", contentLocation);
        }

        if (body != null) {
            request.setEntity(new InputStreamEntity(body));
        }

        return executeRequest(request);
    }
}
