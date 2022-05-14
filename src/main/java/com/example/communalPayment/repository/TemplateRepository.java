package com.example.communalPayment.repository;

import com.example.communalPayment.entity.UserTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<UserTemplate, Long> {
}
