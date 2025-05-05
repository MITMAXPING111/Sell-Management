package com.example.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sale.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}