package com.example.recipegenerator.repository;

import com.example.recipegenerator.entity.Role;
import com.example.recipegenerator.entity.constant.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRole(ERole roleType);
}