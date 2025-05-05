package com.example.sale.model.requestDTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

import com.example.sale.constants.PermissionEnum;

@Getter
@Setter
public class PermissionRequetsDTO {
    private Integer id;
    @Enumerated(EnumType.STRING)
    private PermissionEnum name;
    private String createBy;
    private LocalDateTime createAt;
    private String updateBy;
    private LocalDateTime updateAt;
}