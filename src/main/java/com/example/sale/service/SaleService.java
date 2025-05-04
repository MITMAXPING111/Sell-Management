package com.example.sale.service;

import java.util.List;

import com.example.sale.model.requestDTO.SaleRequestDTO;
import com.example.sale.model.responseDTO.SaleResponseDTO;

public interface SaleService {
    SaleResponseDTO createSale(SaleRequestDTO requestDTO);

    SaleResponseDTO getSaleById(Long id);

    List<SaleResponseDTO> getAllSales();

    SaleResponseDTO updateSale(Long id, SaleRequestDTO requestDTO);

    void deleteSale(Long id);

}
