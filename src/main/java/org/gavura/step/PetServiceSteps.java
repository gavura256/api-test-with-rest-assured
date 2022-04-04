package org.gavura.step;

import lombok.experimental.UtilityClass;
import org.gavura.entity.User;
import org.gavura.service.PetService;

import static org.gavura.uritemplate.PetServiceUri.PET_BY_ID;

@UtilityClass
public class PetServiceSteps {
    private static final PetService PET_SERVICE = PetService.getInstance();

    public static User getPetById(int id) {
        return PET_SERVICE.getRequest(PET_BY_ID, id).as(User.class);
    }


}
