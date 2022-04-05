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
    private static final String BASE_URI = ReadApplicationProperties.readBaseUrl();

    private final UnaryOperator<String> prepareUri = uri -> String.format("%s%s", BASE_URI, uri);

    protected RequestSpecification requestSpecification;

    protected CommonService() {
        this.requestSpecification = RestAssured.given().filter(new RAFilter());
        setCommonParams();
    }

    protected void setCommonParams() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("api_key", "api_key");
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
