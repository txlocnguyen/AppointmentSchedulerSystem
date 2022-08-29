package com.locnguyen.appointmentschedulerlocnguyen.models;

import java.sql.Timestamp;

/***
 *  Public class appointment
 *  @author Loc Nguyen
 */
public class appointment {
    //declare variable
    int apptID;
    String apptTitle;
    String apptDescription;
    String apptLocation;
    String apptType;
    Timestamp apptStart;
    Timestamp apptEnd;
    int apptCustomerID;
    int apptUserID;
    int apptContactID;

    /***
     * Appointment class constructor
     * @param apptID
     * @param apptTitle
     * @param apptDescription
     * @param apptLocation
     * @param apptType
     * @param apptStart
     * @param apptEnd
     * @param apptCustomerID
     * @param apptUserID
     * @param apptContactID
     */
    public appointment(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptType, Timestamp apptStart, Timestamp apptEnd, int apptCustomerID, int apptUserID, int apptContactID) {
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptType = apptType;
        this.apptStart = apptStart;
        this.apptEnd = apptEnd;
        this.apptCustomerID = apptCustomerID;
        this.apptUserID = apptUserID;
        this.apptContactID = apptContactID;
    }
    /***
     * Getter for appointment ID
     * @return apptID
     */
    public int getApptID() {
        return apptID;
    }
    /***
     * Setter for appointment ID
     * @param apptID
     */
    public void setApptID(int apptID) {
        this.apptID = apptID;
    }
    /***
     * Getter for appointment title
     * @return apptTitle
     */
    public String getApptTitle() {
        return apptTitle;
    }
    /***
     * Setter for appointment title
     * @param apptTitle
     */
    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }
    /***
     * Getter for appointment description
     * @return apptDescription
     */
    public String getApptDescription() {
        return apptDescription;
    }
    /***
     * Setter for appointment description
     * @param apptDescription
     */
    public void setApptDescription(String apptDescription) {
        this.apptDescription = apptDescription;
    }
    /***
     * Getter for appointment location
     * @return apptLocation
     */
    public String getApptLocation() {
        return apptLocation;
    }
    /***
     * Setter for appointment location
     * @param apptLocation
     */
    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }
    /***
     * Getter for appointment type
     * @return apptType
     */
    public String getApptType() {
        return apptType;
    }
    /***
     * Setter for appointment type
     * @param apptType
     */
    public void setApptType(String apptType) {
        this.apptType = apptType;
    }
    /***
     * Getter for appointment start time
     * @return apptStart
     */
    public Timestamp getApptStart() {
        return apptStart;
    }
    /***
     * Setter for appointment start time
     * @param apptStart
     */
    public void setApptStart(Timestamp apptStart) {
        this.apptStart = apptStart;
    }
    /***
     * Getter for appointment end time
     * @return apptEnd
     */
    public Timestamp getApptEnd() {
        return apptEnd;
    }
    /***
     * Setter for appointment end time
     * @param apptEnd
     */
    public void setApptEnd(Timestamp apptEnd) {
        this.apptEnd = apptEnd;
    }
    /***
     * Getter for appointment customer ID
     * @return apptCustomerID
     */
    public int getApptCustomerID() {
        return apptCustomerID;
    }
    /***
     * Setter for appointment customer ID
     * @param apptCustomerID
     */
    public void setApptCustomerID(int apptCustomerID) {
        this.apptCustomerID = apptCustomerID;
    }
    /***
     * Getter for appointment user ID
     * @return apptUserID
     */
    public int getApptUserID() {
        return apptUserID;
    }
    /***
     * Setter for appointment user ID
     * @param apptUserID
     */
    public void setApptUserID(int apptUserID) {
        this.apptUserID = apptUserID;
    }
    /***
     * Getter for appointment contact ID
     * @return apptContactID
     */
    public int getApptContactID() {
        return apptContactID;
    }
    /***
     * Setter for appointment contact ID
     * @param apptContactID
     */
    public void setApptContactID(int apptContactID) {
        this.apptContactID = apptContactID;
    }
}
