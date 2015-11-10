package ca.islandora.fcrepo.client.response;

import java.io.InputStream;

public interface IFcrepoResponse {
    public String getRequestUri();
    public int getStatusCode();
    public String getReasonPhrase();
    public InputStream getBody();
    public String getLocation();
}
