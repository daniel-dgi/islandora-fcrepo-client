package ca.islandora.fcrepo.client.request;

import ca.islandora.fcrepo.client.response.IFcrepoResponse;

import org.fcrepo.camel.FcrepoOperationFailedException;

public interface IFcrepoRequest {
    public IFcrepoResponse execute() throws FcrepoOperationFailedException;
}
