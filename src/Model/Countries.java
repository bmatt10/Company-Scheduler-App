package Model;

/**
 * Countries.java
 *
 * @author Brendan Matthews
 */
public class Countries {
    public int countryID;
    public String countryName;

    /**
     * A constructor used to create a Country object
     * @param countryID
     * @param countryName
     */

    public Countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * Getters Country.
     *
     */

    public int getCountryID() { return countryID; }

    public String getCountryName() {
        return countryName;
    }


    /**
     * Setters Country.
     *
     */

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    /**
     * Fixes combobox selection
     *
     */
    @Override
    public String toString()
    {
        return(countryName);
    }
}
