package DAO;
/**
 * DAOFLDivision.java
 *
 * This class loads customer firstleveldivision objects from the database
 *
 * @author Brendan Matthews
 */

import Model.Countries;
import Model.FirstLeveLDivision;
import ModelObservableList.FirstLevelDivisionList;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;

public class DAOFLDivision {

    /**
     * This method grabs all the firstleveldivisions from the database and returns an observable list of firstleveldivisions
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<FirstLeveLDivision> getAllFirstLevelDivisions() throws Exception {

        ObservableList<FirstLeveLDivision> allFirstLevelDivisions = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM  first_level_divisions ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryID =  rs.getInt("COUNTRY_ID");


            System.out.println(rs);

            FirstLevelDivisionList.addFirstLevelDivisions(new FirstLeveLDivision(divisionID, division, countryID));
        }

        return allFirstLevelDivisions;
    }

    /**
     * This method grabs all the firstleveldivisions in the US from the database and returns an observable list of firstleveldivisions
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<FirstLeveLDivision> getAllFirstLevelDivisionsUS() throws Exception {

        ObservableList<FirstLeveLDivision> allFirstLevelDivisions = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM  first_level_divisions WHERE COUNTRY_ID = 1 ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryID =  rs.getInt("COUNTRY_ID");


            System.out.println(rs);

            FirstLevelDivisionList.addFirstLevelDivisionsUS(new FirstLeveLDivision(divisionID, division, countryID));
        }

        return allFirstLevelDivisions;
    }

    /**
     * This method grabs all the firstleveldivisions in the UK from the database and returns an observable list of firstleveldivisions
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<FirstLeveLDivision> getAllFirstLevelDivisionsUK() throws Exception {

        ObservableList<FirstLeveLDivision> allFirstLevelDivisions = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM  first_level_divisions WHERE COUNTRY_ID = 2 ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryID =  rs.getInt("COUNTRY_ID");


            System.out.println(rs);

            FirstLevelDivisionList.addFirstLevelDivisionsUK(new FirstLeveLDivision(divisionID, division, countryID));
        }

        return allFirstLevelDivisions;
    }

    /**
     * This method grabs all the firstleveldivisions in Canada from the database and returns an observable list of firstleveldivisions
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<FirstLeveLDivision> getAllFirstLevelDivisionsCanada() throws Exception {

        ObservableList<FirstLeveLDivision> allFirstLevelDivisions = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM  first_level_divisions WHERE COUNTRY_ID = 3 ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryID =  rs.getInt("COUNTRY_ID");


            System.out.println(rs);

            FirstLevelDivisionList.addFirstLevelDivisionsCanada(new FirstLeveLDivision(divisionID, division, countryID));
        }

        return allFirstLevelDivisions;
    }

    /**
     * This method grabs all the firstleveldivisions from the database and returns a Firstleveldivision based on the selection
     *
     * @throws IOException
     * @returns Firstleveldivision
     */
    public static FirstLeveLDivision getAllFirstLevelDivisions(String FLD) throws Exception {

        FirstLeveLDivision firstLeveLDivision = null;
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM first_level_divisions WHERE Division Like  " + "'"+ FLD + "'" ;
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryID =  rs.getInt("COUNTRY_ID");


            System.out.println(rs);


            firstLeveLDivision = new FirstLeveLDivision(divisionID, division, countryID );
        }

        return firstLeveLDivision;
    }
}

