package com.alphabank.model;

public class Employee extends Person {
    private String position;
    private Employee manager;
    private Branch branch;
    private String branch_id; // Use the correct variable name

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void setBranch(String branch) {
        // TODO Auto-generated method stub
    }
    
    // Add getter and setter for branch_id
    public String getBranchId() {
        return branch_id;
    }

    public void setBranchId(String branchId) {
        this.branch_id = branchId;
    }
}
