package com.locnguyen.appointmentschedulerlocnguyen.models;

import java.sql.Timestamp;

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

    //constructor
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
    //getter and setter

    public int getApptID() {
        return apptID;
    }

    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    public String getApptTitle() {
        return apptTitle;
    }

    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    public String getApptDescription() {
        return apptDescription;
    }

    public void setApptDescription(String apptDescription) {
        this.apptDescription = apptDescription;
    }

    public String getApptLocation() {
        return apptLocation;
    }

    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    public String getApptType() {
        return apptType;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public Timestamp getApptStart() {
        return apptStart;
    }

    public void setApptStart(Timestamp apptStart) {
        this.apptStart = apptStart;
    }

    public Timestamp getApptEnd() {
        return apptEnd;
    }

    public void setApptEnd(Timestamp apptEnd) {
        this.apptEnd = apptEnd;
    }

    public int getApptCustomerID() {
        return apptCustomerID;
    }

    public void setApptCustomerID(int apptCustomerID) {
        this.apptCustomerID = apptCustomerID;
    }

    public int getApptUserID() {
        return apptUserID;
    }

    public void setApptUserID(int apptUserID) {
        this.apptUserID = apptUserID;
    }

    public int getApptContactID() {
        return apptContactID;
    }

    public void setApptContactID(int apptContactID) {
        this.apptContactID = apptContactID;
    }
}
