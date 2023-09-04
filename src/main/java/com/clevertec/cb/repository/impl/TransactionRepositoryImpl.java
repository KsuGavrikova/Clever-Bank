package com.clevertec.cb.repository.impl;

import com.clevertec.cb.model.Bill;
import com.clevertec.cb.model.Transaction;
import com.clevertec.cb.repository.BillRepository;
import com.clevertec.cb.repository.TransactionRepository;
import com.clevertec.cb.util.ConnectionManager;
import com.clevertec.cb.util.IdGenerator;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {
    private final BillRepository billRepository = new BillRepositoryImpl();

    public Transaction moneyTransaction(Transaction entity) {
        String sql = "INSERT INTO transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlBill = "UPDATE bills SET balance = ? WHERE number = ?";
        String sendBill = entity.getSendBill().getNumber();
        String recipientBill = entity.getRecipientBill().getNumber();
        Long id = IdGenerator.getId();

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConnectionManager.open();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.setTimestamp(2, Timestamp.valueOf(entity.getDateCurrent()));
            pstmt.setString(3, sendBill);
            pstmt.setString(4, recipientBill);
            pstmt.setString(5, entity.getTypeTransaction());
            pstmt.setDouble(6, entity.getTotal());
            pstmt.setBoolean(7, entity.isStatus());
            pstmt.executeUpdate();

            Bill send = entity.getSendBill();
            Bill recipient = entity.getRecipientBill();
            pstmt = conn.prepareStatement(sqlBill);
            pstmt.setDouble(1, send.getBalance());
            pstmt.setString(2, send.getNumber());
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(sqlBill);
            pstmt.setDouble(1, recipient.getBalance());
            pstmt.setString(2, recipient.getNumber());
            pstmt.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return getById(id);
    }

    @Override
    public void save(Transaction entity) {

    }

    @Override
    public Transaction saveWithReturn(Transaction entity) {
        String sql = "INSERT INTO transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Long id = IdGenerator.getId();

        try (Connection conn = ConnectionManager.open()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.setTimestamp(2, Timestamp.valueOf(entity.getDateCurrent()));
            pstmt.setString(3, entity.getSendBill().getNumber());
            pstmt.setString(4, entity.getRecipientBill().getNumber());
            pstmt.setString(5, entity.getTypeTransaction());
            pstmt.setDouble(6, entity.getTotal());
            pstmt.setBoolean(7, entity.isStatus());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(id);
    }

    @Override
    public void update(Transaction entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Transaction getById(Long id) {
        Transaction transaction = new Transaction();
        if (id != null) {
            try (Connection conn = ConnectionManager.open();
                 PreparedStatement pstmt = conn.prepareStatement("Select * from transactions WHERE id = ?")
            ) {
                pstmt.setLong(1, id);
                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    LocalDateTime dateCurrent = resultSet.getTimestamp("datecurrent").toLocalDateTime();
                    String sendBill = resultSet.getString("sendbill");
                    String recipientBill = resultSet.getString("recipientbill");
                    String typeTransaction = resultSet.getString("typetransaction");
                    long total = resultSet.getLong("total");
                    boolean status = resultSet.getBoolean("status");

                    transaction.setId(id);
                    transaction.setDateCurrent(dateCurrent);
                    transaction.setSendBill(billRepository.getByNumber(sendBill));
                    transaction.setRecipientBill(billRepository.getByNumber(recipientBill));
                    transaction.setTypeTransaction(typeTransaction);
                    transaction.setTotal(total);
                    transaction.setStatus(status);
                }

            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        } else {
            System.out.println("id is null");
        }
        return transaction;
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactionList = new ArrayList<>();
        try (Connection conn = ConnectionManager.open();
             PreparedStatement pstmt = conn.prepareStatement("Select * from transactions");
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                LocalDateTime dateCurrent = resultSet.getTimestamp("datecurrent").toLocalDateTime();
                String sendBill = resultSet.getString("sendbill");
                String recipientBill = resultSet.getString("recipientbill");
                String typeTransaction = resultSet.getString("typetransaction");
                long total = resultSet.getLong("total");
                boolean status = resultSet.getBoolean("status");

                Transaction transaction = new Transaction();
                transaction.setId(id);
                transaction.setDateCurrent(dateCurrent);
                transaction.setSendBill(billRepository.getByNumber(sendBill));
                transaction.setRecipientBill(billRepository.getByNumber(recipientBill));
                transaction.setTypeTransaction(typeTransaction);
                transaction.setTotal(total);
                transaction.setStatus(status);


                transactionList.add(transaction);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionList;
    }
}
