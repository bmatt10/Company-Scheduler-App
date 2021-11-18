package Controller;
/**
 * AddAppointment.java
 *
 * This class adds appointments to the application
 *
 * @author Brendan Matthews
 */

import DAO.DAOAppointment;
import Model.*;
import ModelObservableList.*;
import Utils.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Model.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    public Button BackButton;
    public TextField ApptTitleText;
    public TextField ApptDescriptionText;
    public TextField ApptLocationText;
    public ComboBox <Contact>contactCB;
    public Button addButton;
    public DatePicker startDate;
    public TextField StartHourTxt;
    public TextField EndHourTxt;
    public TextField StartMinTxt;
    public TextField EndMinTxt;
    public Label ApptID;
    public TextField CustomerIDText;
    public TextField ApptIDText;
    public DatePicker endDate;
    public ComboBox<User> USerIDCB;
    public ComboBox<String> TypeCB;
    Stage stage;
    Parent scene;
    private static int appointmentIDCount = 0;
    private int appointmentID;
    private String excMess = "";
    String[] mType = {"Planning Session","De-Briefing"};

    /**
     * Exits to Appointment Menu.
     *
     * @param actionEvent back button action.
     * @throws IOException From FXMLLoader.
     */

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Initializes controller and generates the ID
     *
     * @param url
     * @param rb
     */

    public void initialize (URL url, ResourceBundle rb)
    {
        appointmentID = getappointmentIDCount();
        ApptIDText.setText(" " + appointmentID);
        contactCB.setItems(ContactList.getAllContacts());
        USerIDCB.setItems(UserList.getAllUsers());
        TypeCB.getItems().setAll(mType);
    }

    /**
     * This method increments the the ID number
     *
     */
    public static int getappointmentIDCount() {
        appointmentIDCount++;
        return appointmentIDCount + 2;
    }

    /**
     * Adds new appointment
     *
     *
     * @param actionEvent add button action.
     */
    public void addButtonClicked(ActionEvent actionEvent) {

        try {

            String name = ApptTitleText.getText();
            String location = ApptLocationText.getText();
            String description = ApptDescriptionText.getText();
            int contact = contactCB.getSelectionModel().getSelectedItem().getContactID();
            String type = TypeCB.getValue();
            int customerID = Integer.parseInt(CustomerIDText.getText());
            String user = USerIDCB.getSelectionModel().getSelectedItem().getUserID();
            LocalDateTime startLDT = startDate.getValue().atTime
                    (Integer.parseInt(StartHourTxt.getText()),
                            Integer.parseInt(StartMinTxt.getText()));
            LocalDateTime endLDT = endDate.getValue().atTime
                    (Integer.parseInt(EndHourTxt.getText()),
                            Integer.parseInt(EndMinTxt.getText()));

            LocalDateTime createDate = LocalDateTime.now();
            Timestamp createTS = Timestamp.valueOf(createDate);

            try {

                excMess =isValid( startLDT, endLDT, excMess);
                if (excMess.length() > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Adding Appointment");
                    alert.setHeaderText("Error");
                    alert.setContentText(excMess);
                    excMess = "";
                    alert.showAndWait();
                }

                else {

                    Connection conn = DBConnection.getConnection();

                    String sql = "INSERT INTO `WJ05NPM`.`appointments` (`Appointment_ID`, `Title`, `Description`, `Location`, `Type`, `Start`, `End`, `Create_Date`, " +
                            "`Created_By`, `Last_Update`, `Last_Updated_By`, `Customer_ID`, `User_ID`, `Contact_ID`) VALUES " +
                            "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                    PreparedStatement ps = conn.prepareStatement(sql);

                    ps.setInt(1, appointmentID);
                    ps.setString(2, name);
                    ps.setString(3, description);
                    ps.setString(4, location);
                    ps.setString(5, type);
                    ps.setTimestamp(6, Timestamp.valueOf(startLDT));
                    ps.setTimestamp(7, Timestamp.valueOf(endLDT));
                    ps.setTimestamp(8, createTS);
                    ps.setString(9, LoginMenu.lIn);
                    ps.setTimestamp(10, createTS);
                    ps.setString(11, LoginMenu.lIn);
                    ps.setInt(12, customerID);
                    ps.setString(13, user);
                    ps.setInt(14, contact);
                    ps.execute();

                    AppointmentList.addAppointment(
                            new Appointment(appointmentID, name, description, location,
                                    type, contact, startLDT, endLDT, customerID, user));

                    AppointmentList.getAllAppointments().clear();
                    AppointmentList.getAllAppointmentsMonth().clear();
                    AppointmentList.getAllAppointmentsWeek().clear();
                    AppointmentList.getAllAppointmentsOne().clear();
                    AppointmentList.getAllAppointmentsTwo().clear();
                    AppointmentList.getAllAppointmentsThree().clear();
                    CountList.getAllCountsType1().clear();
                    CountList.getAllCountsType2().clear();
                    CountList.getAllCountsTypeJan().clear();
                    CountList.getAllCountsTypeFeb().clear();
                    CountList.getAllCountsTypeMar().clear();
                    CountList.getAllCountsTypeApr().clear();
                    CountList.getAllCountsTypeMay().clear();
                    CountList.getAllCountsTypeJun().clear();
                    CountList.getAllCountsTypeJul().clear();
                    CountList.getAllCountsTypeAug().clear();
                    CountList.getAllCountsTypeSep().clear();
                    CountList.getAllCountsTypeOct().clear();
                    CountList.getAllCountsTypeNov().clear();
                    CountList.getAllCountsTypeDec().clear();

                    try
                    {
                        DAOAppointment.getAllAppointments();
                        DAOAppointment.getAllAppointmentsWeek();
                        DAOAppointment.getAllAppointmentsMonth();

                    }
                    catch (Exception e)
                    {
                        System.out.println("Error!");
                    }

                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentMenu.fxml")));
                    stage.setScene(new Scene(scene));
                    stage.show();

                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Customer ID does not exist");
                alert.setHeaderText("The customerID entered is not available");
                alert.setContentText("Try Again");
                alert.showAndWait();
            }


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Customer ID does not exist");
            alert.setHeaderText("The customerID entered is not available");
            alert.setContentText("Try Again");
            alert.showAndWait();

        }

    }

    /**
     * Displays alerts
     *
     * @param startLDT the start time
     * @param endLDT the end minimum
     * @param exceptionMessage the exceptionMessage
     * @return exceptionMessage the exceptionMessage
     */

    public static String isValid( LocalDateTime startLDT, LocalDateTime endLDT, String exceptionMessage) throws Exception {


        if (startLDT.getHour() < 8 || endLDT.getHour() > 22) {

            exceptionMessage += "Please Make Sure the hours are between 8:00 AM and 10: 00 PM (8:00 and 22:00)";
        }

        for (Appointment a : AppointmentList.getAllAppointments()) {
            if
            (startLDT.equals(a.getStart()) || startLDT.equals(a.getEnd()) ||
                    endLDT.equals(a.getStart()) || endLDT.equals(a.getEnd()) ||
                    ((startLDT.isAfter(a.getStart()) && startLDT.isBefore(a.getEnd())) ||
                            (endLDT.isAfter(a.getStart()) && endLDT.isBefore(a.getEnd())) ||
                            (startLDT.isAfter(a.getStart()) && endLDT.isBefore(a.getEnd())) ||
                            (startLDT.isBefore(a.getStart()) && endLDT.isAfter(a.getEnd())) ||
                            (startLDT.equals(a.getStart()) && endLDT.equals(a.getEnd())) ))  {

                exceptionMessage += "This appointment is in a time conflict with a current appointment.Please Make sure the appointments are not overlapping before saving";

            }
        }

        return exceptionMessage;
    }

}



