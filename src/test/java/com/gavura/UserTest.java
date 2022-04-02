package com.gavura;

import org.gavura.entity.User;
import org.gavura.step.UserServiceSteps;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserTest {


    @Test
    public void getUsersTest() {
        User actualUser = User.builder().id(11111111111364L)
                .username("username-1987504084")
                .firstName("firstName-21461829")
                .lastName("lastName-1874789715")
                .email("email291838304@gmail.com")
                .password("password1820333349")
                .phone("phone-1662439164")
                .userStatus(-793663380).build();
        User expectedUser = UserServiceSteps.getUserByName(actualUser.getUsername());

        assertThat(actualUser, is(equalTo(expectedUser)));
    }

    @Test
    public void createUsersTest() {
        User expectedUser = createUser();
        UserServiceSteps.createUser(expectedUser);
        User actualUser = UserServiceSteps.getUserByName(expectedUser.getUsername());

        assertThat(actualUser, is(equalTo(expectedUser)));
    }

    @Test
    public void deleteUserTest() {
        User expectedUser = createUser();
        UserServiceSteps.createUser(expectedUser);
        String deletedUserName = UserServiceSteps.deleteUserByUserName(expectedUser.getUsername()).getMessage();

        assertThat(deletedUserName, is(equalTo(expectedUser.getUsername())));
    }

    @Test
    public void updateUserTest() {
        User expectedUser = createUser();
        UserServiceSteps.createUser(expectedUser);

        User updatedUser = createUser();
        ;
//        UserServiceSteps.updateUserByUserName(expectedUser.getUsername())
//        ;
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
