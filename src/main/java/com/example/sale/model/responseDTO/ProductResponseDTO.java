package com.example.sale.model.responseDTO;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
}
