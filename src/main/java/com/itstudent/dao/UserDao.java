package com.itstudent.dao;

import com.itstudent.model.AppUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

  public AppUser findById(int id) throws Exception {
        try {
            String sql = "select * from user where id = ?";
            PreparedStatement ps = prepare(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.first();
            return getObject(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

   public AppUser checkLogin(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            PreparedStatement ps = prepare(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            rs.first();
            return getObject(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

   public List<AppUser> findAll() throws Exception {
        try {
            String sql = "select * from user";
            PreparedStatement ps = prepare(sql);
            ResultSet rs = ps.executeQuery();
            rs.first();
            List<AppUser> data = new ArrayList<>();
            do {
                data.add(getObject(rs));
            } while (rs.next());
            return data;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private AppUser getObject(ResultSet resultSet) throws SQLException {
        AppUser appUser = new AppUser();
        appUser.setId(appUser.getId());
        appUser.setUsername(resultSet.getString("username"));
        appUser.setPassword(resultSet.getString("password"));
        appUser.setName(resultSet.getString("name"));
        return appUser;
    }


    private PreparedStatement prepare(String sql) throws SQLException {
        return ConnectionController.connection
                .prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }
}
