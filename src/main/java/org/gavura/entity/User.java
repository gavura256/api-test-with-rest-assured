package org.gavura.entity;

import lombok.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class User {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;
}
