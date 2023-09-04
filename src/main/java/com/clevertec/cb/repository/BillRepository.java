package com.clevertec.cb.repository;

import com.clevertec.cb.model.Bill;

public interface BillRepository extends Repository<Bill> {
    Bill getByNumber(String number);
}
