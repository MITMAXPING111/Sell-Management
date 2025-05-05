package com.example.sale.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.sale.model.Permission;
import com.example.sale.model.Role;
import com.example.sale.model.requestDTO.PermissionIdRequetsDTO;
import com.example.sale.model.requestDTO.RoleIdRequestDTO;
import com.example.sale.model.requestDTO.RoleRequestDTO;
import com.example.sale.model.responseDTO.RoleResponseDTO;
import com.example.sale.repository.PermissionRepository;
import com.example.sale.repository.RoleRepository;
import com.example.sale.service.RoleService;
import com.example.sale.util.RestResponse;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public RestResponse findAll() {
        RestResponse restResponse = new RestResponse();

        try {
            List<Role> roles = roleRepository.findAll();
            List<RoleResponseDTO> result = new ArrayList<>();

            for (Role r : roles) {
                RoleResponseDTO resRole = modelMapper.map(r, RoleResponseDTO.class);

                result.add(resRole);
            }

            restResponse.setMessage("Find all role success");
            restResponse.setData(result);
            restResponse.setSuccess(true);
            restResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            restResponse.setMessage("Failed to get all role: " + e.getMessage());
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return restResponse;
    }

    @Override
    public RestResponse findById(RoleIdRequestDTO reqRoleId) {
        RestResponse restResponse = new RestResponse();

        try {
            Role role = roleRepository.findById(reqRoleId.getId()).orElse(null);
            RoleResponseDTO result = modelMapper.map(role, RoleResponseDTO.class);

            restResponse.setMessage("Find role success");
            restResponse.setData(result);
            restResponse.setSuccess(true);
            restResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            restResponse.setMessage("Failed to get role: " + e.getMessage());
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return restResponse;
    }

    @Override
    public RestResponse createOrUpdate(RoleRequestDTO req) {
        RestResponse restResponse = new RestResponse();
        boolean update = false;

        try {
            if (req.getId() != null && roleRepository.existsById(req.getId())) {
                Role role = roleRepository.findById(req.getId()).orElse(null);
                req.setCreateAt(role.getCreateAt());
                req.setCreateBy(role.getCreateBy());
                req.setUpdateAt(LocalDateTime.now());
                req.setUpdateBy("admin@gmail.com");
                update = true;
            } else {
                req.setCreateAt(LocalDateTime.now());
                req.setCreateBy("admin@gmail.com");
            }

            Set<PermissionIdRequetsDTO> permissionIds = req.getPermissionIdRequetsDTOs();
            Set<Permission> permissions = new HashSet<>();

            for (PermissionIdRequetsDTO permissionId : permissionIds) {
                Permission permission = permissionRepository.findById(permissionId.getId()).orElse(null);
                if (permission != null)
                    permissions.add(permission);
            }

            Role role = modelMapper.map(req, Role.class);

            role.setPermissions(permissions);

            roleRepository.save(role);
            RoleResponseDTO result = modelMapper.map(role, RoleResponseDTO.class);

            restResponse.setMessage(update ? "Update role success" : "Create role success");
            restResponse.setData(result);
            restResponse.setSuccess(true);
            restResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            restResponse.setMessage(update ? ("Failed to update role: " + e.getMessage())
                    : ("Failed to create role: " + e.getMessage()));
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return restResponse;
    }

    @Override
    public RestResponse deleteById(RoleIdRequestDTO reqRoleId) {
        RestResponse restResponse = new RestResponse();

        try {
            roleRepository.deleteById(reqRoleId.getId());

            restResponse.setMessage("Delete role success");
            restResponse.setSuccess(true);
            restResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            restResponse.setMessage("Failed to delete role: " + e.getMessage());
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return restResponse;
    }

}