package com.locnguyen.appointmentschedulerlocnguyen.models;

public class users {
    //declare variables
    private int userId;
    private String userName;
    //constructor
    public users(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    //getters and setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
