package com.example.sale.util;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> {
    private int status;
    private T data;
    private String message;
    private Object messageError;
    private boolean success;
}
