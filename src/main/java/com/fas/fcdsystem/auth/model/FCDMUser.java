package com.fas.fcdsystem.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FCDMUser {
    private String userId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String role;
    private String status;
    private String failCount;
    private String isBlocked;
}
