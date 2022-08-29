package com.locnguyen.appointmentschedulerlocnguyen.models;
/***
 * Public class contacts
 * @author Loc Nguyen
 */
public class contacts {
    //declare variables
    int contactID;
    String contactName;
    String contactEmail;
    /***
     * Constructor for contacts class
     * @param contactID
     * @param contactName
     * @param contactEmail
     */
    public contacts(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }
    /***
     * Getter for contactID
     * @return contactID
     */
    public int getContactID() {
        return contactID;
    }
    /***
     * Setter for contactID
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    /***
     * Getter for contactName
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }
    /***
     * Setter for contactName
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /***
     * Getter for contactEmail
     * @return contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }
    /***
     * Setter for contactEmail
     * @param contactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
