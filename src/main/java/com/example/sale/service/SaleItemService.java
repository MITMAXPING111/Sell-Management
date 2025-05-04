package com.example.sale.service;

import java.util.List;

import com.example.sale.model.requestDTO.SaleItemRequestDTO;
import com.example.sale.model.responseDTO.SaleItemResponseDTO;

public interface SaleItemService {
    SaleItemResponseDTO createSaleItem(SaleItemRequestDTO requestDTO);

    SaleItemResponseDTO getSaleItemById(Long id);

    List<SaleItemResponseDTO> getAllSaleItems();

    SaleItemResponseDTO updateSaleItem(Long id, SaleItemRequestDTO requestDTO);

    void deleteSaleItem(Long id);

}
