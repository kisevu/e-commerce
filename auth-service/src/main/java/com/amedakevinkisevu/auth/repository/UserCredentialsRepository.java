package com.amedakevinkisevu.auth.repository;

import com.amedakevinkisevu.auth.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials,Integer> {
    Optional<UserCredentials> findByName(String username);
}
