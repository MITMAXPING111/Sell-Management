package com.example.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sale.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
