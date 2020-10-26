package com.reka.lakatos.angularchatbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserCredentials {
    private String userName;
    private String password;
}