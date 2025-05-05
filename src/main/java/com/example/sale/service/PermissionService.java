package com.example.sale.service;

import com.example.sale.model.requestDTO.PermissionIdRequetsDTO;
import com.example.sale.model.requestDTO.PermissionRequetsDTO;
import com.example.sale.util.RestResponse;

public interface PermissionService {
    RestResponse findAll();

    RestResponse findById(PermissionIdRequetsDTO permissionId);

    RestResponse createOrUpdate(PermissionRequetsDTO req);

    RestResponse deleteById(PermissionIdRequetsDTO permissionId);
}
