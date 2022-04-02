package org.gavura.service.uritemplate;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserServiceUri {
    public static final UriTemplate USER = new UriTemplate("user");
    public static final UriTemplate USER_BY_USERNAME = new UriTemplate("user/%s");
}
