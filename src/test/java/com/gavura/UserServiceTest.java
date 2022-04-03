package com.gavura;

import org.gavura.entity.User;
import org.gavura.step.UserServiceSteps;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserServiceTest {

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
        updatedUser.setUsername(expectedUser.getUsername());
        UserServiceSteps.updateUserByUserName(expectedUser.getUsername(), updatedUser);

        assertThat(UserServiceSteps.getUserByName(updatedUser.getUsername()), is(notNullValue()));
    }

    @Test
    public void createUsersFromListTest() {
        List<User> list = new ArrayList<>();
        IntStream.range(0, 10).forEach(iteration -> list.add(createUser()));
        String respondForAddingListOfUsers = UserServiceSteps.createUsersWithList(list).getMessage();

        assertThat(respondForAddingListOfUsers, is(containsStringIgnoringCase("ok")));
    }

    private User createUser() {
        Random random = new Random();
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
