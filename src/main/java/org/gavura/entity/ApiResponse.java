package org.gavura.entity;

import lombok.Data;

@Data
public class ApiResponse {
    Integer code;
    String type;
    String message;
}
