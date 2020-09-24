package com.faber.airmgr.data.repositories.impl;
import com.faber.airmgr.data.entities.AirPortEntity;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirPortRepositoryImpl {

    @Autowired
    HikariDataSource dataSource;

    protected AirPortEntity extractResultSet(ResultSet resultSet) throws SQLException {
        AirPortEntity port = new AirPortEntity();
        port.setId(resultSet.getLong("id"));
        port.setName(resultSet.getString("name"));
        port.setCity(resultSet.getString("city"));
        
        return port;
    }

    public List<AirPortEntity> findAll() throws SQLException {
        String query = "SELECT * FROM air_port;";
        List<AirPortEntity> ports = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);) {

            try (ResultSet resultSet = ps.executeQuery();) {

                while (resultSet.next()) {
                    ports.add(extractResultSet(resultSet));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error: Connection!");
        }
        return ports;
    }

    public List<AirPortEntity> findById(Long id) throws SQLException {
        String query = "SELECT * FROM air_port WHERE id = ? ;";
        List<AirPortEntity> ports = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    ports.add(extractResultSet(resultSet));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error: Connection!");
        }

        return ports;
    }
}
