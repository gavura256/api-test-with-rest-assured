package com.gavura.apitests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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
@Feature("User service tests")
public class UserServiceTest {
    @Test
    @Description("Create user method should not throw any exceptions")
    @Severity(SeverityLevel.BLOCKER)
    public void testCreateUserMethodShouldNotThrowsAnyExceptions() {
        User expectedUser = createUser();
        UserServiceSteps.createUser(expectedUser);
    }

    @Test
    @Description("Create user and verify whether created user equals expected")
    @Severity(SeverityLevel.BLOCKER)
    public void testGetUserByNameMethodShouldReturnUserAsExpected() {
        User expectedUser = createUser();
        UserServiceSteps.createUser(expectedUser);
        User createdUser = UserServiceSteps.getUserByName(expectedUser.getUsername());

        assertThat(createdUser, is(equalTo(expectedUser)));
    }

    @Test
    @Description("Delete user and verify whether response body contains deleted user name")
    @Severity(SeverityLevel.BLOCKER)
    public void testDeleteUserByUserNameMethodShouldReturnUserName() {
        User expectedUser = createUser();
        UserServiceSteps.createUser(expectedUser);
        String deletedUserName = UserServiceSteps.deleteUserByUserName(expectedUser.getUsername())
                .getMessage();

        assertThat(deletedUserName, is(equalTo(expectedUser.getUsername())));
    }

    @Test
    @Description("Update user and verify whether updated user is as expected")
    @Severity(SeverityLevel.BLOCKER)
    public void testUpdateUserByUserNameMethodShouldPutUserIntoRightPlace() {
        User expectedUser = createUser();
        UserServiceSteps.createUser(expectedUser);
        User updatedUser = createUser();
        UserServiceSteps.updateUserByUserName(expectedUser.getUsername(), updatedUser);
        User userFromDataBase = UserServiceSteps.getUserByName(updatedUser.getUsername());

        assertThat(userFromDataBase, is(equalTo(updatedUser)));
    }

    @Test
    @Description("Create users with list and verify whether response body message contains 'ok'")
    @Severity(SeverityLevel.BLOCKER)
    public void createUsersWithListMethodShouldReturnOkInMessageField() {
        List<User> list = new ArrayList<>();
        IntStream.range(0, 10).forEach(iteration -> list.add(createUser()));
        String message = UserServiceSteps.createUsersWithList(list).getMessage();

        assertThat(message, is(containsStringIgnoringCase("ok")));
    }
}
