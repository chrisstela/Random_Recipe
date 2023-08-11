package com.example.recipegenerator.service.impl;

import com.example.recipegenerator.entity.Role;
import com.example.recipegenerator.entity.constant.ERole;
import com.example.recipegenerator.repository.RoleRepository;
import com.example.recipegenerator.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByType(ERole roleType) {
        return roleRepository.findByRole(roleType);
    }
}

