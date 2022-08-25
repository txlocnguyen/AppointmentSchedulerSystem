package com.locnguyen.appointmentschedulerlocnguyen.models;

public class countries {
    //declare variables
    int countryID;
    String countryName;
    //constructor
    public countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }
    //getters and setters

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
