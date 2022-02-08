package com.revature.daos;

import com.revature.model.User;
import com.revature.model.UserType;

import java.util.List;

public interface UserDao {

    public boolean createUser(User user); //Admin only
    public List<User> getAllUsers(); //Admin only
    public User getUserByType(UserType type);
    public User getUserById(int id);
    public boolean updateUsers(User user);
    public User getUserByUsernameAndPassword(String username, String password);
    public boolean deleteUser(int id);

}