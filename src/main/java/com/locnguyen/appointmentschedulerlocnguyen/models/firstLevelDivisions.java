package com.locnguyen.appointmentschedulerlocnguyen.models;

public class firstLevelDivisions {
    //declare variables
    int divisionID;
    String divisionName;
    int divisionCountryID;
    //constructor
    public firstLevelDivisions(int divisionID, String divisionName, int divisionCountryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.divisionCountryID = divisionCountryID;
    }
    //getters and setters

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getDivisionCountryID() {
        return divisionCountryID;
    }

    public void setDivisionCountryID(int divisionCountryID) {
        this.divisionCountryID = divisionCountryID;
    }
}
