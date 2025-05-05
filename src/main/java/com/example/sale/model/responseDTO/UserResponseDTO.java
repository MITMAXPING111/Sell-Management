package com.example.sale.model.responseDTO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.sale.constants.GenderEnum;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserResponseDTO {
    private Integer id;
    private String email;
    private String name;
    private boolean enabled;
    private String phone;
    private GenderEnum gender;
    private String createBy;
    private Set<RoleIdResponseDTO> roleIdResponseDTOs = new HashSet<>();
    private LocalDateTime createAt;
    private String updateBy;
    private LocalDateTime updateAt;

    public UserResponseDTO() {
    }
}
