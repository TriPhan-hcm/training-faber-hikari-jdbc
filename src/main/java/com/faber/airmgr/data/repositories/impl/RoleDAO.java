package com.faber.airmgr.data.repositories.impl;

import com.faber.airmgr.data.entities.RoleEntity;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDAO extends JdbcDaoSupport {

    @Autowired
    HikariDataSource dataSource;

    public RoleEntity extractResultSet(ResultSet resultSet) throws SQLException {
        RoleEntity role = new RoleEntity();
        role.setId(resultSet.getLong("id_role"));
        role.setNameRole(resultSet.getString("name_role"));
        return role;
    }

    public RoleEntity getRoleName(String roleName) {
         String sql = "Select * from role where name_role= ? ;";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, roleName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return extractResultSet(rs);
                }
            }

        } catch (Exception ex) {
            System.out.println("Error:Can't search Role- " + ex);
        }
        return new RoleEntity();
    }
}
