package org.gavura.log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
                .append("\nBody: ").append(toPrettyFormat(frqSpec))
                .append("\n******************************");

        responseBuilderLogs = new StringBuilder()
                .append("\nStatus Code: ").append(response.getStatusCode())
                .append("\nStatus Line: ").append(response.getStatusLine())
                .append("\nResponse Cookies: ").append(response.getDetailedCookies())
                .append("\nResponse Content Type: ").append(response.getContentType())
                .append("\nResponse Headers: ").append(response.getHeaders())
                .append("\nResponse Body: \n").append(response.getBody().asPrettyString())
                .append("\n******************************");

        Log.print(getRequestBuilder());
        Log.print(getResponseBuilder());

        return response;
    }

    public static String toPrettyFormat(FilterableRequestSpecification frs) {
        final String body;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if (frs.getBody() != null) {
            body = frs.getBody().toString();

        } else {
            body = "{}";
        }
        JsonElement json = JsonParser.parseString(body);

        if (json instanceof JsonObject) {
            return gson.toJson(json.getAsJsonObject());
        }

        return gson.toJson(json.getAsJsonArray());
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