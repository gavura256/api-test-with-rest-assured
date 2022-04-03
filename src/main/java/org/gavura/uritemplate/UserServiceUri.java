package org.gavura.uritemplate;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserServiceUri {
    public static final UriTemplate USER = new UriTemplate("user");
    public static final UriTemplate USER_BY_USERNAME = new UriTemplate("user/%s");
    public static final UriTemplate USERS_BY_LIST= new UriTemplate("user/createWithList");
}
