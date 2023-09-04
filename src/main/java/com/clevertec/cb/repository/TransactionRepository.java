package com.clevertec.cb.repository;

import com.clevertec.cb.model.Transaction;

public interface TransactionRepository extends Repository<Transaction> {
    Transaction saveWithReturn(Transaction entity);

    Transaction moneyTransaction(Transaction entity);
}
