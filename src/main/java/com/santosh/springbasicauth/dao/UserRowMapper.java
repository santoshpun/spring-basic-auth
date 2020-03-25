package com.santosh.springbasicauth.dao;

import com.santosh.springbasicauth.model.UserDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserDetails> {
    @Override
    public UserDetails mapRow(ResultSet rs, int row) throws SQLException {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(rs.getString("username"));
        userDetails.setPassword(rs.getString("password"));
        userDetails.setRole(rs.getString("authority"));
        return userDetails;
    }
}
