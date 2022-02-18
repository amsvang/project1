package com.revature.model;

public class Reimbursement {
    private int id;
    private int userId;
    private ReimbursementType reimbursementType;
    private ReimbursementStatus reimbursementStatus;
    private double reimbursementAmount;
    private boolean isReimbursementSubmitted;
    private boolean isReimbursementResolved;
    private String description;
    private boolean reimbursementReceipt;


    public Reimbursement() {
    }

    /*public Reimbursement(int user_id, ReimbursementType reimbursementType, ReimbursementStatus reimbursementStatus, double reimbursementAmount, boolean isReimbusementSubmitted, boolean isReimbusementResolved, String description, boolean reimbursmentReceipt) {
        this.user_id = user_id;
        this.reimbursementType = reimbursementType;
        this.reimbursementStatus = reimbursementStatus;
        this.reimbursementAmount = reimbursementAmount;
        this.isReimbursementSubmitted = isReimbusementSubmitted;
        this.isReimbursementResolved = isReimbusementResolved;
        this.description = description;
        this.reimbursementReceipt = reimbursmentReceipt;
    }

    public Reimbursement(int reimbursementId, int user_id, ReimbursementType reimbursementType, ReimbursementStatus reimbursementStatus, double reimbursementAmount, boolean isReimbusementSubmitted, boolean isReimbusementResolved, String description, boolean reimbursmentReceipt) {
        this.reimbursementId = reimbursementId;
        this.user_id = user_id;
        this.reimbursementType = reimbursementType;
        this.reimbursementStatus = reimbursementStatus;
        this.reimbursementAmount = reimbursementAmount;
        this.isReimbursementSubmitted = isReimbusementSubmitted;
        this.isReimbursementResolved = isReimbusementResolved;
        this.description = description;
        this.reimbursementReceipt = reimbursmentReceipt;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ReimbursementType getReimbursementType() {

        return reimbursementType;
    }

    public void setReimbursementType(ReimbursementType reimbursementType) {
        this.reimbursementType = reimbursementType;
    }

    public ReimbursementStatus getReimbursementStatus() {
        return reimbursementStatus;
    }

    public void setReimbursementStatus(ReimbursementStatus reimbursementStatus) {
        this.reimbursementStatus = reimbursementStatus;
    }

    public double getReimbursementAmount() {
        return reimbursementAmount;
    }

    public void setReimbursementAmount(double reimbursementAmount) {
        this.reimbursementAmount = reimbursementAmount;
    }

    public boolean getIsReimbursementSubmitted() {
        return isReimbursementSubmitted;
    }

    public void setReimbursementSubmitted(boolean reimbursementSubmitted) {
        isReimbursementSubmitted = reimbursementSubmitted;
    }

    public boolean getIsReimbursementResolved() {
        return isReimbursementResolved;
    }

    public void setReimbursementResolved(boolean reimbursementResolved) {
        isReimbursementResolved = reimbursementResolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getReimbursementReceipt() {
        return reimbursementReceipt;
    }

    public void setReimbursementReceipt(boolean reimbursementReceipt) {
        this.reimbursementReceipt = reimbursementReceipt;
    }

    /*@Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", user_id=" + userId +
                ", reimbursementType=" + reimbursementType +
                ", reimbursementStatus=" + reimbursementStatus +
                ", reimbursementAmount=" + reimbursementAmount +
                ", isReimbusementSubmitted=" + isReimbursementSubmitted +
                ", isReimbusementResolved=" + isReimbursementResolved +
                ", description='" + description + '\'' +
                ", reimbursmentReceipt=" + reimbursementReceipt +
                '}';
    }*/
}
