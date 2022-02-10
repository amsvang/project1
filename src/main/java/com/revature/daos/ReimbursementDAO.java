package com.revature.daos;

import com.revature.model.*;

import java.util.List;

public interface ReimbursementDAO {



    public boolean createReimbursement(Reimbursement reimbursement);
    public boolean updateReimbursement(Reimbursement reimbursement);
    public boolean deleteReimbursement(int id);
    public List<Reimbursement> getAllReimbursements();
    public Reimbursement getReimbursementById(int id);
    public List<Reimbursement> getReimbursementsByStatus();




}