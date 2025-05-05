package com.example.sale.model.responseDTO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.sale.constants.RoleEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDTO {
    private Integer id;
    private RoleEnum name;
    private String createBy;
    private LocalDateTime createAt;
    private String updateBy;
    private LocalDateTime updateAt;
    private Set<PermissionResponseDTO> permissionResponseDTOs = new HashSet<>();
}
