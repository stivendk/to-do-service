package com.coderio.to_do_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {

    private boolean succes;
    private T data;
    private String message;
}
