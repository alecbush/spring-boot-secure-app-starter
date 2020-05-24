package com.ajb.secureapp.config;

import com.ajb.secureapp.model.Role;
import com.ajb.secureapp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class DbInit {

    final private RoleRepository roleRepository;

    @PostConstruct
    private void postConstruct() {
        Role userRole = roleRepository.findByName(Constants.DEFAULT_USER_ROLE);
        if (userRole == null) {
            userRole = new Role(Constants.DEFAULT_USER_ROLE);
            roleRepository.save(userRole);
        }
    }
}
