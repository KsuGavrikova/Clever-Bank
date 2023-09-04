package com.clevertec.cb;

import com.clevertec.cb.repository.BillRepository;
import com.clevertec.cb.repository.TransactionRepository;
import com.clevertec.cb.repository.impl.BillRepositoryImpl;
import com.clevertec.cb.repository.impl.TransactionRepositoryImpl;
import com.clevertec.cb.service.Operation;
import com.clevertec.cb.threads.MyThread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThread.getThread();
    }
}
