package com.clevertec.cb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends Entity {
    private LocalDateTime dateCurrent;
    private Bill sendBill;
    private Bill recipientBill;
    private String typeTransaction;
    private double total;
    private boolean status;
}
