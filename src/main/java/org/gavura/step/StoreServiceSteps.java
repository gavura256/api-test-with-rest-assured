package org.gavura.step;

import lombok.experimental.UtilityClass;
import org.gavura.entity.Store;
import org.gavura.service.StoreService;

import static org.gavura.uritemplate.StoreServiceUri.STORE_INVENTORY;
import static org.gavura.uritemplate.StoreServiceUri.STORE_ORDER;

@UtilityClass
public class StoreServiceSteps {
    private static final StoreService STORE_SERVICE = StoreService.getInstance();

    public static String getInventory() {
        return STORE_SERVICE.getRequest(STORE_INVENTORY)
                .getBody()
                .asPrettyString();
    }

    public static String postStore(Store storeOrder){
        return STORE_SERVICE.postRequest(STORE_ORDER, storeOrder)
                .getBody()
                .asPrettyString();
    }
}
