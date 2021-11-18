package DAO;

/**
 * DAOCustomers.java
 *
 * This class loads customer objects from the database
 *
 * @author Brendan Matthews
 */


import Model.Customer;
import ModelObservableList.CustomerList;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;

public class DAOCustomers {

    /**
     * This method grabs all the customers from the database and returns an observable list of customers
     *
     * @throws IOException
     * @returns ObservableList
     */
   public static ObservableList<Customer> getAllCustomers() throws Exception {

        ObservableList<Customer> allCustomer = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * From customers, first_level_divisions, countries WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.COUNTRY_ID = countries.COUNTRY_ID " +
                "ORDER BY Customer_ID ASC ;";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address =  rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String country = rs.getString("Country");
            String state = rs.getString("Division");

            System.out.println(rs);

            CustomerList.addCustomer(new Customer(customerID, customerName, address, postalCode, country, state, phone));
        }

        return allCustomer;
    }
}

