package com.ajb.secureapp.service.impl;

import com.ajb.secureapp.dto.SignUpRequest;
import com.ajb.secureapp.model.User;
import com.ajb.secureapp.repository.UserRepository;
import com.ajb.secureapp.service.RoleService;
import com.ajb.secureapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private RoleService roleService;
    final private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void register(SignUpRequest signUpRequest) {
        User user = new User(
                signUpRequest.getUsername(),
                passwordEncoder.encode(signUpRequest.getPassword())
        );
        user.setActive(true);
        user.addRole(roleService.getDefaultUserRole());
        userRepository.save(user);
    }

    @Transactional
    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
