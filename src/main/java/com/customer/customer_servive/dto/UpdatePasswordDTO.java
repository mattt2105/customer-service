package com.customer.customer_servive.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordDTO {
    private String oldPassword;
    private String newPassword;
}
