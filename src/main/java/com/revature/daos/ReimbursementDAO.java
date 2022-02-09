package com.revature.daos;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

public interface ReimbursementDAO {

    public boolean addReimbursement(Reimbursement reimbursement);
    public boolean updateReimbursement();
    public boolean deleteReimbursement();
    public ReimbursementStatus status(String status);
    public ReimbursementType type(String type);

    //test change comment
}
