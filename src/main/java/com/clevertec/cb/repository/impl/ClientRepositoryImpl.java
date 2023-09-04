package com.clevertec.cb.repository.impl;

import com.clevertec.cb.model.Client;
import com.clevertec.cb.repository.ClientRepository;
import com.clevertec.cb.util.ConnectionManager;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {
    @Override
    public void save(Client entity) {

    }

    @Override
    public void update(Client entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Client getById(Long id) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clientList = new ArrayList<>();
        try (Connection conn = ConnectionManager.open();
             PreparedStatement pstmt = conn.prepareStatement("Select * from clients");
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String surname = resultSet.getString("surname");

                Client client = new Client();
                client.setId(id);
                client.setFirstName(firstName);
                client.setLastName(lastName);
                client.setSurname(surname);

                clientList.add(client);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientList;
    }
}
