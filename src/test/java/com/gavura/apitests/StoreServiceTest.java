package com.gavura.apitests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.gavura.entity.Store;
import org.gavura.step.StoreServiceSteps;
import org.gavura.utility.ConvertHelper;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.gavura.utility.ObjectsCreator.createStore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertThrows;

@Epic("REST API Regression Testing using TestNG")
@Feature("Test pet service")
public class StoreServiceTest {
    private static final String INVENTORY_SCHEMA_JSON = "src/test/resources/inventorySchema.json";
    private static final String STORE_ORDER_SCHEMA = "src/test/resources/storeOrderSchema.json";

    @Test
    @Step("Get store inventory and verify that response body matches json schema")
    @Description("Returns a map of status codes to quantities")
    @Severity(SeverityLevel.BLOCKER)
    public void getStoreInventoryMethodShouldReturnAMapOfStatusCodesToQuantitiesTest() {
        File inventoryResponseSchema = new File(INVENTORY_SCHEMA_JSON);
        String inventoryResponse = StoreServiceSteps.getInventory();

        assertThat(inventoryResponse, is(matchesJsonSchema(inventoryResponseSchema)));
    }

    @Test
    @Step("Place purchase order for a pet and verify that response json matches validate json schema")
    @Severity(SeverityLevel.BLOCKER)
    public void postStoreMethodShouldReturnJsonWithValidDataTest() {
        File storeOrderSchema = new File(STORE_ORDER_SCHEMA);
        String actualStore = StoreServiceSteps.postStore(createStore());

        assertThat(actualStore, is(matchesJsonSchema(storeOrderSchema)));
    }

    @Test
    @Step("Find purchase order by id and verify that response json matches validate json schema test")
    @Description("For valid response try integer IDs with positive integer value. " +
            "Negative or non-integer values will generate API errors")
    @Severity(SeverityLevel.BLOCKER)
    public void getOrderByIdShouldReturnStoreOrderTest() {
        File storeOrderSchema = new File(STORE_ORDER_SCHEMA);
        String expectedStoreOrder = StoreServiceSteps.postStore(createStore());
        Long expectedStoreOrderId = ConvertHelper.convertStringToPojoStoreClass(expectedStoreOrder)
                .getId();
        String actualStoreOrder = StoreServiceSteps.getOrderById(expectedStoreOrderId);

        assertThat(actualStoreOrder, is(matchesJsonSchema(storeOrderSchema)));
    }

    @Test
    @Step("Delete purchase order by id and verify that after deleting the order does not exist")
    @Description("For valid response try integer IDs with positive integer value. " +
            "Negative or non-integer values will generate API errors")
    @Severity(SeverityLevel.BLOCKER)
    public void deleteOrderByIdShouldThrowsExceptionWhenOrderDoesNotExistTest() {
        Store expectedOrder = createStore();
        StoreServiceSteps.postStore(expectedOrder);
        StoreServiceSteps.deleteOrderById(expectedOrder.getId());

        assertThrows(() -> StoreServiceSteps.getOrderById(expectedOrder.getId()));
    }
}
