package com.faber.airmgr.data.repositories.impl;

import com.faber.airmgr.data.entities.FlightEntity;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightRepositoryImpl extends BaseRepositoryImpl {

    @Autowired
    HikariDataSource dataSource;

    //<editor-fold defaultstate="collapsed" desc="FILTER-FLIGHT">
    public List<FlightEntity> filter(Long fromPort, Long toPort, Date departureDate, Date arrivalDate) throws SQLException {
        String query = "SELECT * \n"
                + "FROM flight \n"
                + "WHERE departure_port_id = ? \n"
                + "AND arrival_port_id = ? \n"
                + "AND DAY(departure_time) = DAY(?) \n"
                + "AND (? IS NULL OR DAY(arrival_time) = DAY(?));";
        List<FlightEntity> listSearch = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, fromPort);
            ps.setLong(2, toPort);
            ps.setDate(3, convertUtilToSql(departureDate));
            ps.setDate(4, convertUtilToSql(arrivalDate));
            ps.setDate(5, convertUtilToSql(arrivalDate));
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    listSearch.add(extractResultSet(rs));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error: Can't Search flight- " + ex);
        }
        return listSearch;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="FIND ALL-FLIGHT">
    public List<FlightEntity> findAll() throws SQLException {
        String query = "SELECT * FROM flight;";
        List<FlightEntity> results = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(extractResultSet(rs));

                }
            }

        } catch (Exception ex) {
            System.out.println("Error:Can't search all!- " + ex);
        }
        return results;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="FIND ID - FLIGHT">
    public FlightEntity findById(Long id) throws SQLException {

        String query = "SELECT * FROM flight WHERE id = ?;";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return extractResultSet(rs);
                }
            }

        } catch (Exception ex) {
            System.out.println("Error:Can't search ID- " + ex);
        }
        return new FlightEntity();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SAVE-Flight">
    public void save(FlightEntity entity) {
        String query = "INSERT INTO flight(arrival_time, departure_time, price, arrival_port_id, departure_port_id) \n"
                + "VALUES(?,?,?,?,?);";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);) {
            ps.setDate(1, convertUtilToSql(entity.getArrivalTime()));
            ps.setDate(2, convertUtilToSql(entity.getDepartureTime()));
            ps.setLong(3, entity.getPrice());
            ps.setLong(4, entity.getArrivalPort());
            ps.setLong(5, entity.getDeparturePort());
            entity.setId(new Long(ps.executeUpdate()));

        } catch (SQLException ex) {
            System.err.println("Error!Can't Insert " + ex);
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="UPDATE-FLIGHT">
    public void edit(FlightEntity entity) throws SQLException {
        String query = "UPDATE flight \n"
                + "SET arrival_time= ?, departure_time= ?,price= ?,arrival_port_id= ?,departure_port_id= ? \n"
                + "WHERE id = ? ;";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);) {
            ps.setDate(1, convertUtilToSql(entity.getArrivalTime()));
            ps.setDate(2, convertUtilToSql(entity.getDepartureTime()));
            ps.setLong(3, entity.getPrice());
            ps.setLong(4, entity.getArrivalPort());
            ps.setLong(5, entity.getDeparturePort());
            ps.setLong(6, entity.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error!Can't Edit " + ex);
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="DELETE-FLIGHT">
    public void deleteById(Long id) {
        String query = "DELETE FROM flight WHERE id= ? ;";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error!Can't delete " + ex);
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="BUILDER-FLIGHT">
    public FlightEntity extractResultSet(ResultSet resultSet) throws SQLException {
        FlightEntity flight = new FlightEntity();
        flight.setId(resultSet.getLong("id"));
        flight.setDeparturePort(resultSet.getLong("departure_port_id"));
        flight.setArrivalPort(resultSet.getLong("arrival_port_id"));
        flight.setArrivalTime(resultSet.getDate("arrival_time"));
        flight.setDepartureTime(resultSet.getDate("departure_time"));
        flight.setPrice(resultSet.getLong("price"));
        return flight;
    }
//</editor-fold>

}
