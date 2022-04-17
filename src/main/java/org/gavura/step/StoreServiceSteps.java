package org.gavura.step;

import lombok.experimental.UtilityClass;
import org.gavura.entity.ApiResponse;
import org.gavura.entity.Store;
import org.gavura.service.StoreService;

import static org.gavura.uritemplate.StoreServiceUri.STORE_INVENTORY;
import static org.gavura.uritemplate.StoreServiceUri.STORE_ORDER;
import static org.gavura.uritemplate.StoreServiceUri.STORE_ORDER_BY_ID;

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

    public static String getOrderById(Long id) {
        return STORE_SERVICE.getRequest(STORE_ORDER_BY_ID, id)
                .getBody()
                .asPrettyString();
    }

    public static ApiResponse deleteOrderById(Long id) {
        return STORE_SERVICE.deleteRequest(STORE_ORDER_BY_ID, id)
                .as(ApiResponse.class);
    }
}
