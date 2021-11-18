package DAO;

/**
 * DAOContact.java
 *
 * This class loads contact objects from the database
 *
 * @author Brendan Matthews
 */

import Model.Contact;
import ModelObservableList.ContactList;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;

public class DAOContact {

    /**
     * This method grabs all the contacts from the database and returns an observable list of contacts
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<Contact> getAllContacts() throws Exception {

        ObservableList<Contact> allContact = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM  contacts ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email =  rs.getString("Email");


            System.out.println(rs);

            ContactList.addContact(new Contact(contactID, contactName, email));
        }

        return allContact;
    }

    /**
     * This method grabs all the contacts from the database and returns a Contact based on the selection
     *
     * @throws IOException
     * @returns Contact
     */
    public static Contact getAllContacts(int contactId) throws Exception {

        Contact contact = null;
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM  contacts WHERE Contact_ID =  " + contactId ;
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email =  rs.getString("Email");


            System.out.println(rs);

            contact = new Contact(contactID, contactName, email);
        }

        return contact;
    }
}


