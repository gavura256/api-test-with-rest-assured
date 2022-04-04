package org.gavura.step;

import lombok.experimental.UtilityClass;
import org.gavura.entity.User;
import org.gavura.service.Petservice;

import static org.gavura.uritemplate.PetServiceUri.PET_BY_ID;

@UtilityClass
public class PetServiceSteps {
    private static final Petservice PET_SERVICE = Petservice.getInstance();

    public static User getPetById(Integer id) {
        return PET_SERVICE.getRequest(PET_BY_ID, String.valueOf(id)).as(User.class);
    }


}
