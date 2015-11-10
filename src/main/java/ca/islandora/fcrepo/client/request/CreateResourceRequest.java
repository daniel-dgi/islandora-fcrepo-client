package ca.islandora.fcrepo.client.request;

import ca.islandora.fcrepo.client.response.IFcrepoResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import org.fcrepo.camel.FcrepoOperationFailedException;

import java.io.InputStream;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class CreateResourceRequest extends AFcrepoRequest {

    protected InputStream body;
    protected String contentType;
    protected String checksum;
    protected String contentDisposition;
    protected String contentLocation;
    protected String slug;

    private static final Logger LOGGER = getLogger(CreateResourceRequest.class);

    public CreateResourceRequest(final CloseableHttpClient httpClient, final String uri) {
        super(httpClient, uri);
    }

    public CreateResourceRequest setBody(InputStream value) {
         body = value;
         return this;
    }

    public CreateResourceRequest setContentType(String value) {
         contentType = value;
         return this;
    }

    public CreateResourceRequest setChecksum(String value) {
         checksum = value;
         return this;
    }

    public CreateResourceRequest setContentDisposition(String value) {
         contentDisposition = value;
         return this;
    }

    public CreateResourceRequest setContentLocation(String value) {
         contentLocation = value;
         return this;
    }

    public CreateResourceRequest setSlug(String value) {
         slug = value;
         return this;
    }

    @Override
    public IFcrepoResponse execute() throws FcrepoOperationFailedException {
        try {
            URIBuilder uriBuilder = new URIBuilder(uri);

            if (checksum != null) {
                uriBuilder.setParameter("checksum", "urn:sha1:" + checksum);
            }

            final HttpEntityEnclosingRequestBase request = (HttpEntityEnclosingRequestBase)new HttpPost(uriBuilder.build());

            if (contentType != null) {
                request.setHeader("Content-Type", contentType);
            }

            if (contentDisposition != null) {
                request.setHeader("Content-Disposition", contentDisposition);
            }

            if (contentLocation != null) {
                request.setHeader("Content-Location", contentLocation);
            }

            if (slug != null) {
                request.setHeader("Slug", slug);
            }

            if (body != null) {
                request.setEntity(new InputStreamEntity(body));
            }

            return executeRequest(request);
        } catch (URISyntaxException ex) {
            LOGGER.error(ex.getMessage());
        }
        return null;
    }
}
