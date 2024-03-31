package com.example.sales.system.repository;

import com.example.sales.system.domain.entities.JpaClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<JpaClient,Long> {
}
