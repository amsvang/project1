package com.revature.daos;

import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    @Override
    public boolean createUser(User user) {
        String sql = "insert into project1.ers_users (ers_username, ers_password, user_first_name, " +
                "user_last_name, user_email, user_type) values(?, ?, ?, ?, ?, ?::project1.ers_user_roles)";

        try (Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql); ){

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getRole().name());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1){
                return true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "select * from project1.ers_users;";
        List<User> users = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
            Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                User user = new User();

                int id = rs.getInt("id");
                user.setUserId(id);

                String username = rs.getString("ers_username");
                user.setUsername(username);

                String firstName = rs.getString("user_first_name");
                user.setFirstName(firstName);

                String lastName = rs.getString("user_last_name");
                user.setLastName(lastName);

                String email = rs.getString("user_email");
                user.setEmail(email);

                String userRole = rs.getString("user_type");


                users.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUserById(int id) {
        String sql = "select * from project1.ers_users where id = ?;";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                User user = new User();

                user.setUserId(id);

                user.setUsername(rs.getString("ers_username"));
                user.setFirstName(rs.getString("user_first_name"));
                user.setLastName(rs.getString("user_last_name"));
                user.setEmail(rs.getString("user_email"));
                user.setRole(UserRoles.valueOf(rs.getString("user_type")));

                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUsers(User user) {

        String sql = "update project1.ers_users set id = ?, ers_username = ?, ers_password = ?, " +
            "user_first_name = ?, user_last_name = ?, user_email = ?, user_type = ?::project1.ers_user_roles where id = ?;";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getLastName());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getRole().name());
            ps.setInt(8, user.getUserId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from project1.ers_users where ers_username = ? and ers_password = ?;";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();

                user.setUserId(rs.getInt("id"));
                user.setUsername(rs.getString("ers_username"));
                user.setPassword(rs.getString("ers_password"));
                user.setFirstName(rs.getString("user_first_name"));
                user.setLastName(rs.getString("user_last_name"));
                user.setEmail(rs.getString("user_email"));
                user.setRole(UserRoles.valueOf(rs.getString("user_type")));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "delete from project1.ers_users where id = ?; ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}

