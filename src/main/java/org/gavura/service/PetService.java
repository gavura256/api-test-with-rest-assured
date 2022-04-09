package org.gavura.service;

import io.restassured.response.Response;
import org.gavura.uritemplate.UriTemplate;

import java.io.File;

public class PetService extends CommonService {
    public static final String QUERY_PARAMETER_KEY_STATUS = "status";
    public static final String MULTIPART_FORM_DATA_VALUE = "multipart/form-data";
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

    public Response postRequest(UriTemplate uri, long petId, File imageToUpload) {
        requestSpecification.headers(CONTENT_TYPE_KEY, MULTIPART_FORM_DATA_VALUE)
                .multiPart(imageToUpload);

        return super.postRequest(uri.getUri(petId));
    }
}
