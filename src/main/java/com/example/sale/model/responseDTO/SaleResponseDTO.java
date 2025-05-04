package com.example.sale.model.responseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.sale.model.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleResponseDTO {
    private Long id;
    private LocalDateTime saleDate;
    private Customer customer;
    private List<SaleItemResponseDTO> items;
    private BigDecimal totalAmount;
}
