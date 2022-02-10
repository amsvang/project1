package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;
import java.util.List;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

import java.util.List;

public class ReimbursementServices {


    private ReimbursementDAO reimbursementDao = new ReimbursementDaoImpl();
<<<<<<< HEAD
=======

>>>>>>> origin/main




    public boolean createReimbursement(Reimbursement reimbursement)
    {return reimbursementDao.createReimbursement(reimbursement);}

    public List<Reimbursement> getAllReimbursements()
    {return reimbursementDao.getAllReimbursements();}

    public List<Reimbursement> getAllReimbursementsByStatus()
    {return reimbursementDao.getReimbursementsByStatus();}

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