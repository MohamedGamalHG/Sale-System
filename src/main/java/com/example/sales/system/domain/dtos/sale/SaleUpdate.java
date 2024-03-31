package com.example.sales.system.domain.dtos.sale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleUpdate {
    private Long Id;
    private int Quantity;
    private double Price;
}
