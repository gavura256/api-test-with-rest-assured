package org.gavura.service;

import io.restassured.response.Response;
import org.gavura.uritemplate.UriTemplate;

public class PetService extends CommonService {
    public static final String QUERY_PARAMETER_KEY_STATUS = "status";
    private static PetService instance;

    public static PetService getInstance() {
        if (instance == null) {
            instance = new PetService();
        }
        return instance;
    }

    public Response getRequest(UriTemplate uri, long petId) {
        return super.getRequest(uri.getUri(petId));
    }

    public Response getRequest(UriTemplate uri, String status) {
        requestSpecification.queryParam(QUERY_PARAMETER_KEY_STATUS, status);

        return super.getRequest(uri.getUri());
    }

    public Response postRequest(UriTemplate uri, Object body) {
        return super.postRequest(uri.getUri(), body);
    }

    public Response deleteRequest(UriTemplate uri, long petId) {
        return super.deleteRequest(uri.getUri(petId));
    }

    public Response updateRequest(UriTemplate uri, Object body) {
        return super.updateRequest(uri.getUri(), body);
    }
}
