package org.gavura.step;

import lombok.experimental.UtilityClass;
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

    public static void deleteUserById(String username) {
        USER_SERVICE.deleteRequest(USER_BY_USERNAME, username);
    }
}
