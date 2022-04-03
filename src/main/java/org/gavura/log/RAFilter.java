package org.gavura.log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.json.JSONObject;

import java.util.stream.IntStream;

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
                .append("\nBody: ").append(objectValidation(getBody(frqSpec)))
                .append("\n******************************");

        responseBuilderLogs = new StringBuilder()
                .append("\nStatus Code: ").append(response.getStatusCode())
                .append("\nStatus Line: ").append(response.getStatusLine())
                .append("\nResponse Cookies: ").append(response.getDetailedCookies())
                .append("\nResponse Content Type: ").append(response.getContentType())
                .append("\nResponse Headers: ").append(response.getHeaders())
                .append("\nResponse Body: \n").append(response.getBody().asPrettyString())
                .append("\n******************************");

        Log.printLog(getRequestBuilder());
        Log.printLog(getResponseBuilder());

        return response;
    }

    private String getBody(FilterableRequestSpecification frqSpec) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String bodyWithSquareBrackets = frqSpec.getBody().toString();


        String s = JSONObject.stringToValue(bodyWithSquareBrackets).toString();
        return gson.toJson(s);



    }

    private String getRequestBuilder() {
        return requestBuilderLogs.toString();
    }

    private String getResponseBuilder() {
        return responseBuilderLogs.toString();
    }

    public String objectValidation(Object o) {
        if (o == null)
            return null;
        else
            return o.toString();
    }
}