package com.revature.daos;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDAO {
    @Override
    public boolean addReimbursement(Reimbursement reimbursement) {
        String sql = "insert into ers_reimbursement (users_id, reimb_type,status_type,reimb_amount,reimb_submitted,reimb_resolved, reimb_description,reimb_receipt) " +
                "values(?,CAST(? AS ERS_REIMBURSEMENT_TYPE),CAST(? AS ERS_REIMBURSEMENT_STATUS), ?, ?,? ,?,?)";
        try (Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){

            ps.setInt(1, reimbursement.getUser_id());
            ps.setString(2, reimbursement.getReimbursementType().name());
            ps.setString(3, reimbursement.getReimbursementStatus().name());
            ps.setDouble(4, reimbursement.getReimbursementAmount());
            ps.setBoolean(5, reimbursement.isReimbusementSubmitted());
            ps.setBoolean(6, reimbursement.isReimbusementResolved());
            ps.setString(7, reimbursement.getDescription());
            ps.setBoolean(8, reimbursement.ReimbursmentReceipt());

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


//*****************************************************
//update
    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        String sql ="update ERS_REIMBURSEMENT set id = ?, ers  ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, reimbursement.getReimbursementId());

//            ps.setString(2, reim.getUsername());
//            ps.setString(3, user.getPassword());
//            ps.setString(4, user.getFirstName());
//            ps.setString(5, user.getLastName());
//            ps.setString(6, user.getEmail());
//            ps.setInt(7, user.getType().ordinal());

            //ps.setInt(7, user.getType().ordinal()+1);

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteReimbursement() {
        String sql = "delete from ERS_REIMBURSEMENT where id = ?; ";
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

    @Override
    public List<Reimbursement> getAllReimbursements() {
        return null;
    }

    @Override
    public List<Reimbursement> getAllReimbursementsByStatus(int id) {
        return null;
    }

    @Override
    public List<Reimbursement> getAllReimbursementsByUsernameAndStatus(String username, int id) {
        return null;
    }

    @Override
    public Reimbursement getReimbursementById(int id) {
        return null;
    }

//    @Override
//    public ReimbursementStatus status(String status) {
//        return null;
//    }
//
//    @Override
//    public ReimbursementType type(String type) {
//        return null;
//    }


}
