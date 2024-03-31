package com.example.sales.system.repository;

import com.example.sales.system.domain.entities.JpaProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<JpaProduct,Long> {
}
