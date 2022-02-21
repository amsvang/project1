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

    public boolean createReimbursement(Reimbursement reimbursement)
    {return reimbursementDao.createReimbursement(reimbursement);}

    public List<Reimbursement> getAllReimbursements()
    {return reimbursementDao.getAllReimbursements();}

    public List<Reimbursement> getAllReimbursementsByStatus(ReimbursementStatus status)
    {return reimbursementDao.getReimbursementsByStatus(status);}

    public List<Reimbursement> getAllReimbursementsByStatusAndId(ReimbursementStatus status, int inputId)
    {return reimbursementDao.getReimbursementsByStatusAndId(status, inputId);}

    public List<Reimbursement> getAllReimbursementsByUserId(int inputUserId)
    {return reimbursementDao.getReimbursementsByUserId(inputUserId);}

    public Reimbursement getReimbursementById(int id) {
        return reimbursementDao.getReimbursementById(id);
    }

    public boolean updateReimbursement(Reimbursement reimbursement) {
        return reimbursementDao.updateReimbursement(reimbursement);
    }

    public boolean updateReimbursementStatus(Reimbursement reimbursement) {
        return reimbursementDao.updateReimbursementStatus(reimbursement);
    }

    public boolean deleteReimbursement(int id) {
        return reimbursementDao.deleteReimbursement(id);
    }



}