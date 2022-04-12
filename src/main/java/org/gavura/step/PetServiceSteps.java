package org.gavura.step;

import lombok.experimental.UtilityClass;
import org.gavura.entity.ApiResponse;
import org.gavura.entity.Pet;
import org.gavura.service.PetService;

import java.io.File;
import java.util.List;

import static org.gavura.uritemplate.PetServiceUri.PET;
import static org.gavura.uritemplate.PetServiceUri.PET_BY_ID;
import static org.gavura.uritemplate.PetServiceUri.PET_FIND_BY_STATUS;
import static org.gavura.uritemplate.PetServiceUri.PET_IMAGE_BY_ID;

@UtilityClass
public class PetServiceSteps {
    private static final PetService PET_SERVICE = PetService.getInstance();

    public static Pet getPetById(long id) {
        return PET_SERVICE.getRequest(PET_BY_ID, id)
                .as(Pet.class);
    }

    public static Pet createPet(Pet pet) {
        return PET_SERVICE.postRequest(PET, pet)
                .as(Pet.class);
    }

    public static void deletePetById(long petId) {
         PET_SERVICE.deleteRequest(PET_BY_ID, petId);
    }

    public static Pet updatePet(Pet pet) {
        return PET_SERVICE.updateRequest(PET, pet)
                .as(Pet.class);
    }

    public static List<Pet> getPetsByStatus(String status) {
        return PET_SERVICE.getRequest(PET_FIND_BY_STATUS, status)
                .getBody()
                .jsonPath()
                .getList(".", Pet.class);
    }

    public static ApiResponse uploadImage(long petId, File imageToUpload) {
        return PET_SERVICE.postRequest(PET_IMAGE_BY_ID, petId, imageToUpload)
                .as(ApiResponse.class);
    }
}
