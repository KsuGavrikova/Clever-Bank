package com.clevertec.cb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accrual {
    private Bill bill;
    private LocalDateTime date;
    private double amount;
}
