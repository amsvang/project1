package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.model.User;

public class AuthService {

    private final UserDao userDao;

    public AuthService(UserDao userDao){
        this.userDao = userDao;
    }

    public boolean loginUser(String username, String password){
        User login = userDao.getUserByUsernameAndPassword(username,password);

        if(login == null || !login.getUsername().equals(username) && !login.getPassword().equals(password)){
            return false;
        }
        return true;
    }


}
