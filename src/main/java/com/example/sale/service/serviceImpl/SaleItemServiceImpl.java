package com.example.sale.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sale.model.Product;
import com.example.sale.model.Sale;
import com.example.sale.model.SaleItem;
import com.example.sale.model.requestDTO.SaleItemRequestDTO;
import com.example.sale.model.responseDTO.SaleItemResponseDTO;
import com.example.sale.repository.ProductRepository;
import com.example.sale.repository.SaleItemRepository;
import com.example.sale.repository.SaleRepository;
import com.example.sale.service.SaleItemService;

@Service
public class SaleItemServiceImpl implements SaleItemService {
    @Autowired
    private SaleItemRepository saleItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public SaleItemResponseDTO createSaleItem(SaleItemRequestDTO requestDTO) {
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Sale sale = saleRepository.findById(requestDTO.getSaleId())
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        SaleItem item = new SaleItem();
        item.setProduct(product);
        item.setSale(sale);
        item.setQuantity(requestDTO.getQuantity());
        item.setPrice(requestDTO.getPrice());

        SaleItem savedItem = saleItemRepository.save(item);
        return convertToDTO(savedItem);
    }

    @Override
    public SaleItemResponseDTO getSaleItemById(Long id) {
        SaleItem item = saleItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale item not found"));
        return convertToDTO(item);
    }

    @Override
    public List<SaleItemResponseDTO> getAllSaleItems() {
        return saleItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SaleItemResponseDTO updateSaleItem(Long id, SaleItemRequestDTO requestDTO) {
        SaleItem item = saleItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale item not found"));

        item.setQuantity(requestDTO.getQuantity());
        item.setPrice(requestDTO.getPrice());
        return convertToDTO(saleItemRepository.save(item));
    }

    @Override
    public void deleteSaleItem(Long id) {
        if (!saleItemRepository.existsById(id)) {
            throw new RuntimeException("Sale item not found");
        }
        saleItemRepository.deleteById(id);
    }

    private SaleItemResponseDTO convertToDTO(SaleItem item) {
        SaleItemResponseDTO dto = new SaleItemResponseDTO();
        dto.setId(item.getId());
        dto.setProduct(item.getProduct());
        dto.setPrice(item.getPrice());
        dto.setQuantity(item.getQuantity());
        return dto;
    }

}
