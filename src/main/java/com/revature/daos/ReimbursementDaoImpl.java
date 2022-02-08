package com.revature.daos;

import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
