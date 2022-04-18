package com.gavura.apitests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.gavura.entity.Pet;
import org.gavura.step.PetServiceSteps;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static org.gavura.utility.ObjectsCreator.createPet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.assertThrows;

@Epic("REST API Regression Testing using TestNG")
@Feature("Test pet service")
public class PetServiceTest {
    public static final String DOG_IMAGE_PATH = "src/test/resources/dogimage.jpg";

    @Test
    @Description("Create pet and verify whether created pet equals expected")
    @Severity(SeverityLevel.BLOCKER)
    public void testCreatePetMethodShouldReturnPetTheSameAsExpected() {
        Pet expectedPet = createPet();
        Pet actualPet = PetServiceSteps.createPet(expectedPet);

        assertThat(actualPet, is(equalTo(expectedPet)));
    }

    @Test
    @Description("Delete pet by id and verify whether pet with this id does not exist")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeletePetByIdMethodShouldThrowsExceptionWhenPetIsNonexistent() {
        Pet expectedPet = createPet();
        Long actualPetId = PetServiceSteps.createPet(expectedPet).getId();
        PetServiceSteps.deletePetById(actualPetId);

        assertThrows(() -> PetServiceSteps.getPetById(actualPetId));
    }

    @Test
    @Description("Get pet by id and verify whether actual pet equals to expected")
    @Severity(SeverityLevel.BLOCKER)
    public void testGetPetByIdMethodShouldReturnPetWhenMakeRequest() {
        Pet expectedPet = createPet();
        Long actualPetId = PetServiceSteps.createPet(expectedPet).getId();
        Pet actualPet = PetServiceSteps.getPetById(actualPetId);

        assertThat(actualPet, is(equalTo(expectedPet)));
    }

    @Test
    @Description("Update pet and verify whether request return valid update pet")
    @Severity(SeverityLevel.BLOCKER)
    public void testUpdatePetMethodShouldReturnUpdatedPet() {
        Pet expectedPet = createPet();
        PetServiceSteps.createPet(expectedPet);
        Pet updatedPet = PetServiceSteps.updatePet(expectedPet);

        assertThat(expectedPet, is(equalTo(updatedPet)));
    }

    @Test
    @Description("Find pet by status and verify whether method return only pets with expected status")
    @Severity(SeverityLevel.BLOCKER)
    public void testFindPetByStatusMethodShouldReturnOnlyPetsWithExpectedStatus() {
        String expectedStatus = "available";
        List<String> actualStatuses =
                PetServiceSteps.getPetsByStatus(expectedStatus)
                        .stream()
                        .map(Pet::getStatus)
                        .toList();

        assertThat(actualStatuses, everyItem(is(equalTo(expectedStatus))));
    }

    @Test
    @Description("Upload pet image and verify whether method doesn't return exception message")
    @Severity(SeverityLevel.BLOCKER)
    public void testUploadPetImageMethodShouldNotReturnExceptionMessage() {
        String exceptionMessage = "org.jvnet.mimepull.MIMEParsingException: Missing start boundary";
        File imageToUpload = new File(DOG_IMAGE_PATH);
        Pet expectedPet = createPet();
        Long petId = PetServiceSteps.createPet(expectedPet).getId();
        String messageResponse = PetServiceSteps.uploadImage(petId, imageToUpload).getMessage();

        assertThat(messageResponse, is(not(equalTo(exceptionMessage))));
    }
}
