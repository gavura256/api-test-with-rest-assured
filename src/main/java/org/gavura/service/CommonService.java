package org.gavura.service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.gavura.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;

public abstract class CommonService {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/";

    private final UnaryOperator<String> prepareUri = uri -> String.format("%s%s", BASE_URI, uri);

    protected RequestSpecification requestSpecification;

    protected CommonService() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        this.requestSpecification = RestAssured.given();
        setCommonParams();
    }

    protected void setCommonParams() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        requestSpecification.headers(headers);
    }

    protected Response getRequest(String uri) {
        return requestSpecification.expect().statusCode(HttpStatus.SC_OK)
                .log().all()
                .when().get(prepareUri.apply(uri));
    }

    protected Response postRequest(String uri, Object body) {
        return requestSpecification.body(body)
                .expect().statusCode(HttpStatus.SC_OK)
                .log().all()
                .when().post(prepareUri.apply(uri));
    }

    protected Response deleteRequest(String uri) {
        return requestSpecification.expect().statusCode(HttpStatus.SC_OK)
                .log().all()
                .when().delete(prepareUri.apply(uri));
    }
    protected Response updateRequest(String uri, Object body) {
        return requestSpecification.body(body).expect().statusCode(HttpStatus.SC_OK)
                .log().all()
                .when().put(prepareUri.apply(uri));
    }
}
