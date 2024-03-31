package com.example.sales.system.domain.dtos.product;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProductView {
    private Long Id;
    private String name;
    private String description;
    private int category_id;
    private int quantity;
    private double price;
    private LocalDateTime creation_date;
}
