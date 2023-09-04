package com.clevertec.cb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill extends Entity {
    private Bank bank;
    private long clientId;
    private String currency;
    private LocalDate dateOpen;
    private LocalDate dateClose;
    private String number;
    private double balance;
}
