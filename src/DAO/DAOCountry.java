package DAO;

/**
 * DAOCountry.java
 *
 * This class loads country objects from the database
 *
 * @author Brendan Matthews
 */

import Model.Countries;
import ModelObservableList.CountryList;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;


public class DAOCountry {

    /**
     * This method grabs all the countries from the database and returns an observable list of countries
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<Countries> getAllCountries() throws Exception {

        ObservableList<Countries> allCountries = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM  countries ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");

            System.out.println(rs);

           CountryList.addCountry(new Countries(countryID, countryName));
        }

        return allCountries;
    }

    /**
     * This method grabs all the countries from the database and returns a Country based on the country selected
     *
     * @throws IOException
     * @returns country
     */
    public static Countries getAllCountries(String CountryName) throws Exception {

        Countries country = null;
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM countries WHERE Country Like  " + "'"+ CountryName + "'" ;
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");

            System.out.println(rs);


            country = new Countries(countryID, countryName);
        }

        return country;
    }
}
