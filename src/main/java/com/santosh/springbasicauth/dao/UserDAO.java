package com.santosh.springbasicauth.dao;

import com.santosh.springbasicauth.model.UserDetails;

public interface UserDAO {

    UserDetails getUser(String username);
}
