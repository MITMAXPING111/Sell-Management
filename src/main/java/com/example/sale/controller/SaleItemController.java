package com.example.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sale.model.requestDTO.SaleItemRequestDTO;
import com.example.sale.model.responseDTO.SaleItemResponseDTO;
import com.example.sale.service.SaleItemService;

@RestController
@RequestMapping("/sale-items")
public class SaleItemController {
    @Autowired
    private SaleItemService saleItemService;

    @PostMapping
    public ResponseEntity<SaleItemResponseDTO> create(@RequestBody SaleItemRequestDTO request) {
        return ResponseEntity.ok(saleItemService.createSaleItem(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleItemResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(saleItemService.getSaleItemById(id));
    }

    @GetMapping
    public ResponseEntity<List<SaleItemResponseDTO>> getAll() {
        return ResponseEntity.ok(saleItemService.getAllSaleItems());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleItemResponseDTO> update(@PathVariable Long id, @RequestBody SaleItemRequestDTO request) {
        return ResponseEntity.ok(saleItemService.updateSaleItem(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saleItemService.deleteSaleItem(id);
        return ResponseEntity.noContent().build();
    }

}
