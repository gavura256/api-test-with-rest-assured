package org.gavura.service;

import io.restassured.response.Response;
import org.gavura.uritemplate.UriTemplate;

public class StoreService extends CommonService {
    private static StoreService instance;

    public static StoreService getInstance() {
        if (instance == null) {
            instance = new StoreService();
        }

        return instance;
    }

    public Response getRequest(UriTemplate uri) {
        return super.getRequest(uri.getUri());
    }

    public Response getRequest(UriTemplate uri, Long id) {
        return super.getRequest(uri.getUri(id));
    }

    public Response postRequest(UriTemplate uri, Object body) {
        return super.postRequest(uri.getUri(), body);
    }

    public Response deleteRequest(UriTemplate uri, Long id) {
        return super.deleteRequest(uri.getUri(id));
    }
}
