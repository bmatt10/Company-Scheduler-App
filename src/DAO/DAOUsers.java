package DAO;

/**
 * DAOUsers.java
 *
 * This class loads customer user objects from the database
 *
 * @author Brendan Matthews
 */

import Model.User;
import ModelObservableList.UserList;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;

public class DAOUsers {

    /**
     * This method grabs all the users from the database and returns an observable list of users
     *
     * @throws IOException
     * @returns ObservableList
     */

    public static ObservableList<User> getAllUsers() throws Exception {

        ObservableList<User> allUser = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM  users ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            String UserID = rs.getString("User_ID");
            String UserName = rs.getString("User_Name");
            String Password =  rs.getString("Password");


            System.out.println(rs);

            UserList.addUser(new User(UserID, UserName, Password));
        }

        return allUser;
    }

    /**
     * This method grabs all the users from the database and returns a User based on the selection
     *
     * @throws IOException
     * @returns User
     */
    public static User getAllUsers(String userId) throws Exception {

        User user = null;
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM users WHERE User_ID =  " + userId ;
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            String UserID = rs.getString("User_ID");
            String UserName = rs.getString("User_Name");
            String Password =  rs.getString("Password");


            System.out.println(rs);

            user = new User(UserID, UserName, Password);
        }

        return user;
    }
}
