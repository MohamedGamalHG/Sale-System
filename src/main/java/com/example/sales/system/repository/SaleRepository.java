package com.example.sales.system.repository;

import com.example.sales.system.domain.entities.JpaSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<JpaSale,Long> {
}
