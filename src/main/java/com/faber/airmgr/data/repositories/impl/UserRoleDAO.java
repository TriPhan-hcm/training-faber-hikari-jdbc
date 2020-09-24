package com.faber.airmgr.data.repositories.impl;

import com.faber.airmgr.data.entities.RoleEntity;
import com.faber.airmgr.data.entities.UserRole;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRoleDAO {

    @Autowired
    HikariDataSource dataSource;

    public UserRole findByUserRoleByName(String nameUser) {
        String sql = "SE";
        return null;

    }

    public List<RoleEntity> findRoleByUserName(String nameUser) {
        String sql = "select c.id_role, c.name_role from user_role a \n"
                + "join user b on a.id_user = b.id_user \n"
                + "join role c on a.id_role = c.id_role \n"
                + "Where b.name_user = ? ;";
        List<RoleEntity> listRole = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nameUser);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RoleEntity role = new RoleEntity();
                    role.setId(rs.getLong("id_role"));
                    role.setNameRole(rs.getString("name_role"));
                    listRole.add(role);
                }
                
            }

        } catch (Exception ex) {
            System.out.println("Error:Can't search Role- " + ex);
        }
        return listRole;

    }
}
