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


//*************************************************************
//update
    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        String sql ="update ERS_REIMBURSEMENT reimb_resolved = ?, reimb_submitted = ?, " +
                "where users_id = ?";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setBoolean(1, reimbursement.isReimbusementResolved());
            ps.setBoolean(2, reimbursement.isReimbusementSubmitted());
            ps.setInt(3,reimbursement.getUser_id());


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
//*************************************************************
//get all
    @Override
    public List<Reimbursement> getAllReimbursements() {
        List <Reimbursement> reimbursements = new ArrayList<>();
        String sql ="Select * from ERS_REIMBURSEMENT";

        try(Connection c = ConnectionUtil.getConnection();
            Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Reimbursement reimb = new Reimbursement();

                int id = rs.getInt("id");
                reimb.setUser_id(id);

                int typeOrdinal = rs.getInt("reimb_type");
                typeOrdinal = typeOrdinal -1; //start index at position 1
                ReimbursementType[] reimbType = ReimbursementType.values();
                reimb.setReimbursementType(reimbType[typeOrdinal]);

                int typeOrdinal2 = rs.getInt("status_type");
                typeOrdinal2 = typeOrdinal2 -1; //start index at position 1
                ReimbursementStatus[] reimbStatus = ReimbursementStatus.values();
                reimb.setReimbursementStatus(reimbStatus[typeOrdinal]);

                double amount = rs.getDouble("reimb_amount");
                reimb.setReimbursementAmount(amount);

                boolean submitted = rs.getBoolean("reimb_submitted");
                reimb.setReimbusementSubmitted(submitted);

                boolean resolved = rs.getBoolean("reimb_resolved");
                reimb.setReimbusementResolved(resolved);

                String description = rs.getString("reimb_description");
                reimb.setDescription(description);

                boolean receipt = rs.getBoolean("reimb_receipt");
                reimb.setReimbursmentReceipt(receipt);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;



    }

    @Override
    public List<Reimbursement> getAllReimbursementsByStatus(Reimbursement reimbursement) {
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