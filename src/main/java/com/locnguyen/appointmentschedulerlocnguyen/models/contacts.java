package com.locnguyen.appointmentschedulerlocnguyen.models;

public class contacts {
    //declare variables
    int contactID;
    String contactName;
    String contactEmail;
    //constructor
    public contacts(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }
    //getters and setters


    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
