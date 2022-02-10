package com.revature.daos;

import com.revature.model.*;

import java.util.List;

public interface ReimbursementDAO {



    //public boolean createReimbursement(Reimbursement reimbursement);






    public boolean createReimbursement(Reimbursement reimbursement);
    public boolean updateReimbursement(Reimbursement reimbursement);
    public boolean deleteReimbursement(int id);//id
    public List<Reimbursement> getAllReimbursements();//list everything including status
    public Reimbursement getReimbursementById(int id);
//  public List<Reimbursement> getAllReimbursementsByStatus(Reimbursement reimbursement);
//  public List<Reimbursement> getAllReimbursementsByUsernameAndStatus(String username, int id);



//**************************************************************
//original from Jaffar
//    public boolean addReimbursement(Reimbursement reimbursement);
//    public boolean updateReimbursement();
//    public boolean deleteReimbursement();
//    public ReimbursementStatus status(String status);
//    public ReimbursementType type(String type);

}