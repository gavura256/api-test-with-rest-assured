package org.gavura.service;

import io.restassured.response.Response;
import org.gavura.uritemplate.UriTemplate;

public class UserService extends CommonService {
    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public Response getRequest(UriTemplate uri, String username) {
        return super.getRequest(uri.getUri(username));
    }

    public Response postRequest(UriTemplate uri, Object body) {
        return super.postRequest(uri.getUri(), body);
    }

    public Response deleteRequest(UriTemplate uri, String username) {
        return super.deleteRequest(uri.getUri(username));
    }

    public Response updateRequest(UriTemplate uri, String username, Object body) {
        return super.updateRequest(uri.getUri(username), body);
    }
}
