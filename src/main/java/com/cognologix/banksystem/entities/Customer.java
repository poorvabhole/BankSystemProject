package com.cognologix.banksystem.entities;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private Integer customerId;

//    @NotBlank(message = "Required field")
    private String fullName;

//    @NotBlank(message = "Required field")
    private String address;

//    @NotBlank(message = "Required field")
    private String dateOfBirh;

//    @NotBlank(message = "Required field")
    private String panNumber;

//    @NotBlank(message = "Required field")
    private String aadharNumber;

}
