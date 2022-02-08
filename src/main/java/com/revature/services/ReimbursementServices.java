package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

public class ReimbursementServices {

   private ReimbursementDAO rd = new ReimbursementDaoImpl();



    public boolean addReimbursement(int userId, ReimbursementType rt, ReimbursementStatus rs, double ra, boolean rsub, boolean rr, String des, boolean rrecp) {

        Reimbursement reimb = new Reimbursement(userId, rt, rs, ra, rsub, rr, des, rrecp);

       return rd.addReimbursement(reimb);
    }
    //user get reimbursement all by userId
    //update reimbursement status (admin only)
    //

}
