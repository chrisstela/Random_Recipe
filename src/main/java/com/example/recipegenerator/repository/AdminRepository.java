package com.example.recipegenerator.repository;

import com.example.recipegenerator.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    @Query(value = "INSERT INTO m_admin (name, user_credential_id) VALUES (:name, :credentialId)", nativeQuery = true)
    @Modifying
    void registerAdmin(@Param("name") String name, @Param("credentialId") String credentialId);
}
