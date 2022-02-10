package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;
import java.util.List;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

public class ReimbursementServices {

    private ReimbursementDAO reimbursementDao = new ReimbursementDaoImpl();



    //Jaffar's method
    /*public boolean createReimbursement(int userId, ReimbursementType rt, ReimbursementStatus rs, double ra, boolean rsub, boolean rr, String des, boolean rrecp) {

        Reimbursement reimb = new Reimbursement(userId, rt, rs, ra, rsub, rr, des, rrecp);

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