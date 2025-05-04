package com.example.sale.service.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sale.model.Customer;
import com.example.sale.model.Product;
import com.example.sale.model.Sale;
import com.example.sale.model.SaleItem;
import com.example.sale.model.requestDTO.SaleItemRequestDTO;
import com.example.sale.model.requestDTO.SaleRequestDTO;
import com.example.sale.model.responseDTO.SaleItemResponseDTO;
import com.example.sale.model.responseDTO.SaleResponseDTO;
import com.example.sale.repository.CustomerRepository;
import com.example.sale.repository.ProductRepository;
import com.example.sale.repository.SaleItemRepository;
import com.example.sale.repository.SaleRepository;
import com.example.sale.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Override
    public SaleResponseDTO createSale(SaleRequestDTO requestDTO) {
        Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setSaleDate(LocalDateTime.now());

        List<SaleItem> items = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (SaleItemRequestDTO itemDTO : requestDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < itemDTO.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - itemDTO.getQuantity());
            productRepository.save(product);

            SaleItem item = new SaleItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(product.getPrice());
            item.setSale(sale);
            items.add(item);

            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity())));
        }

        sale.setItems(items);
        sale.setTotalAmount(totalAmount);
        sale = saleRepository.save(sale);
        saleItemRepository.saveAll(items);

        return convertToDTO(sale);
    }

    @Override
    public SaleResponseDTO getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        return convertToDTO(sale);
    }

    @Override
    public SaleResponseDTO updateSale(Long id, SaleRequestDTO requestDTO) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Xoá các SaleItem cũ
        saleItemRepository.deleteAll(existingSale.getItems());

        List<SaleItem> newItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (SaleItemRequestDTO itemDTO : requestDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < itemDTO.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            // Giảm tồn kho
            product.setStock(product.getStock() - itemDTO.getQuantity());
            productRepository.save(product);

            SaleItem item = new SaleItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(product.getPrice());
            item.setSale(existingSale);
            newItems.add(item);

            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity())));
        }

        existingSale.setCustomer(customer);
        existingSale.setSaleDate(LocalDateTime.now());
        existingSale.setItems(newItems);
        existingSale.setTotalAmount(totalAmount);

        saleItemRepository.saveAll(newItems);
        saleRepository.save(existingSale);

        return convertToDTO(existingSale);
    }

    @Override
    public List<SaleResponseDTO> getAllSales() {
        return saleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSale(Long id) {
        // Tìm kiếm đơn bán
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        // Trả lại số lượng hàng tồn kho
        for (SaleItem item : sale.getItems()) {
            Product product = item.getProduct();
            product.setStock(product.getStock() + item.getQuantity());
            productRepository.save(product);
        }

        // Xoá các SaleItem trước
        saleItemRepository.deleteAll(sale.getItems());

        // Xoá đơn bán
        saleRepository.delete(sale);
    }

    private SaleResponseDTO convertToDTO(Sale sale) {
        SaleResponseDTO dto = new SaleResponseDTO();
        dto.setId(sale.getId());
        dto.setSaleDate(sale.getSaleDate());
        dto.setCustomer(sale.getCustomer());
        dto.setTotalAmount(sale.getTotalAmount());

        List<SaleItemResponseDTO> itemDTOs = sale.getItems().stream().map(item -> {
            SaleItemResponseDTO itemDTO = new SaleItemResponseDTO();
            itemDTO.setId(item.getId());
            itemDTO.setProduct(item.getProduct());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setQuantity(item.getQuantity());
            return itemDTO;
        }).collect(Collectors.toList());

        dto.setItems(itemDTOs);
        return dto;
    }

}
