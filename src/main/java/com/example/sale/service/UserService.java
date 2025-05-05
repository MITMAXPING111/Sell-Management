package com.example.sale.service;

import com.example.sale.model.requestDTO.UserIdRequestDTO;
import com.example.sale.model.requestDTO.UserRequestDTO;
import com.example.sale.util.RestResponse;

public interface UserService {
    RestResponse findAll();

    RestResponse findById(UserIdRequestDTO userIdRequestDTO);

    RestResponse createOrUpdate(UserRequestDTO req);

    RestResponse deleteById(UserIdRequestDTO userId);
}
