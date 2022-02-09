package com.revature.daos;

import com.revature.model.*;

import java.util.List;

public interface ReimbursementDAO {

//    public boolean addReimbursement(Reimbursement reimbursement);
//    public boolean updateReimbursement();
//    public boolean deleteReimbursement();
//    public ReimbursementStatus status(String status);
//    public ReimbursementType type(String type);

    public boolean addReimbursement(Reimbursement reimbursement);
    public boolean updateReimbursement(Reimbursement reimbursement);
    public boolean deleteReimbursement();
    public List<Reimbursement> getAllReimbursements();
    public List<Reimbursement> getAllReimbursementsByStatus(int id);
    public List<Reimbursement> getAllReimbursementsByUsernameAndStatus(String username, int id);
    public Reimbursement getReimbursementById(int id);


}

