package com.example.sales.system.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JpaProduct extends BaseEntity{
    private String name;
    private String description;
    private int category_id;
    private int quantity;
    private double price;
    private LocalDateTime creation_date;
}
