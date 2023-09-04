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

//        BankRepository bankRepository = new BankRepositoryImpl();
//        List<Bank> result = bankRepository.getAll();
//        for (Bank bank : result) {
//            System.out.println(bank);
//        }

//        ClientRepository clientRepository = new ClientRepositoryImpl();
//        List<Client> clients = clientRepository.getAll();
//        for (Client client : clients) {
//            System.out.println(client);
//        }
        BillRepository billRepository = new BillRepositoryImpl();
//        List<Bill> re = billRepository.getAll();
//        for (Bill bill : re) {
//            System.out.println(bill);
//        }

//        Bill newbill = billRepository.getByNumber("BY20 OLMP 3135 0000 0010 0000 0934");
//        System.out.println("____________________________________");
//
//        newbill.setBalance(newbill.getBalance() + 500);
//        billRepository.update(newbill);
//        System.out.println(billRepository.getByNumber("BY20 OLMP 3135 0000 0010 0000 0934"));

        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
//        List<Transaction> transactions = transactionRepository.getAll();
//       Transaction t = new Transaction();
//        for (Transaction transaction : transactions) {
//            t = transaction;
//            t.setTotal(500);
//        }

//
//        System.out.println("/////////////////////////////////////");
//        transactionRepository.save(t);

//        MyThread.getThread(1, 5).start();
        Operation o = new Operation();


//        List<Accrual> accruals = o.checkup();

//        for (Accrual a:
//             accruals) {
//            System.out.println(a);
//        }
//        accruals = o.accrual(accruals);
//
//        for (Accrual a:
//                accruals) {
//            System.out.println(a);
//        }
//        o.transfer("BY20 OLMP 3135 0000 0010 0000 0934", "BY20 OLMP 3135 0000 0010 0000 0933",5000);
//        o.refill("BY20 OLMP 3135 0000 0010 0000 0934", 4000);

//        System.out.println("/////////////////////////////////////");

//        Transaction transaction = transactionRepository.getById(13L);

//        Print.check(transaction);


//        for (Transaction transaction : transactionRepository.getAll()) {
//            System.out.println(transaction);
//        }
//        System.out.println("******************************");
//        for (Bill bill : billRepository.getAll()) {
//            System.out.println(bill);
//        }
    }
}