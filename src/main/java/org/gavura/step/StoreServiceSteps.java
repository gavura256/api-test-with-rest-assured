package org.gavura.step;

import lombok.experimental.UtilityClass;
import org.gavura.service.StoreService;

import static org.gavura.uritemplate.StoreServiceUri.STORE_INVENTORY;


@UtilityClass
public class StoreServiceSteps {
    private static final StoreService STORE_SERVICE = StoreService.getInstance();


    public static String getInventory() {
        return STORE_SERVICE.getRequest(STORE_INVENTORY).getBody().asPrettyString();
    }
}
