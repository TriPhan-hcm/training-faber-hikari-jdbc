package com.faber.airmgr.data.repositories.impl;

import com.faber.airmgr.data.entities.FlightEntity;
import com.faber.airmgr.data.entities.OrderEntity;
import com.faber.airmgr.data.enums.TypeFlight;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class OrderRepositoryImpl {

    @Autowired
    private HikariDataSource dataSource;

    protected OrderEntity extractResultSet(ResultSet resultSet) throws SQLException {
        OrderEntity order = new OrderEntity();
        order.setId(resultSet.getLong("id"));
        order.setFlight((FlightEntity) resultSet.getObject("flight_id"));
        order.setAdultsCount(resultSet.getInt("adults_count"));
        order.setChildrenCount(resultSet.getInt("children_count"));
        order.setSeniorsCount(resultSet.getInt("seniors_count"));
        order.setOrderTime(resultSet.getDate("order_time"));
        order.setFlightPrice(resultSet.getLong("flight_price"));
        order.setTotalPrice(resultSet.getLong("total_price"));
        order.setReturnType((TypeFlight) resultSet.getObject("return_type"));
        return order;
    }

    public OrderEntity save(OrderEntity entity) {
        return null;
    }

    public List<OrderEntity> findAll() {
        String query = "SELECT * FROM order;";
        List<OrderEntity> orders = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.add(extractResultSet(rs));

                }
            }

        } catch (Exception ex) {
            System.out.println("Error:Can't search all!- " + ex);
        }
        return orders;
    }
    
}
