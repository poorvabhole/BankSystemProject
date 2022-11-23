package com.cognologix.banksystem.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
//    UUID uuid = UUID.randomUUID();
    private String accountId = UUID.randomUUID().toString();

//    @NotBlank(message = "Required field")
    private String accountType;

//    @NotBlank(message = "Required field")
    private Double balance = 500.00;

//    @NotBlank(message = "Required field")
    private Integer customerId;

}
