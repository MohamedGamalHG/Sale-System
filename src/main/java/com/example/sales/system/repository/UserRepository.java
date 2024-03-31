package com.example.sales.system.repository;

import com.example.sales.system.domain.entities.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<JpaUser,Long> {
    JpaUser findByUsername(String name);

}
