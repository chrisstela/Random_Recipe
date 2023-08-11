package com.example.recipegenerator.repository;

import com.example.recipegenerator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "INSERT INTO m_user (name, user_credential_id) VALUES (:name, :credentialId)", nativeQuery = true)
    @Modifying
    void registerUser(@Param("name") String name, @Param("credentialId") String credentialId);
}
