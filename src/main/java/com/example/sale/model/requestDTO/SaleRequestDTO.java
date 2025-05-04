package com.example.sale.model.requestDTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleRequestDTO {
    private Long customerId;
    private List<SaleItemRequestDTO> items;
}
