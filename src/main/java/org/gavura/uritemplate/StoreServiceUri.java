package org.gavura.uritemplate;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StoreServiceUri {
    public static final UriTemplate STORE_INVENTORY = new UriTemplate("store/inventory");
    public static final UriTemplate STORE_ORDER = new UriTemplate("store/order");
}
