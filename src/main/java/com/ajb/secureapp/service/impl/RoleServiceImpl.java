package com.ajb.secureapp.service.impl;

import com.ajb.secureapp.config.Constants;
import com.ajb.secureapp.model.Role;
import com.ajb.secureapp.repository.RoleRepository;
import com.ajb.secureapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    final private RoleRepository roleRepository;

    @Transactional
    @Override
    public Role getDefaultUserRole() {
        return roleRepository.findByName(Constants.DEFAULT_USER_ROLE);
    }
}
