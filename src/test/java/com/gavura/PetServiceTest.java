package com.gavura;

import org.gavura.entity.Category;
import org.gavura.entity.Pet;
import org.gavura.entity.Tag;
import org.gavura.step.PetServiceSteps;
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
    public void deletePetAndVerifyWhetherPetWithThisIdDoesNotExist(){
        Pet expectedPet = createPet();
        Long actualPetId = PetServiceSteps.createPet(expectedPet).getId();
        PetServiceSteps.deletePetById(actualPetId);

        assertThrows(() -> PetServiceSteps.getPetById(actualPetId));
    }

    private Pet createPet() {
        Random random = new Random();
        return Pet.builder().category(new Category(0, "available"))
                .name("categoryName" + random.nextInt())
                .photoUrls(List.of("photoUrl" + random.nextInt()))
                .tags(List.of(new Tag(0, "tag" + random.nextInt())))
                .status("statusPet" + random.nextInt())
                .build();
    }
}
