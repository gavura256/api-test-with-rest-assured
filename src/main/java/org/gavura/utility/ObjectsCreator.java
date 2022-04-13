package org.gavura.utility;

import lombok.experimental.UtilityClass;
import org.gavura.entity.Category;
import org.gavura.entity.Pet;
import org.gavura.entity.Store;
import org.gavura.entity.Tag;
import org.gavura.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Random;

@UtilityClass
public class ObjectsCreator {
    private static final Random random = new Random();

    public static Pet createPet() {
        return Pet.builder()
                .category(new Category(0L, "available"))
                .name("categoryName" + random.nextInt())
                .photoUrls(List.of("photoUrl" + random.nextInt()))
                .tags(List.of(new Tag(0L, "tag" + random.nextInt())))
                .status("statusPet" + random.nextInt())
                .build();
    }

    public static Store createStore() {
        return Store.builder()
                .id(1L)
                .petId(0L)
                .quantity(0)
                .shipDate(new Date())
                .status("placed")
                .complete(true)
                .build();
    }

    public static User createUser() {
        return User.builder()
                .username("username" + random.nextInt())
                .firstName("firstName" + random.nextInt())
                .lastName("lastName" + random.nextInt())
                .email("email" + random.nextInt() + "@gmail.com")
                .password("password" + random.nextInt())
                .phone("phone" + random.nextInt())
                .userStatus(random.nextInt())
                .build();
    }
}
