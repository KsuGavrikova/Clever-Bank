package com.clevertec.cb.repository.impl;

import com.clevertec.cb.model.Bank;
import com.clevertec.cb.repository.BankRepository;
import com.clevertec.cb.util.ConnectionManager;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class BankRepositoryImpl implements BankRepository {

    @Override
    public void save(Bank entity) {

    }

    @Override
    public void update(Bank entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Bank getById(Long id) {
        Bank bank = new Bank();
        try (Connection conn = ConnectionManager.open();
             PreparedStatement pstmt = conn.prepareStatement("Select * from banks WHERE id = ?");
        ) {
            pstmt.setLong(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("bankname");
                bank.setId(id);
                bank.setBankName(name);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bank;

    }

    @Override
    public List<Bank> getAll() {
        List<Bank> bankList = new ArrayList<>();
        try (Connection conn = ConnectionManager.open();
             PreparedStatement pstmt = conn.prepareStatement("Select * from banks");
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("bankname");

                Bank bank = new Bank();
                bank.setId(id);
                bank.setBankName(name);

                bankList.add(bank);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankList;
    }
}
