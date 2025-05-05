package com.example.sale.service;

import com.example.sale.model.requestDTO.RoleIdRequestDTO;
import com.example.sale.model.requestDTO.RoleRequestDTO;
import com.example.sale.util.RestResponse;

public interface RoleService {
    RestResponse findAll();

    RestResponse findById(RoleIdRequestDTO roleIdRequestDTO);

    RestResponse createOrUpdate(RoleRequestDTO req);

    RestResponse deleteById(RoleIdRequestDTO roleIdRequestDTO);
}