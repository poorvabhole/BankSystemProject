package com.cognologix.banksystem.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerDto {
    private String fullName;
    private String address;
    private String dateOfBirth;
}
