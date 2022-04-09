package com.gavura.apitests;

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
    public void createUserAndVerifyWhetherCreatedUserEqualsExpectedTest() {
        User expectedUser = createUser();
        UserServiceSteps.createUser(expectedUser);
        User createdUser = UserServiceSteps.getUserByName(expectedUser.getUsername());

        assertThat(createdUser, is(equalTo(expectedUser)));
    }

    @Test
    public void deleteUserAndVerifyWhetherResponseBodyContainsDeletedUserNameTest() {
        User expectedUser = createUser();
        UserServiceSteps.createUser(expectedUser);
        String deletedUserName = UserServiceSteps.deleteUserByUserName(expectedUser.getUsername()).getMessage();

        assertThat(deletedUserName, is(equalTo(expectedUser.getUsername())));
    }

    @Test
    public void updateUserAndVerifyWhetherUpdatedUserIsAsExpectedTest() {
        User expectedUser = createUser();
        UserServiceSteps.createUser(expectedUser);
        User updatedUser = createUser();
        UserServiceSteps.updateUserByUserName(expectedUser.getUsername(), updatedUser);
        User userFromDataBase = UserServiceSteps.getUserByName(updatedUser.getUsername());

        assertThat(userFromDataBase, is(equalTo(updatedUser)));
    }

    @Test
    public void createTenUsersAndVerifyWhetherResponseBodyMessageContainsOk() {
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
