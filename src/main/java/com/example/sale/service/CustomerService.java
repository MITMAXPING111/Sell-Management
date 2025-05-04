package com.example.sale.service;

import java.util.List;

import com.example.sale.model.requestDTO.CustomerRequestDTO;
import com.example.sale.model.responseDTO.CustomerResponseDTO;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO);

    CustomerResponseDTO getCustomerById(Long id);

    List<CustomerResponseDTO> getAllCustomers();

    CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO requestDTO);

    void deleteCustomer(Long id);

}
