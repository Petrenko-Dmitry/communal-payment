package com.example.communalPayment.repository;

import com.example.communalPayment.entity.CommunalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunalUserRepository extends JpaRepository<CommunalUser, String> {
    Optional<CommunalUser> findByEmail(String email);
}
