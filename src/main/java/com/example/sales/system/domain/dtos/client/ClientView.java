package com.example.sales.system.domain.dtos.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ClientView {
    private Long Id;
    private String name;
    private String last_name;
    private String email;
    private String address;
    private String mobile;
}
