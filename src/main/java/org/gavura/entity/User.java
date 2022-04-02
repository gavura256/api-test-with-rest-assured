package org.gavura.entity;

import lombok.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class User {
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Long id;
    @Setter
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;
}
