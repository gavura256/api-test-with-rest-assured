package org.gavura.service.uritemplate;

public record UriTemplate(String url) {
    public String getUri() {
        return String.format(url, "");
    }

    public String getUri(Object... params) {
        return String.format(url, params);
    }
}
