package com.gavura.apitests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.gavura.entity.User;
import org.gavura.step.UserServiceSteps;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.gavura.utility.ObjectsCreator.createUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Epic("REST API Regression Testing using TestNG")
@Feature("User service API test")
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
        String deletedUserName = UserServiceSteps.deleteUserByUserName(expectedUser.getUsername())
                .getMessage();

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
    public void createUsersWithListAndVerifyWhetherResponseBodyMessageContainsOkTest() {
        List<User> list = new ArrayList<>();
        IntStream.range(0, 10).forEach(iteration -> list.add(createUser()));
        String respondForAddingListOfUsers = UserServiceSteps.createUsersWithList(list)
                .getMessage();

        assertThat(respondForAddingListOfUsers, is(containsStringIgnoringCase("ok")));
    }
}
