package com.example.sales.system.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JpaClient extends BaseEntity{
    private String name;
    private String last_name;
    private String email;
    private String address;
    private String mobile;
}
