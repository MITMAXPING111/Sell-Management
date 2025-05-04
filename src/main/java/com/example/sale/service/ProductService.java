package com.example.sale.service;

import java.util.List;

import com.example.sale.model.requestDTO.ProductRequestDTO;
import com.example.sale.model.responseDTO.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO requestDTO);

    ProductResponseDTO getProductById(Long id);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO);

    void deleteProduct(Long id);

}
