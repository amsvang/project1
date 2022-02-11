package com.revature.daos;

import com.revature.model.*;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDAO {



    //*************************************************************
//create
    @Override
    public boolean createReimbursement(Reimbursement reimbursement) {
        String sql = "insert into project1.ers_reimbursement (users_id, reimb_type, status_type, reimb_amount, " +
                "reimb_submitted, reimb_resolved, reimb_description, reimb_receipt) " +
                "values (?, ?::project1.ers_reimbursement_type ,?::project1.ers_reimbursement_status, ?, ?, ?,?,?);";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, reimbursement.getUserId());
            ps.setString(2, reimbursement.getReimbursementType().name());
            ps.setString(3, reimbursement.getReimbursementStatus().name());
            ps.setDouble(4, reimbursement.getReimbursementAmount());
            ps.setBoolean(5, reimbursement.getIsReimbursementSubmitted());
            ps.setBoolean(6, reimbursement.getIsReimbursementResolved());
            ps.setString(7, reimbursement.getDescription());
            ps.setBoolean(8, reimbursement.getReimbursementReceipt());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //*************************************************************
//update
    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        String sql = "update project1.ERS_REIMBURSEMENT SET users_id = ?, reimb_type = ?::project1.ers_reimbursement_type, " +
                "status_type = ?::project1.ers_reimbursement_status, reimb_amount = ?, reimb_resolved = ?, " +
                "reimb_submitted = ?,  reimb_description = ?, reimb_receipt = ? where id = ?;";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, reimbursement.getUserId());
            ps.setString(2, reimbursement.getReimbursementType().name());
            ps.setString(3, reimbursement.getReimbursementStatus().name());
            ps.setDouble(4, reimbursement.getReimbursementAmount());
            ps.setBoolean(5, reimbursement.getIsReimbursementResolved());
            ps.setBoolean(6, reimbursement.getIsReimbursementSubmitted());
            ps.setString(7, reimbursement.getDescription());
            ps.setBoolean(8, reimbursement.getReimbursementReceipt());
            ps.setInt(9, reimbursement.getId());

            System.out.println(reimbursement.getUserId());
            System.out.println(reimbursement.getReimbursementType().name());
            System.out.println(reimbursement.getReimbursementStatus().name());
            System.out.println(reimbursement.getReimbursementAmount());
            System.out.println(reimbursement.getIsReimbursementResolved());
            System.out.println(reimbursement.getIsReimbursementSubmitted());
            System.out.println(reimbursement.getDescription());
            System.out.println(reimbursement.getReimbursementReceipt());



            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    //*************************************************************
//delete
    @Override
    public boolean deleteReimbursement(int id) {
        String sql = "delete from project1.ERS_REIMBURSEMENT where id = ?; ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;



    }
    //*************************************************************
