package com.locnguyen.appointmentschedulerlocnguyen.models;
/***
 * Public class customers
 * @author Loc Nguyen
 */
public class customers {
    //declare variables
    int customerID;
    String customerName;
    String customerAddress;
    String customerPostalCode;
    String customerPhone;
    int customerDivisionID;
    /***
     * Public constructor for customers
     * @param customerID
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhone
     * @param customerDivisionID
     */
    public customers(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerDivisionID = customerDivisionID;
    }
    /***
     * Getter for customerID
     * @return customerID
     */
    public int getCustomerID() {
        return customerID;
    }
    /***
     * Setter for customerID
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    /***
     * Getter for customerName
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }
    /***
     * Setter for customerName
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /***
     * Getter for customerAddress
     * @return customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }
    /***
     * Setter for customerAddress
     * @param customerAddress
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    /***
     * Getter for customerPostalCode
     * @return customerPostalCode
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }
    /***
     * Setter for customerPostalCode
     * @param customerPostalCode
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }
    /***
     * Getter for customerPhone
     * @return customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }
    /***
     * Setter for customerPhone
     * @param customerPhone
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    /***
     * Getter for customerDivisionID
     * @return customerDivisionID
     */
    public int getCustomerDivisionID() {
        return customerDivisionID;
    }
    /***
     * Setter for customerDivisionID
     * @param customerDivisionID
     */
    public void setCustomerDivisionID(int customerDivisionID) {
        this.customerDivisionID = customerDivisionID;
    }
}
