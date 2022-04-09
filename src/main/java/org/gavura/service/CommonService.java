package org.gavura.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.gavura.log.RAFilter;
import org.gavura.utility.ReadApplicationProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public abstract class CommonService {
    public static final String ACCEPT_KEY = "Accept";
    public static final String CONTENT_TYPE_KEY = "Content-Type";
    public static final String APPLICATION_JSON_VALUE = "application/json";
    public static final String API_KEY = "api_key";

    private final UnaryOperator<String> prepareUri = uri -> String.format("%s", uri);

    protected RequestSpecification requestSpecification;

    protected CommonService() {
        RestAssured.baseURI = ReadApplicationProperties.readBaseUrl();
        this.requestSpecification = RestAssured.given()
                .filter(new RAFilter());
        setCommonParams();
    }

    protected void setCommonParams() {
        Map<String, String> headers = new HashMap<>();
        headers.put(ACCEPT_KEY, APPLICATION_JSON_VALUE);
        headers.put(CONTENT_TYPE_KEY, APPLICATION_JSON_VALUE);
        headers.put(API_KEY, ReadApplicationProperties.readApiKey());
        requestSpecification.headers(headers);
    }

    protected Response getRequest(String uri) {
        return requestSpecification.expect()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(prepareUri.apply(uri));
    }

    protected Response postRequest(String uri, Object body) {
        return requestSpecification.body(body)
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .post(prepareUri.apply(uri));
    }

    protected Response postRequest(String uri) {
        return requestSpecification.expect()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .post(prepareUri.apply(uri));
    }

    protected Response updateRequest(String uri, Object body) {
        return requestSpecification.body(body)
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .put(prepareUri.apply(uri));
    }

    protected Response deleteRequest(String uri) {
        return requestSpecification.expect()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .delete(prepareUri.apply(uri));
    }
}
