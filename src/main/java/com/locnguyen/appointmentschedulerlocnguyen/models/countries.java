package com.locnguyen.appointmentschedulerlocnguyen.models;
/***
 * Public class countries
 * @author Loc Nguyen
 */
public class countries {
    //declare variables
    int countryID;
    String countryName;
    /***
     * Public constructor for countries
     * @param countryID
     * @param countryName
     */
    public countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }
    /***
     * Getter for countryID
     * @return countryID
     */
    public int getCountryID() {
        return countryID;
    }
    /***
     * Setter for countryID
     * @param countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
    /***
     * Getter for countryName
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }
    /***
     * Setter for countryName
     * @param countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
