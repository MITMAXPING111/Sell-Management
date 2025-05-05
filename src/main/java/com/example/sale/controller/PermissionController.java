package com.example.sale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sale.model.requestDTO.PermissionIdRequetsDTO;
import com.example.sale.model.requestDTO.PermissionRequetsDTO;
import com.example.sale.service.PermissionService;
import com.example.sale.util.RestResponse;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @PostMapping("")
    public ResponseEntity<?> createOrUpdate(@RequestBody PermissionRequetsDTO req) {
        RestResponse result = permissionService.createOrUpdate(req);

        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatus()));
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        RestResponse result = permissionService.findAll();

        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatus()));
    }

    @PostMapping("/find-by-id")
    public ResponseEntity<?> getById(@RequestBody PermissionIdRequetsDTO permissionId) {
        RestResponse result = permissionService.findById(permissionId);

        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatus()));
    }

    @PostMapping("/delete-by-id")
    public ResponseEntity<?> deleteById(@RequestBody PermissionIdRequetsDTO permissionId) {
        RestResponse result = permissionService.deleteById(permissionId);

        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatus()));
    }
}
