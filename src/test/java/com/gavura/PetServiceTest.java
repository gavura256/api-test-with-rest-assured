package com.gavura;

import org.gavura.entity.Category;
import org.gavura.entity.Pet;
import org.gavura.entity.Tag;
import org.gavura.step.PetServiceSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertThrows;

public class PetServiceTest {
    @Test
    public void createPetAndVerifyWhetherCreatedPetIsTheSameAsExpectedTest() {
        Pet expectedPet = createPet();
        Pet actualPet = PetServiceSteps.createPet(expectedPet);

        assertThat(actualPet, is(equalTo(expectedPet)));
    }

    @Test
    public void deletePetAndVerifyWhetherPetWithThisIdDoesNotExist() {
        Pet expectedPet = createPet();
        Long actualPetId = PetServiceSteps.createPet(expectedPet).getId();
        String responseMessage = PetServiceSteps.deletePetById(actualPetId).getMessage();

        assertThrows(() -> PetServiceSteps.getPetById(actualPetId));
        assertThat(responseMessage, is(equalTo(String.valueOf(actualPetId))));
    }

    @Test
    public void getPetByIdTest() {
        Pet expectedPet = createPet();
        Long actualPetId = PetServiceSteps.createPet(expectedPet).getId();
        Pet actualPet = PetServiceSteps.getPetById(actualPetId);

        assertThat(actualPet, is(equalTo(expectedPet)));
    }

    @Test
    public void updatePetAndVerifyWhetherRequestReturnValidUpdatedPet() {
        Pet expectedPet = createPet();
        PetServiceSteps.createPet(expectedPet);
        Pet updatedPet = PetServiceSteps.updatePet(expectedPet);

        assertThat(expectedPet, is(equalTo(updatedPet)));
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
