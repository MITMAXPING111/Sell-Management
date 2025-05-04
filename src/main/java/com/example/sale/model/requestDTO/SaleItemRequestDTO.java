package com.example.sale.model.requestDTO;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleItemRequestDTO {
    private Long productId;
    private Long saleId;
    private Integer quantity;
    private BigDecimal price;
}
