package com.example.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sale.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
