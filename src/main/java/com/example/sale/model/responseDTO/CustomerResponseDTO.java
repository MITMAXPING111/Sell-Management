package com.example.sale.model.responseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
