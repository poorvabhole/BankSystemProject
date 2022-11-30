package com.cognologix.banksystem.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
//    UUID uuid = UUID.randomUUID();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "accountId")
    private Integer accountId ;

    @NotNull(message = "Required field")
    @Column(name = "accountNumber")
    private Long accountNumber;

    @Column(name = "accountType")
    @NotEmpty(message = "Required field")
    private String accountType;

    @Column(name = "balance")
    @NotNull(message = "Required field")
    private Double balance;

    @ManyToOne
    private Customer customer;

}
