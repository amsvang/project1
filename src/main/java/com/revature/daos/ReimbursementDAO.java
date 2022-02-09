package com.revature.daos;

import com.revature.model.*;

import java.util.List;

public interface ReimbursementDAO {

//    public boolean addReimbursement(Reimbursement reimbursement);
//    public boolean updateReimbursement();
//    public boolean deleteReimbursement();
//    public ReimbursementStatus status(String status);
//    public ReimbursementType type(String type);

    //

    public boolean updateReimbursement();
    public boolean deleteReimbursement();
    public ReimbursementStatus status(String status);
    public ReimbursementType type(String type);

    //test change comment

    public boolean updateReimbursement(Reimbursement reimbursement);
    public boolean deleteReimbursement();//id

    //view all
    public List<Reimbursement> getAllReimbursements();//list everything including status
    public List<Reimbursement> getAllReimbursementsByStatus(Reimbursement reimbursement);
    public List<Reimbursement> getAllReimbursementsByUsernameAndStatus(String username, int id);
    public Reimbursement getReimbursementById(int id);

//manager
//    approv/deny pending and resolved


}

