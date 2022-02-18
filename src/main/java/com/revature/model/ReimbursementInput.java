package com.revature.model;

public class ReimbursementInput {

    // get body/data from payload/json object example: { "status": "APPROVED" }, used in the reimbursement controller

    ReimbursementStatus status;

    public ReimbursementStatus getStatus() {
        return status;
    }

    public void setStatus(ReimbursementStatus status) {
        this.status = status;
    }
}
