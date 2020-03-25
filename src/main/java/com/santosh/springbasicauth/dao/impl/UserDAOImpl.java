package com.santosh.springbasicauth.dao.impl;

import com.santosh.springbasicauth.dao.UserDAO;
import com.santosh.springbasicauth.dao.UserRowMapper;
import com.santosh.springbasicauth.model.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails getUser(String username) {
        try {
            final String sql = "select u.username, u.password, ur.authority from users u join authorities ur on(u.username=ur.username) where u.username = ?";
            UserDetails userDetails = jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserRowMapper());
            return userDetails;
        } catch (Exception e) {
            log.error("Exception ", e);
            return null;
        }
    }

}
