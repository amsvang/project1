package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;
import java.util.List;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

import java.util.List;

public class ReimbursementServices {


// private ReimbursementDAO rmbDao = new ReimbursementDaoImpl();

// //*************************************************************
// //create

//     public boolean createReimbursement(Reimbursement reimbursement) {


//         return rmbDao.createReimbursement(reimbursement);

//     }

//
private ReimbursementDAO reimbursementDao = new ReimbursementDaoImpl();
// 

// //*************************************************************
// //update

//     public boolean updateReimbursement(Reimbursement reimbursement){
//         return rmbDao.updateReimbursement(reimbursement);

// <<<<<<< niko
//     }

// //*************************************************************
// //delete
//     public boolean deletReimbursement(int id){
//         return rmbDao.deleteReimbursement(id);
//     }

//*************************************************************
//get all

    //Jaffar's method
    /*public boolean createReimbursement(int userId, ReimbursementType rt, ReimbursementStatus rs, double ra, boolean rsub, boolean rr, String des, boolean rrecp) {


    public List<Reimbursement> getAllReimbursements(){
        return rmbDao.getAllReimbursements();
    }

//*************************************************************
//get by ID


    public Reimbursement getReimbursementById(int id){
        return rmbDao.getReimbursementById(id);

        }
    }



        return rd.createReimbursement(reimb);
    }*/



    public boolean createReimbursement(Reimbursement reimbursement) {

        return reimbursementDao.createReimbursement(reimbursement);
    }

    public List<Reimbursement> getAllReimbursements() {
        return reimbursementDao.getAllReimbursements();
    }

    public Reimbursement getReimbursementById(int id) {
        return reimbursementDao.getReimbursementById(id);
    }

    public boolean updateReimbursement(Reimbursement reimbursement) {
        return reimbursementDao.updateReimbursement(reimbursement);
    }

    public boolean deleteReimbursement(int id) {
        return reimbursementDao.deleteReimbursement(id);
    }



}

