package com.locnguyen.appointmentschedulerlocnguyen.models;
/***
 * Public class users
 * @author Loc Nguyen
 */
public class users {
    //declare variables
    private int userId;
    private String userName;
    /***
     * Public constructor for users
     * @param userId
     * @param userName
     */
    public users(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    /***
     * Getter for userId
     * @return userId
     */
    public int getUserId() {
        return userId;
    }
    /***
     * Setter for userId
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /***
     * Getter for userName
     * @return userName
     */
    public String getUserName() {
        return userName;
    }
    /***
     * Setter for userName
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
