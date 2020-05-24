package com.ajb.secureapp.service;

import com.ajb.secureapp.dto.SignUpRequest;

public interface UserService {

    void register(SignUpRequest signUpRequest);

    boolean userExists(String username);
}
