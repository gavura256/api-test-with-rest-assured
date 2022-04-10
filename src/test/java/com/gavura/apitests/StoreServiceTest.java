package com.gavura.apitests;

import org.gavura.entity.Store;
import org.gavura.step.StoreServiceSteps;
import org.gavura.utility.ConvertHelper;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Date;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertThrows;

public class StoreServiceTest {
    private static final String INVENTORY_SCHEMA_JSON = "src/test/resources/inventorySchema.json";
    private static final String STORE_ORDER_SCHEMA = "src/test/resources/storeOrderSchema.json";

    @Test
    public void getStoreInventoryAndVeryFyThatResponseBodyMatchesJsonSchemeTest() {
        File inventoryResponseSchema = new File(INVENTORY_SCHEMA_JSON);
        String inventoryResponse = StoreServiceSteps.getInventory();

        assertThat(inventoryResponse, is(matchesJsonSchema(inventoryResponseSchema)));
    }

    @Test
    public void placeAnOrderForAPetAndVerifyThatResponseJsonMatchesValidateJsonSchemaTest() {
        File storeOrderSchema = new File(STORE_ORDER_SCHEMA);
        String actualStore = StoreServiceSteps.postStore(createStore());

        assertThat(actualStore, is(matchesJsonSchema(storeOrderSchema)));
    }

    @Test
    public void findPurchaseOrderByIdAndVerifyThatResponseJsonMatchesValidateJsonSchemaTest() {
        File storeOrderSchema = new File(STORE_ORDER_SCHEMA);
        String expectedStoreOrder = StoreServiceSteps.postStore(createStore());
        Long expectedStoreOrderId = ConvertHelper.convertStringToPojoStoreClass(expectedStoreOrder)
                .getId();
        String actualStoreOrder = StoreServiceSteps.getOrderById(expectedStoreOrderId);

        assertThat(actualStoreOrder, is(matchesJsonSchema(storeOrderSchema)));
    }

    @Test
    public void deletePurchaseOrderByIdAndVerifyThatAfterDeletingOrderDoesNotExist() {
        Store expectedStore = createStore();
        StoreServiceSteps.postStore(expectedStore);
        StoreServiceSteps.deleteStoreById(expectedStore.getId());

        assertThrows(() -> StoreServiceSteps.getOrderById(expectedStore.getId()));
    }

    private Store createStore() {
        return Store.builder()
                .id(1L)
                .petId(0L)
                .quantity(0)
                .shipDate(new Date())
                .status("placed")
                .complete(true)
                .build();
    }
}
