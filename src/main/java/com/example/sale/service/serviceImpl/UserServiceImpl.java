package com.example.sale.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.sale.model.User;
import com.example.sale.model.requestDTO.UserIdRequestDTO;
import com.example.sale.model.requestDTO.UserRequestDTO;
import com.example.sale.model.responseDTO.UserResponseDTO;
import com.example.sale.repository.UserRepository;
import com.example.sale.service.UserService;
import com.example.sale.util.RestResponse;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RestResponse findAll() {
        RestResponse restResponse = new RestResponse();

        try {
            List<User> users = userRepository.findAll();
            List<UserRequestDTO> result = new ArrayList<>();

            for (User user : users) {
                UserRequestDTO resUser = modelMapper.map(user, UserRequestDTO.class);
                result.add(resUser);
            }

            restResponse.setMessage("Successfully retrieved all users");
            restResponse.setData(result);
            restResponse.setSuccess(true);
            restResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            restResponse.setMessage("Failed to retrieve users: " + e.getMessage());
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return restResponse;
    }

    @Override
    public RestResponse findById(UserIdRequestDTO reqUserId) {
        RestResponse restResponse = new RestResponse();

        if (reqUserId == null || reqUserId.getId() == null) {
            restResponse.setMessage("Invalid user ID");
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return restResponse;
        }

        try {
            Optional<User> userOptional = userRepository.findById(reqUserId.getId());
            if (userOptional.isEmpty()) {
                restResponse.setMessage("User not found");
                restResponse.setSuccess(false);
                restResponse.setStatus(HttpStatus.NOT_FOUND.value());
                return restResponse;
            }

            UserResponseDTO result = modelMapper.map(userOptional.get(), UserResponseDTO.class);
            restResponse.setMessage("Successfully retrieved user");
            restResponse.setData(result);
            restResponse.setSuccess(true);
            restResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            restResponse.setMessage("Failed to retrieve user: " + e.getMessage());
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return restResponse;
    }

    @Override
    public RestResponse createOrUpdate(UserRequestDTO req) {
        RestResponse restResponse = new RestResponse();
        boolean update = false;

        try {
            if (req.getId() != null && userRepository.existsById(req.getId())) {
                User user = userRepository.findById(req.getId()).orElse(null);
                req.setCreateAt(user.getCreateAt());
                req.setCreateBy(user.getCreateBy());
                req.setUpdateAt(LocalDateTime.now());

                req.setUpdateBy("admin@gmail.com");
                update = true;
            } else {
                req.setCreateAt(LocalDateTime.now());
                req.setCreateBy("admin@gmail.com");
            }

            User user = modelMapper.map(req, User.class);
            userRepository.save(user);
            UserResponseDTO result = modelMapper.map(user, UserResponseDTO.class);

            restResponse.setMessage(update ? "Update user success" : "Create user success");
            restResponse.setData(result);
            restResponse.setSuccess(true);
            restResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            restResponse.setMessage(update ? ("Failed to update users: " + e.getMessage())
                    : ("Failed to create user: " + e.getMessage()));
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }

        return restResponse;
    }

    @Override
    public RestResponse deleteById(UserIdRequestDTO userId) {
        RestResponse restResponse = new RestResponse();

        if (userId == null || userId.getId() == null) {
            restResponse.setMessage("Invalid user ID");
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return restResponse;
        }

        try {
            if (!userRepository.existsById(userId.getId())) {
                restResponse.setMessage("User not found");
                restResponse.setSuccess(false);
                restResponse.setStatus(HttpStatus.NOT_FOUND.value());
                return restResponse;
            }

            userRepository.deleteById(userId.getId());
            restResponse.setMessage("Successfully deleted user");
            restResponse.setSuccess(true);
            restResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            restResponse.setMessage("Failed to delete user: " + e.getMessage());
            restResponse.setSuccess(false);
            restResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return restResponse;
    }

}