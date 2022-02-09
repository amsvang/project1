package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;
import com.revature.model.User;
import java.util.List;

public class UserServices {

    private final UserDao userDao = new UserDaoImpl();


    public boolean createUser(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        return userDao.createUser(user);
    }

    public List<User> getAll() {
        return userDao.getAllUsers();
    }

    public User getById(int id) {
        return userDao.getUserById(id);
    }

    public boolean updateUser(User user) {
        return userDao.updateUsers(user);
    }

    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    public User getUserByUsernameAndPassword(String username, String pass){
        return userDao.getUserByUsernameAndPassword(username,pass);
    }


}
