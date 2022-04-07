package org.gavura.uritemplate;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PetServiceUri {
    public static final UriTemplate PET = new UriTemplate("pet");
    public static final UriTemplate PET_BY_ID = new UriTemplate("pet/%s");
    public static final UriTemplate PET_FIND_BY_STATUS = new UriTemplate("pet/findByStatus");
    public static final UriTemplate PET_IMAGE_BY_ID = new UriTemplate("pet/%s/uploadImage");
}
