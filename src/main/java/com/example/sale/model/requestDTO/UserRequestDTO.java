package com.example.sale.model.requestDTO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.sale.constants.GenderEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private boolean enabled = true; // tài khoản bị khóa/mở
    private String phone;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private Set<RoleIdRequestDTO> roleIdRequestDTOs = new HashSet<>();
    private String createBy;
    private LocalDateTime createAt;
    private String updateBy;
    private LocalDateTime updateAt;
}
