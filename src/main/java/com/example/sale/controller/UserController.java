package com.example.sale.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sale.model.requestDTO.UserIdRequestDTO;
import com.example.sale.model.requestDTO.UserRequestDTO;
import com.example.sale.service.UserService;
import com.example.sale.util.RestResponse;

@RestController
@RequestMapping("/users")
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("")
    public ResponseEntity<?> createOrUpdate(@RequestBody UserRequestDTO req) {
        String hashPassword = this.passwordEncoder.encode(req.getPassword());
        req.setPassword(hashPassword);
        RestResponse result = userService.createOrUpdate(req);

        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatus()));
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        RestResponse result = userService.findAll();
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatus()));
    }

    @PostMapping("/find-by-id")
    public ResponseEntity<?> getById(@RequestBody UserIdRequestDTO userId) {
        RestResponse result = userService.findById(userId);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatus()));
    }

    @PostMapping("/delete-by-id")
    public ResponseEntity<?> deleteById(@RequestBody UserIdRequestDTO userId) {
        RestResponse result = userService.deleteById(userId);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatus()));
    }

}