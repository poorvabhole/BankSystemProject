package com.cognologix.banksystem.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "customerId")
    private Integer customerId;

    @NotEmpty(message = "Required field")
    @Column(name = "fullName")
    private String fullName;

    @NotEmpty(message = "Required field")
    @Column(name = "address")
    private String address;

//    @NotNull(message = "Required field")
//    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @NotEmpty(message = "Required field")
    @Column(name = "panNumber")
    private String panNumber;

    @NotEmpty(message = "Required field")
    @Column(name = "aadharNumber")
    private String aadharNumber;

}
