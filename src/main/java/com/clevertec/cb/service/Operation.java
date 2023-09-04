package com.clevertec.cb.service;

import com.clevertec.cb.model.Accrual;
import com.clevertec.cb.model.Bill;
import com.clevertec.cb.model.Transaction;
import com.clevertec.cb.repository.BillRepository;
import com.clevertec.cb.repository.TransactionRepository;
import com.clevertec.cb.repository.impl.BillRepositoryImpl;
import com.clevertec.cb.repository.impl.TransactionRepositoryImpl;
import com.clevertec.cb.util.Print;
import com.clevertec.cb.util.PropertiesUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Operation {
    public static final TransactionRepository transactionRepository = new TransactionRepositoryImpl();
    public static final BillRepository billRepository = new BillRepositoryImpl();

    public static final List<Accrual> accruals = new ArrayList<>();
    private static final String ACCRUAL_KEY = PropertiesUtil.get("accrualCount");
    private static final String NO_MONEY = "Bill not found!";
    private static final String NO_BILL = "Dismissed. Insufficient funds on the account.";

    public void refill(String number, long amount) {
        Bill billAtm = billRepository.getByNumber("ATM");
        Bill bill = billRepository.getByNumber(number);

        Transaction transaction = new Transaction();

        transaction.setDateCurrent(LocalDateTime.now());
        transaction.setSendBill(billAtm);
        transaction.setRecipientBill(bill);
        transaction.setTypeTransaction("пополнение");
        transaction.setTotal(amount);
        transaction.setStatus(false);

        if (bill != null) {
            bill.setBalance(bill.getBalance() + amount);
            billRepository.update(bill);

            transaction.setStatus(true);
        }
        printDismissed(transactionRepository.saveWithReturn(transaction), NO_BILL);
    }

    public void withdrawal(String number, long amount) {
        Bill bill = billRepository.getByNumber(number);
        Bill billAtm = billRepository.getByNumber("ATM");

        Transaction transaction = new Transaction();

        transaction.setDateCurrent(LocalDateTime.now());
        transaction.setSendBill(bill);
        transaction.setRecipientBill(billAtm);
        transaction.setTypeTransaction("снятие");
        transaction.setTotal(amount);
        transaction.setStatus(false);

        if (bill.getBalance() >= amount) {
            bill.setBalance(bill.getBalance() - amount);
            billRepository.update(bill);
            transaction.setStatus(true);
        }

        printDismissed(transactionRepository.saveWithReturn(transaction), NO_MONEY);
    }

    public void transfer(String numberSend, String numberRecipient, long amount) {

        Bill billSend = billRepository.getByNumber(numberSend);
        Bill billRecipient = billRepository.getByNumber(numberRecipient);

        Transaction transaction = new Transaction();

        transaction.setDateCurrent(LocalDateTime.now());
        transaction.setTypeTransaction("перевод");
        transaction.setStatus(false);
        transaction.setTotal(amount);

        if (billSend.getBalance() >= amount) {
            billSend.setBalance(billSend.getBalance() - amount);
            billRecipient.setBalance(billRecipient.getBalance() + amount);
            transaction.setStatus(true);
        }

        transaction.setSendBill(billSend);
        transaction.setRecipientBill(billRecipient);

        printDismissed(transactionRepository.moneyTransaction(transaction), NO_MONEY);
    }

    private void printDismissed(Transaction transaction, String mess) {
        if (transaction.isStatus()) {
            Print.check(transaction);
        } else System.out.println(mess);
    }


    public static void checkup() {
        for (Transaction t : transactionRepository.getAll()) {
            if (!t.getSendBill().getNumber().equals("ATM")) {
                double accrualCount = t.getTotal() * Float.parseFloat(ACCRUAL_KEY) / 100;
                Accrual accrualCurrent = new Accrual(t.getSendBill(), t.getDateCurrent(), accrualCount);
                accruals.add(accrualCurrent);
            }
        }
    }

    public static void accrual() {
        for (Accrual accrual : accruals) {
            Bill bill = billRepository.getByNumber(accrual.getBill().getNumber());
            bill.setBalance(bill.getBalance() + accrual.getAmount());
            billRepository.update(bill);
        }
        accruals.clear();
    }
}
