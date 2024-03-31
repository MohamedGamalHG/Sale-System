package com.example.sales.system.domain.dtos.sale;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class SaleView {
    private Long Id;
    private int client_id;
    private int seller_id;
    private LocalDateTime creation_date;
    private double total;
}
