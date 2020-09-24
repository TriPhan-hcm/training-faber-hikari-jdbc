package com.faber.airmgr.data.repositories.impl;
import com.faber.airmgr.data.entities.UserEntity;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAO {

    @Autowired
    HikariDataSource dataSource;
    
    public UserEntity extractResultSet(ResultSet resultSet) throws SQLException {
        UserEntity user = new UserEntity();
        user.setId(resultSet.getLong("id_user"));
        user.setNameUser(resultSet.getString("name_user"));
        user.setPassword(resultSet.getString("password"));
        
        return user;
    }

    public UserEntity findUserName(String userName) {
        String sql = "Select * from user where name_user= ? ;";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return extractResultSet(rs);
                }
            }

        } catch (Exception ex) {
            System.out.println("Error:Can't search ID- " + ex);
        }
        return new UserEntity();
    }
}
