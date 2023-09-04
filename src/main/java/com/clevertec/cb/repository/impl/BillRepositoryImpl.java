package com.clevertec.cb.repository.impl;

import com.clevertec.cb.model.Bill;
import com.clevertec.cb.repository.BankRepository;
import com.clevertec.cb.repository.BillRepository;
import com.clevertec.cb.util.ConnectionManager;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class BillRepositoryImpl implements BillRepository {
    private final BankRepository bankRepository = new BankRepositoryImpl();

    @Override
    public void save(Bill entity) {

    }

    @Override
    public void update(Bill entity) {
        String sql = "UPDATE bills SET bankid = ?, clientid = ?, currency = ?, " +
                "balance = ? WHERE number = ?";
//            pstmt.setObject(4, entity.getDateOpen());
//            pstmt.setObject(5, entity.getDateClose());

        try (Connection conn = ConnectionManager.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, entity.getBank().getId());
            pstmt.setLong(2, entity.getClientId());
            pstmt.setString(3, entity.getCurrency());
            pstmt.setDouble(4, entity.getBalance());
            pstmt.setString(5, entity.getNumber());

            int rows = pstmt.executeUpdate();

            System.out.printf("Updated %d rows", rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Bill getById(Long id) {
        return null;
    }

    @Override
    public List<Bill> getAll() {
        List<Bill> billList = new ArrayList<>();
        try (Connection conn = ConnectionManager.open();
             PreparedStatement pstmt = conn.prepareStatement("Select * from bills");
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long bankId = resultSet.getLong("bankid");
                long clientId = resultSet.getLong("clientid");
                String currency = resultSet.getString("currency");
                LocalDate dateOpen = (LocalDate) resultSet.getObject("dateopen");
                LocalDate dateClose = (LocalDate) resultSet.getObject("dateclose");
                String number = resultSet.getString("number");
                long balance = resultSet.getLong("balance");


                Bill bill = new Bill();
                bill.setId(id);
                bill.setBank(bankRepository.getById(bankId));
                bill.setClientId(clientId);
                bill.setCurrency(currency);
                bill.setDateOpen(dateOpen);
                bill.setDateClose(dateClose);
                bill.setNumber(number);
                bill.setBalance(balance);

                billList.add(bill);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billList;
    }

    @Override
    public Bill getByNumber(String number) {
        Bill bill = new Bill();
        if (!number.isBlank()) {
            try (Connection conn = ConnectionManager.open();
                 PreparedStatement pstmt = conn.prepareStatement("Select * from  bills WHERE  number = ?");
            ) {
                pstmt.setString(1, number);
                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    long bankId = resultSet.getLong("bankid");
                    long clientId = resultSet.getLong("clientid");
                    String currency = resultSet.getString("currency");
                    LocalDate dateOpen = resultSet.getObject(5, LocalDate.class);
//                LocalDate dateClose = resultSet.getObject(6, LocalDate.class);
//                LocalDate dateOpen = resultSet.getDate("dateopen").toLocalDate();
                    LocalDate dateClose = (LocalDate) resultSet.getObject("dateclose");
                    long balance = resultSet.getLong("balance");

                    bill.setId(id);
                    bill.setBank(bankRepository.getById(bankId));
                    bill.setClientId(clientId);
                    bill.setCurrency(currency);
                    bill.setDateOpen(dateOpen);
                    bill.setDateClose(dateClose);
                    bill.setNumber(number);
                    bill.setBalance(balance);
                }
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("number bill is blank");
        }
        return bill;
    }
}
