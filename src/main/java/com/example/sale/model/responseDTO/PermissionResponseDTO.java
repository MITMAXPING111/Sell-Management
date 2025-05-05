package com.example.sale.model.responseDTO;

import java.time.LocalDateTime;

import com.example.sale.constants.PermissionEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionResponseDTO {
    private Integer id;
    private PermissionEnum name;
    private String createBy;
    private LocalDateTime createAt;
    private String updateBy;
    private LocalDateTime updateAt;
}