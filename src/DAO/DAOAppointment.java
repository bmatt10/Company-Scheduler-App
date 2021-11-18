
package DAO;

/**
 * DAOAppointment.java
 *
 * This class loads appointment objects from the database
 *
 * @author Brendan Matthews
 */

import Model.Appointment;
import ModelObservableList.AppointmentList;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

public class DAOAppointment {

    /**
     * This method grabs all the appointments from the database and returns an observable list of appointments
     *
     * @throws IOException
     * @returns ObservableList
     */
        public static ObservableList<Appointment> getAllAppointments() throws Exception {

            ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
            DBConnection.makeConnection();
            String sqlStatement = "SELECT * FROM  appointments ";
            DBQuery.makeQuery(sqlStatement);
            ResultSet rs = DBQuery.getResult();


            while (rs.next()) {
                int AppointmentID = rs.getInt("Appointment_ID");
                String name = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                Timestamp startTS = rs.getTimestamp("Start");
                Timestamp endTS = rs.getTimestamp("End");
                int contact = rs.getInt("Contact_ID");
                int customerID = rs.getInt("Customer_ID");
                String user = rs.getString("User_ID");


                LocalDateTime startLDT = startTS.toLocalDateTime();
                LocalDateTime endLDT = endTS.toLocalDateTime();

                System.out.println(rs);

                AppointmentList.addAppointment(new Appointment(AppointmentID, name, description, location, type, contact, startLDT, endLDT, customerID, user));
            }

            return allAppointments;
        }

    /**
     * This method grabs all the appointments in the current month from the database and returns an observable list of appointments
     *
     * @throws IOException
     * @returns ObservableList
     */

    public static ObservableList<Appointment> getAllAppointmentsMonth() throws Exception {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM appointments WHERE MONTH(CURDATE()) = MONTH(Start)";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int AppointmentID = rs.getInt("Appointment_ID");
            String name = rs.getString("Title");
            String type = rs.getString("Type");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            Timestamp startTS = rs.getTimestamp("Start");
            Timestamp endTS = rs.getTimestamp("End");
            int contact = rs.getInt("Contact_ID");
            int customerID = rs.getInt("Customer_ID");
            String user = rs.getString("User_ID");

            LocalDateTime startLDT = startTS.toLocalDateTime();
            LocalDateTime endLDT = endTS.toLocalDateTime();

            System.out.println(AppointmentID);

            AppointmentList.addAppointmentMonth(new Appointment(AppointmentID, name, description, location, type, contact, startLDT, endLDT, customerID, user));
        }

        return allAppointments;
    }

    /**
     * This method grabs all the appointments in the current week from the database and returns an observable list of appointments
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<Appointment> getAllAppointmentsWeek() throws Exception {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM appointments WHERE Week(CURDATE()) = week(Start)";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int AppointmentID = rs.getInt("Appointment_ID");
            String name = rs.getString("Title");
            String type = rs.getString("Type");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            Timestamp startTS = rs.getTimestamp("Start");
            Timestamp endTS = rs.getTimestamp("End");
            int contact = rs.getInt("Contact_ID");
            int customerID = rs.getInt("Customer_ID");
            String user = rs.getString("User_ID");

            LocalDateTime startLDT = startTS.toLocalDateTime();
            LocalDateTime endLDT = endTS.toLocalDateTime();

            System.out.println(AppointmentID);

            AppointmentList.addAppointmentWeek(new Appointment(AppointmentID, name, description, location, type, contact, startLDT, endLDT, customerID, user));
        }

        return allAppointments;
    }

    /**
     * This method grabs all the appointments that pertain to Contact with ID = 1 from the database and returns an observable list of appointments
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<Appointment> getAllAppointmentsContactOne() throws Exception {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM appointments WHERE Contact_ID = 1";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int AppointmentID = rs.getInt("Appointment_ID");
            String name = rs.getString("Title");
            String type = rs.getString("Type");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            Timestamp startTS = rs.getTimestamp("Start");
            Timestamp endTS = rs.getTimestamp("End");
            int contact = rs.getInt("Contact_ID");
            int customerID = rs.getInt("Customer_ID");
            String user = rs.getString("User_ID");

            LocalDateTime startLDT = startTS.toLocalDateTime();
            LocalDateTime endLDT = endTS.toLocalDateTime();

            System.out.println(AppointmentID);

            AppointmentList.addAppointmentOne(new Appointment(AppointmentID, name, description, location, type, contact, startLDT, endLDT, customerID, user));
        }

        return allAppointments;
    }

    /**
     * This method grabs all the appointments that pertain to Contact with ID = 2 from the database and returns an observable list of appointments
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<Appointment> getAllAppointmentsContactTwo() throws Exception {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM appointments WHERE Contact_ID = 2";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int AppointmentID = rs.getInt("Appointment_ID");
            String name = rs.getString("Title");
            String type = rs.getString("Type");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            Timestamp startTS = rs.getTimestamp("Start");
            Timestamp endTS = rs.getTimestamp("End");
            int contact = rs.getInt("Contact_ID");
            int customerID = rs.getInt("Customer_ID");
            String user = rs.getString("User_ID");

            LocalDateTime startLDT = startTS.toLocalDateTime();
            LocalDateTime endLDT = endTS.toLocalDateTime();

            System.out.println(AppointmentID);

            AppointmentList.addAppointmentTwo(new Appointment(AppointmentID, name, description, location, type, contact, startLDT, endLDT, customerID, user));
        }

        return allAppointments;
    }

    /**
     * This method grabs all the appointments that pertain to Contact with ID = 3 from the database and returns an observable list of appointments
     *
     * @throws IOException
     * @returns ObservableList
     */

    public static ObservableList<Appointment> getAllAppointmentsContactThree() throws Exception {

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM appointments WHERE Contact_ID = 3";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {
            int AppointmentID = rs.getInt("Appointment_ID");
            String name = rs.getString("Title");
            String type = rs.getString("Type");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            Timestamp startTS = rs.getTimestamp("Start");
            Timestamp endTS = rs.getTimestamp("End");
            int contact = rs.getInt("Contact_ID");
            int customerID = rs.getInt("Customer_ID");
            String user = rs.getString("User_ID");

            LocalDateTime startLDT = startTS.toLocalDateTime();
            LocalDateTime endLDT = endTS.toLocalDateTime();

            System.out.println(AppointmentID);

            AppointmentList.addAppointmentThree(new Appointment(AppointmentID, name, description, location, type, contact, startLDT, endLDT, customerID, user));
        }

        return allAppointments;
    }



    }

