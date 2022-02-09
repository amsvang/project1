package com.revature.model;

public class Reimbursement {
    private int reimbursementId;
    private int user_id;
    private ReimbursementType reimbursementType;
    private ReimbursementStatus reimbursementStatus;
    private double reimbursementAmount;
    boolean isReimbursementSubmitted;
    boolean isReimbursementResolved;
    String description;
    boolean reimbursementReceipt;


    public Reimbursement() {
    }

    public Reimbursement(int user_id, ReimbursementType reimbursementType, ReimbursementStatus reimbursementStatus, double reimbursementAmount, boolean isReimbusementSubmitted, boolean isReimbusementResolved, String description, boolean reimbursmentReceipt) {
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
    }

    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public boolean isReimbusementSubmitted() {
        return isReimbursementSubmitted;
    }

    public void setReimbusementSubmitted(boolean reimbusementSubmitted) {
        isReimbursementSubmitted = reimbusementSubmitted;
    }

    public boolean isReimbusementResolved() {
        return isReimbursementResolved;
    }

    public void setReimbusementResolved(boolean reimbusementResolved) {
        isReimbursementResolved = reimbusementResolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean ReimbursmentReceipt() {
        return reimbursementReceipt;
    }

    public void setReimbursmentReceipt(boolean reimbursmentReceipt) {
        this.reimbursementReceipt = reimbursmentReceipt;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", user_id=" + user_id +
                ", reimbursementType=" + reimbursementType +
                ", reimbursementStatus=" + reimbursementStatus +
                ", reimbursementAmount=" + reimbursementAmount +
                ", isReimbusementSubmitted=" + isReimbursementSubmitted +
                ", isReimbusementResolved=" + isReimbursementResolved +
                ", description='" + description + '\'' +
                ", reimbursmentReceipt=" + reimbursementReceipt +
                '}';
    }
}
