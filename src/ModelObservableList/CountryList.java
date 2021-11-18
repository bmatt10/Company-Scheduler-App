package ModelObservableList;

/**
 * CountryList Class.java
 *
 * This class gets an ObservableList of Countries and then adds them to various methods
 *
 * @author Brendan Matthews
 */

import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountryList {
    private static final ObservableList<Countries> allCountries = FXCollections.observableArrayList();

    /**
     * Getter
     *
     */

    public static ObservableList<Countries> getAllCountries() { return allCountries; }

    /**
     * Adds the list to various methods
     *
     */

    public static void addCountry(Countries newCountry)
    {
        allCountries.add(newCountry);
    }
}
