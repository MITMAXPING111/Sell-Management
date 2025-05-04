package com.example.sale.model.requestDTO;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private String name;
    private BigDecimal price;
    private Integer stock;
}
