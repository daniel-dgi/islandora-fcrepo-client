package ca.islandora.fcrepo.client.request;

import ca.islandora.fcrepo.client.response.IFcrepoResponse;

import java.net.URISyntaxException;

import java.io.InputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import org.fcrepo.camel.FcrepoOperationFailedException;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class SaveResourceRequest extends AFcrepoRequest {

    protected InputStream body;
    protected String contentType;
    protected String checksum;
    protected String contentLocation;
    protected String ifMatch;
    protected String ifUnmodifiedSince;

    private static final Logger LOGGER = getLogger(SaveResourceRequest.class);

    public SaveResourceRequest(final CloseableHttpClient httpClient, final String uri) {
        super(httpClient, uri);
    }

    public SaveResourceRequest setBody(InputStream value) {
         body = value;
         return this;
    }

    public SaveResourceRequest setContentType(String value) {
         contentType = value;
         return this;
    }

    public SaveResourceRequest setChecksum(String value) {
         checksum = value;
         return this;
    }

    public SaveResourceRequest setContentLocation(String value) {
         contentLocation = value;
         return this;
    }

    public SaveResourceRequest setIfMatch(String value) {
         ifMatch = value;
         return this;
    }

    public SaveResourceRequest setIfUnmodifiedSince(String value) {
         ifUnmodifiedSince = value;
         return this;
    }

    @Override
    public IFcrepoResponse execute() throws FcrepoOperationFailedException {
        try {
            URIBuilder uriBuilder = new URIBuilder(uri);

            if (checksum != null) {
                uriBuilder.setParameter("checksum", "urn:sha1:" + checksum);
            }

            final HttpEntityEnclosingRequestBase request = (HttpEntityEnclosingRequestBase)new HttpPut(uriBuilder.build());

            if (contentType != null) {
                request.setHeader("Content-Type", contentType);
            }

            if (contentLocation != null) {
                request.setHeader("Content-Location", contentLocation);
            }

            if (ifMatch != null) {
                request.setHeader("If-Match", ifMatch);
            }

            if (ifUnmodifiedSince != null) {
                request.setHeader("If-Unmodified-Since", ifUnmodifiedSince);
            }

            if (body != null) {
                request.setEntity(new InputStreamEntity(body));
            }

            return executeRequest(request);
        } catch (URISyntaxException ex) {
            throw new FcrepoOperationFailedException(null, -1, ex.getMessage());
        }
    }
}
