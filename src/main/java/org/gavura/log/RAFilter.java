package org.gavura.log;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RAFilter implements Filter {
    private StringBuilder requestBuilderLogs;
    private StringBuilder responseBuilderLogs;

    @Override
    public Response filter(FilterableRequestSpecification frqSpec,
                           FilterableResponseSpecification frsSpec,
                           FilterContext filterContext) {

        Response response = filterContext.next(frqSpec, frsSpec);

        requestBuilderLogs = new StringBuilder()
                .append("\nRequest URI: ").append(objectValidation(frqSpec.getURI()))
                .append("\nRequest method: ").append(objectValidation(frqSpec.getMethod()))
                .append("\nForm Params: ").append(objectValidation(frqSpec.getFormParams()))
                .append("\nRequest Param: ").append(objectValidation(frqSpec.getRequestParams()))
                .append("\nHeaders: ").append(objectValidation(frqSpec.getHeaders()))
                .append("\nCookies: ").append(objectValidation(frqSpec.getCookies()))
                .append("\nProxy: ").append(objectValidation(frqSpec.getProxySpecification()))
                .append("\nBody: ").append(objectValidation(frqSpec.getBody()))
                .append("\n******************************");

        responseBuilderLogs = new StringBuilder()
                .append("\nStatus Code: ").append(response.getStatusCode())
                .append("\nStatus Line: ").append(response.getStatusLine())
                .append("\nResponse Cookies: ").append(response.getDetailedCookies())
                .append("\nResponse Content Type: ").append(response.getContentType())
                .append("\nResponse Headers: ").append(response.getHeaders())
                .append("\nResponse Body: \n").append(response.getBody().prettyPrint())
                .append("\n******************************");

        Log.printLog(getRequestBuilder());
        Log.printLog(getResponseBuilder());

        return response;
    }

    private String getRequestBuilder() {
        return requestBuilderLogs.toString();
    }

    private String getResponseBuilder() {
        return responseBuilderLogs.toString();
    }

    public String objectValidation(Object o) {
        if (o == null)
            return "no data";
        else
            return o.toString();
    }
}