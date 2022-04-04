package org.gavura.step;

import lombok.experimental.UtilityClass;
import org.gavura.entity.Pet;
import org.gavura.service.PetService;

import static org.gavura.uritemplate.PetServiceUri.PET;
import static org.gavura.uritemplate.PetServiceUri.PET_BY_ID;

@UtilityClass
public class PetServiceSteps {
    private static final PetService PET_SERVICE = PetService.getInstance();

    public static Pet getPetById(int id) {
        return PET_SERVICE.getRequest(PET_BY_ID, id).as(Pet.class);
    }
    public static Pet createPet(Pet pet) {
        return PET_SERVICE.postRequest(PET, pet).as(Pet.class);
    }

}
