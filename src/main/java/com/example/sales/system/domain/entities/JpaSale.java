package com.example.sales.system.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JpaSale extends BaseEntity{
    private int client_id;
    private int seller_id;
    private LocalDateTime creation_date;
    private int product_id;
    private double price;
    private int quantity;
}
