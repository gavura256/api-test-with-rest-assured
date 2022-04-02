package org.gavura.step;

import io.restassured.response.Response;
import lombok.experimental.UtilityClass;
import org.gavura.entity.ApiResponse;
import org.gavura.entity.User;
import org.gavura.service.UserService;

import static org.gavura.service.uritemplate.UserServiceUri.USER;
import static org.gavura.service.uritemplate.UserServiceUri.USER_BY_USERNAME;

@UtilityClass
public class UserServiceSteps {
    private static final UserService USER_SERVICE = UserService.getInstance();

    public static User getUserByName(String username) {
        return USER_SERVICE.getRequest(USER_BY_USERNAME, username)
                .as(User.class);
    }

    public static void createUser(User user) {
        USER_SERVICE.postRequest(USER, user);
    }

    public static ApiResponse deleteUserByUserName(String username) {
        return USER_SERVICE.deleteRequest(USER_BY_USERNAME, username)
                .as(ApiResponse.class);
    }

    public static ApiResponse updateUserByUserName(String username, User user) {
        return USER_SERVICE.updateRequest(USER_BY_USERNAME, username, user)
                .as(ApiResponse.class);
    }
}
