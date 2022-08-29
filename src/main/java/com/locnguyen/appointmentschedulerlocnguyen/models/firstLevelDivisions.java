package com.locnguyen.appointmentschedulerlocnguyen.models;
/***
 * Public class firstLevelDivisions
 * @author Loc Nguyen
 */
public class firstLevelDivisions {
    //declare variables
    int divisionID;
    String divisionName;
    int divisionCountryID;
    /***
     * Public constructor firstLevelDivisions
     * @param divisionID
     * @param divisionName
     * @param divisionCountryID
     */
    public firstLevelDivisions(int divisionID, String divisionName, int divisionCountryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.divisionCountryID = divisionCountryID;
    }
    /***
     * Getter for divisionID
     * @return divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }
    /***
     * Setter for divisionID
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
    /***
     * Getter for divisionName
     * @return divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }
    /***
     * Setter for divisionName
     * @param divisionName
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    /***
     * Getter for divisionCountryID
     * @return divisionCountryID
     */
    public int getDivisionCountryID() {
        return divisionCountryID;
    }
    /***
     * Setter for divisionCountryID
     * @param divisionCountryID
     */
    public void setDivisionCountryID(int divisionCountryID) {
        this.divisionCountryID = divisionCountryID;
    }
}
