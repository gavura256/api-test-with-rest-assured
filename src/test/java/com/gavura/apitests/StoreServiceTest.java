package com.gavura.apitests;

import org.gavura.entity.Store;
import org.gavura.step.StoreServiceSteps;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Date;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StoreServiceTest {
    public static final String INVENTORY_SCHEMA_JSON = "src/test/resources/inventorySchema.json";
    private static final String STORE_ORDER_SCHEMA = "src/test/resources/storeOrderSchema.json";
    ;

    @Test
    public void getStoreInventoryAndVeryFyThatResponseBodyMatchesJsonSchemeTest() {
        File inventoryResponseSchema = new File(INVENTORY_SCHEMA_JSON);
        String inventoryResponse = StoreServiceSteps.getInventory();

        assertThat(inventoryResponse, is(matchesJsonSchema(inventoryResponseSchema)));
    }

    @Test
    public void placeAnOrderForAPetAndVerifyThatResponseJsonMatchesValidateJsonSchemaTest() {
        File storeOrderSchema = new File(STORE_ORDER_SCHEMA);
        Store store = createStore();
        String actualStoreOrder = StoreServiceSteps.postStore(store);

        assertThat(actualStoreOrder, is(matchesJsonSchema(storeOrderSchema)));
    }

    private Store createStore() {
        return Store.builder()
                .id(0L)
                .petId(0L)
                .quantity(0)
                .shipDate(new Date())
                .status("placed")
                .complete(true)
                .build();
    }
}
