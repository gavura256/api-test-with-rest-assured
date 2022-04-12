package com.gavura.apitests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.gavura.entity.Category;
import org.gavura.entity.Pet;
import org.gavura.entity.Tag;
import org.gavura.step.PetServiceSteps;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.assertThrows;

@Listeners
@Epic("REST API Regression Testing using TestNG")
@Feature("Pet service API test")
public class PetServiceTest {
    public static final String EXCEPTION_MISSING_START_BOUNDARY = "org.jvnet.mimepull.MIMEParsingException: Missing start boundary";
    public static final String DOG_IMAGE_PATH = "src/test/resources/dogimage.jpg";

    @Test
    public void createPetAndVerifyWhetherCreatedPetIsTheSameAsExpectedTest() {
        Pet expectedPet = createPet();
        Pet actualPet = PetServiceSteps.createPet(expectedPet);

        assertThat(actualPet, is(equalTo(expectedPet)));
    }

    @Test
    public void deletePetAndVerifyWhetherPetWithThisIdDoesNotExistTest() {
        Pet expectedPet = createPet();
        Long actualPetId = PetServiceSteps.createPet(expectedPet)
                .getId();
        PetServiceSteps.deletePetById(actualPetId);

        assertThrows(() -> PetServiceSteps.getPetById(actualPetId));
    }

    @Test
    public void getPetByIdAndVerifyWhetherActualPetEqualsToExpectedPetTest() {
        Pet expectedPet = createPet();
        Long actualPetId = PetServiceSteps.createPet(expectedPet)
                .getId();
        Pet actualPet = PetServiceSteps.getPetById(actualPetId);

        assertThat(actualPet, is(equalTo(expectedPet)));
    }

    @Test
    public void updatePetAndVerifyWhetherRequestReturnValidUpdatedPetTest() {
        Pet expectedPet = createPet();
        PetServiceSteps.createPet(expectedPet);
        Pet updatedPet = PetServiceSteps.updatePet(expectedPet);

        assertThat(expectedPet, is(equalTo(updatedPet)));
    }

    @Test
    public void verifyThatFindPetByStatusRequestReturnsOnlyPetsWithExpectedStatusTest() {
        String expectedStatus = "available";
        List<String> actualStatuses = PetServiceSteps.getPetsByStatus(expectedStatus)
                .stream()
                .map(Pet::getStatus)
                .toList();

        assertThat(actualStatuses, everyItem(is(equalTo(expectedStatus))));
    }

    @Test
    public void uploadPetImageAndVerifyThatResponseMessageDoesNotContainExceptionTextTest() {
        File imageToUpload = new File(DOG_IMAGE_PATH);
        Pet expectedPet = createPet();
        Long petId = PetServiceSteps.createPet(expectedPet)
                .getId();
        String messageResponse = PetServiceSteps.uploadImage(petId, imageToUpload)
                .getMessage();

        assertThat(messageResponse, is(not(equalTo(EXCEPTION_MISSING_START_BOUNDARY))));
    }

    private Pet createPet() {
        Random random = new Random();
        return Pet.builder().category(new Category(0L, "available"))
                .name("categoryName" + random.nextInt())
                .photoUrls(List.of("photoUrl" + random.nextInt()))
                .tags(List.of(new Tag(0L, "tag" + random.nextInt())))
                .status("statusPet" + random.nextInt())
                .build();
    }
}
