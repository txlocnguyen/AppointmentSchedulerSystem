package com.locnguyen.appointmentschedulerlocnguyen.models;

import java.sql.Timestamp;

public class appointment {
    int apptID;
    String title;
    String description;
    String location;
    String type;
    Timestamp start;
    Timestamp end;
    int customerID;
    int userID;
    int contactID;


    public appointment(int apptID, String title, String description, String location, String type, Timestamp start, Timestamp end, int customerID, int userID, int contactID) {
        this.apptID = apptID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }
}
