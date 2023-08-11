package com.example.recipegenerator.service;

import com.example.recipegenerator.entity.Role;
import com.example.recipegenerator.entity.constant.ERole;

public interface RoleService {
    Role getRoleByType(ERole roleType);
}
