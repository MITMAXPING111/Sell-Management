package com.example.sale.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.sale.model.Permission;
import com.example.sale.model.requestDTO.PermissionIdRequetsDTO;
import com.example.sale.model.requestDTO.PermissionRequetsDTO;
import com.example.sale.model.responseDTO.PermissionResponseDTO;
import com.example.sale.repository.PermissionRepository;
import com.example.sale.service.PermissionService;
import com.example.sale.util.RestResponse;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public RestResponse findAll() {
        RestResponse restResponse = new RestResponse();

        try {
            List<Permission> permissions = permissionRepository.findAll();
            List<PermissionResponseDTO> result = new ArrayList<>();

            for (Permission p : permissions) {
                PermissionResponseDTO resPermission = modelMapper.map(p, PermissionResponseDTO.class);

                result.add(resPermission);
            }

            restResponse.setMessage("Find all permission success");
            restResponse.setData(result);
            restResponse.setSuccess(true);
            restResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            restResponse.setMessage("Failed to get all permissions: " + e.getMessage());
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return restResponse;
    }

    @Override
    public RestResponse findById(PermissionIdRequetsDTO permissionId) {
        RestResponse restResponse = new RestResponse();

        try {
            Permission permission = permissionRepository.findById(permissionId.getId()).orElse(null);
            PermissionResponseDTO result = modelMapper.map(permission, PermissionResponseDTO.class);

            restResponse.setMessage("Find permission success");
            restResponse.setData(result);
            restResponse.setSuccess(true);
            restResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            restResponse.setMessage("Failed to get permissions: " + e.getMessage());
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return restResponse;
    }

    @Override
    public RestResponse createOrUpdate(PermissionRequetsDTO req) {
        RestResponse restResponse = new RestResponse();
        boolean update = false;

        try {
            if (req.getId() != null && permissionRepository.existsById(req.getId())) {
                Permission permission = permissionRepository.findById(req.getId()).orElse(null);
                req.setCreateAt(permission.getCreateAt());
                req.setCreateBy(permission.getCreateBy());
                req.setUpdateAt(LocalDateTime.now());
                req.setUpdateBy("admin@gmail.com");
                update = true;
            } else {
                req.setCreateAt(LocalDateTime.now());
                req.setCreateBy("admin@gmail.com");
            }

            Permission permission = modelMapper.map(req, Permission.class);
            permissionRepository.save(permission);
            PermissionResponseDTO result = modelMapper.map(permission, PermissionResponseDTO.class);

            restResponse.setMessage(update ? "Update permission success" : "Create permission success");
            restResponse.setData(result);
            restResponse.setSuccess(true);
            restResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            restResponse.setMessage(update ? ("Failed to update permissions: " + e.getMessage())
                    : ("Failed to create permissions: " + e.getMessage()));
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return restResponse;
    }

    @Override
    public RestResponse deleteById(PermissionIdRequetsDTO permissionId) {
        RestResponse restResponse = new RestResponse();

        try {
            permissionRepository.deleteById(permissionId.getId());

            restResponse.setMessage("Delete permission success");
            restResponse.setSuccess(true);
            restResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            restResponse.setMessage("Failed to delete permissions: " + e.getMessage());
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return restResponse;
    }
}
