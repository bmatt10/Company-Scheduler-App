package Model;

/**
 * FirstLevelDivision.java
 *
 * @author Brendan Matthews
 */


public class FirstLeveLDivision {
    public int divisionID;
    private String divisionName;
    private int countryID;


    /**
     * A constructor used to create an FLDivision object
     * @param divisionID
     * @param divisionName
     * @param countryID
     */
    public FirstLeveLDivision (int divisionID, String divisionName, int countryID)
    {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;

    }

    /**
     * Getters FirstLevelDivision.
     *
     */

    public int getDivisionID() {
        return divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public int getCountryID() {
        return countryID;
    }

    /**
     * Setters FirstLevelDivision.
     *
     */

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * Fixes combobox selection
     *
     */
    @Override
    public String toString()
    {
        return(divisionName);
    }
}
