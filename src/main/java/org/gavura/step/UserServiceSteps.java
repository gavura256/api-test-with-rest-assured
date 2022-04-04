package org.gavura.step;

import lombok.experimental.UtilityClass;
import org.gavura.entity.ApiResponse;
import org.gavura.entity.User;
import org.gavura.service.UserService;

import java.util.List;

import static org.gavura.uritemplate.UserServiceUri.USER;
import static org.gavura.uritemplate.UserServiceUri.USERS_BY_LIST;
import static org.gavura.uritemplate.UserServiceUri.USER_BY_USERNAME;


@UtilityClass
public class UserServiceSteps {
    private static final UserService USER_SERVICE = UserService.getInstance();

    public static User getUserByName(String username) {
        return USER_SERVICE.getRequest(USER_BY_USERNAME, username).as(User.class);
    }

    public void createUser(User user) {
        USER_SERVICE.postRequest(USER, user);
    }

    public static ApiResponse createUsersWithList(List<User> users) {
        return USER_SERVICE.postRequest(USERS_BY_LIST, users).as(ApiResponse.class);
    }

    public static ApiResponse deleteUserByUserName(String username) {
        return USER_SERVICE.deleteRequest(USER_BY_USERNAME, username).as(ApiResponse.class);
    }

    public static ApiResponse updateUserByUserName(String username, User user) {
        return USER_SERVICE.updateRequest(USER_BY_USERNAME, username, user).as(ApiResponse.class);
    }
}
