package com.gavura;

import org.gavura.step.StoreServiceSteps;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StoreServiceTest {
    public static final String INVENTORY_SCHEMA_JSON = "src/test/resources/inventorySchema.json";

    @Test
    public void getStoreInventoryAndVeryFyThatResponseBodyMatchesJsonSchemeTest() {
        File inventoryResponseSchema = new File(INVENTORY_SCHEMA_JSON);
        String inventoryResponse = StoreServiceSteps.getInventory();

        assertThat(inventoryResponse, is(matchesJsonSchema(inventoryResponseSchema)));
    }
}
