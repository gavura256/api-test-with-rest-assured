package com.gavura;

import org.gavura.entity.User;
import org.gavura.log.Log;
import org.gavura.step.UserServiceSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserTest {

    @Test
    public void createUsersTest() {
        User expectedUser = createUser();
        UserServiceSteps.createUser(expectedUser);
        User actualUser = UserServiceSteps.getUserByName(expectedUser.getUsername());

        assertThat(actualUser, is(equalTo(expectedUser)));
    }

    @Test
    public void deleteUserTest(){
        User expectedUser = createUser();
        UserServiceSteps.createUser(expectedUser);


    }


    private User createUser() {
        Random random = new Random();
        return User.builder().username("username" + random.nextInt())
                .firstName("firstName" + random.nextInt())
                .lastName("lastName" + random.nextInt())
                .email("email" + random.nextInt() + "@gmail.com")
                .password("password" + random.nextInt())
                .phone("phone" + random.nextInt())
                .userStatus(random.nextInt())
                .build();
    }
}