//get all
    @Override
    public List<Reimbursement> getAllReimbursements() {
        List <Reimbursement> reimbursements = new ArrayList<>();
        String sql ="Select * from project1.ERS_REIMBURSEMENT;";

        try(Connection c = ConnectionUtil.getConnection();
            Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Reimbursement reimb = new Reimbursement();

                int id = rs.getInt("id");
                reimb.setId(id);

                int userId = rs.getInt("users_id");
                reimb.setUserId(userId);

                String reimbStatus = rs.getString("status_type");
                reimb.setReimbursementStatus(ReimbursementStatus.valueOf(reimbStatus));

                String reimbType = rs.getString("reimb_type");
                reimb.setReimbursementType(ReimbursementType.valueOf(reimbType));

                double amount = rs.getFloat("reimb_amount");
                reimb.setReimbursementAmount(amount);

                boolean submitted = rs.getBoolean("reimb_submitted");
                reimb.setReimbursementSubmitted(submitted);

                boolean resolved = rs.getBoolean("reimb_resolved");
                reimb.setReimbursementResolved(resolved);

                String description = rs.getString("reimb_description");
                reimb.setDescription(description);

                boolean receipt = rs.getBoolean("reimb_receipt");
                reimb.setReimbursementReceipt(receipt);

                reimbursements.add(reimb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;




    }


    //*************************************************************
    //get all by ID

    @Override
    public Reimbursement getReimbursementById(int id) {
        String sql = "select * from project1.ERS_REIMBURSEMENT where id =?;";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Reimbursement reimb = new Reimbursement();
                reimb.setId(id);

                reimb.setUserId(rs.getInt("users_id"));
                reimb.setReimbursementAmount(rs.getDouble("reimb_amount"));
                reimb.setReimbursementSubmitted(rs.getBoolean("reimb_submitted"));
                reimb.setReimbursementResolved(rs.getBoolean("reimb_resolved"));
                reimb.setDescription(rs.getString("reimb_description"));
                String reimbStatus = rs.getString("status_type");
                reimb.setReimbursementStatus(ReimbursementStatus.valueOf(reimbStatus));
                String reimbType = rs.getString("reimb_type");
                reimb.setReimbursementType(ReimbursementType.valueOf(reimbType));

                return reimb;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
//*************************************************************
//get all by Status
    @Override
    public List<Reimbursement> getReimbursementsByStatus(ReimbursementStatus status) {
        List <Reimbursement> reimbursements = new ArrayList<>();
        String sql ="Select * from project1.ERS_REIMBURSEMENT where status_type = ?::project1.ers_reimbursement_status;";

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, status.name());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimb = new Reimbursement();

                int id = rs.getInt("id");
                reimb.setId(id);

                int user_id = rs.getInt("users_id");
                reimb.setUserId(user_id);

                String reimbStatus = rs.getString("status_type");
                reimb.setReimbursementStatus(ReimbursementStatus.valueOf(reimbStatus));

                String reimbType = rs.getString("reimb_type");
                reimb.setReimbursementType(ReimbursementType.valueOf(reimbType));

                double amount = rs.getDouble("reimb_amount");
                reimb.setReimbursementAmount(amount);

                boolean submitted = rs.getBoolean("reimb_submitted");
                reimb.setReimbursementSubmitted(submitted);

                boolean resolved = rs.getBoolean("reimb_resolved");
                reimb.setReimbursementResolved(resolved);

                String description = rs.getString("reimb_description");
                reimb.setDescription(description);

                boolean receipt = rs.getBoolean("reimb_receipt");
                reimb.setReimbursementReceipt(receipt);

                reimbursements.add(reimb);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;
    }

    public List<Reimbursement> getReimbursementsByStatusAndId(ReimbursementStatus status, int inputId) {
        List <Reimbursement> reimbursements = new ArrayList<>();
        String sql ="Select * from project1.ERS_REIMBURSEMENT where users_id = ? AND status_type = ?::project1.ers_reimbursement_status;";

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, inputId);
            ps.setString(2, status.name());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimb = new Reimbursement();

                int id = rs.getInt("id");
                reimb.setId(id);

                int user_id = rs.getInt("users_id");
                reimb.setUserId(user_id);

                String reimbStatus = rs.getString("status_type");
                reimb.setReimbursementStatus(ReimbursementStatus.valueOf(reimbStatus));

                String reimbType = rs.getString("reimb_type");
                reimb.setReimbursementType(ReimbursementType.valueOf(reimbType));

                double amount = rs.getDouble("reimb_amount");
                reimb.setReimbursementAmount(amount);

                boolean submitted = rs.getBoolean("reimb_submitted");
                reimb.setReimbursementSubmitted(submitted);

                boolean resolved = rs.getBoolean("reimb_resolved");
                reimb.setReimbursementResolved(resolved);

                String description = rs.getString("reimb_description");
                reimb.setDescription(description);

                boolean receipt = rs.getBoolean("reimb_receipt");
                reimb.setReimbursementReceipt(receipt);

                reimbursements.add(reimb);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;
    }

    public List<Reimbursement> getReimbursementsByUserId(int inputUserId) {
        List <Reimbursement> reimbursements = new ArrayList<>();
        String sql ="Select * from project1.ERS_REIMBURSEMENT where users_id = ?;";

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, inputUserId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimb = new Reimbursement();

                int id = rs.getInt("id");
                reimb.setId(id);

                int user_id = rs.getInt("users_id");
                reimb.setUserId(user_id);

                String reimbStatus = rs.getString("status_type");
                reimb.setReimbursementStatus(ReimbursementStatus.valueOf(reimbStatus));

                String reimbType = rs.getString("reimb_type");
                reimb.setReimbursementType(ReimbursementType.valueOf(reimbType));

                double amount = rs.getDouble("reimb_amount");
                reimb.setReimbursementAmount(amount);

                boolean submitted = rs.getBoolean("reimb_submitted");
                reimb.setReimbursementSubmitted(submitted);

                boolean resolved = rs.getBoolean("reimb_resolved");
                reimb.setReimbursementResolved(resolved);

                String description = rs.getString("reimb_description");
                reimb.setDescription(description);

                boolean receipt = rs.getBoolean("reimb_receipt");
                reimb.setReimbursementReceipt(receipt);

                reimbursements.add(reimb);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;
    }










}