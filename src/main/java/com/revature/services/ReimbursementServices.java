package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

import java.util.List;

public class ReimbursementServices {

   private ReimbursementDAO rmbDao = new ReimbursementDaoImpl();

//*************************************************************
//create

    public boolean createReimbursement(Reimbursement reimbursement) {


        return rmbDao.createReimbursement(reimbursement);

    }


//*************************************************************
//update

    public boolean updateReimbursement(Reimbursement reimbursement){
        return rmbDao.updateReimbursement(reimbursement);

    }

//*************************************************************
//delete
    public boolean deletReimbursement(int id){
        return rmbDao.deleteReimbursement(id);
    }

//*************************************************************
//get all

    public List<Reimbursement> getAllReimbursements(){
        return rmbDao.getAllReimbursements();
    }

//*************************************************************
//get by ID

    public Reimbursement getReimbursementById(int id){
        return rmbDao.getReimbursementById(id);

        }
    }


