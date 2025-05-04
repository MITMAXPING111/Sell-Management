package com.example.sale.model.responseDTO;

import java.math.BigDecimal;

import com.example.sale.model.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleItemResponseDTO {
    private Long id;
    private Product product;
    private Integer quantity;
    private BigDecimal price;
}
