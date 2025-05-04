package com.example.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sale.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Có thể thêm custom query nếu cần
}
