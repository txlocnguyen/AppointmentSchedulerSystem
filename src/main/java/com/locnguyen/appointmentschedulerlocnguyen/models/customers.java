package com.locnguyen.appointmentschedulerlocnguyen.models;

public class customers {
    //declare variables
    int customerID;
    String customerName;
    String customerAddress;
    String customerPostalCode;
    String customerPhone;
    int customerDivisionID;
    //constructor
    public customers(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerDivisionID = customerDivisionID;
    }
    //getters and setters

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getCustomerDivisionID() {
        return customerDivisionID;
    }

    public void setCustomerDivisionID(int customerDivisionID) {
        this.customerDivisionID = customerDivisionID;
    }
}
