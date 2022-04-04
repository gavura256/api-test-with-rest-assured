package org.gavura.service;

import io.restassured.response.Response;
import org.gavura.uritemplate.UriTemplate;

public class PetService extends CommonService{
    private static PetService instance;

    public static PetService getInstance() {
        if (instance == null) {
            instance = new PetService();
        }
        return instance;
    }

    public Response getRequest(UriTemplate uri, int id) {
        return super.getRequest(uri.getUri(id));
    }
}
