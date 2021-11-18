package Controller;

/**
 * UpdateAppointment.java
 *
 * This class updates appointments of the application
 *
 * @author Brendan Matthews
 */

import DAO.DAOAppointment;
import DAO.DAOContact;
import DAO.DAOUsers;
import Model.Appointment;
import Model.Contact;
import Model.User;
import ModelObservableList.AppointmentList;
import ModelObservableList.ContactList;
import ModelObservableList.CountList;
import ModelObservableList.UserList;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class UpdateAppointment implements Initializable {

    public Label AptIDLabel;
    public TextField ApptIDText;
    public Label TitleLabel;
    public Label DescriptionLabel;
    public Label LocationLabel;
    public Label ContactLabel;
    public Label TypeLabel;
    public TextField TitleText;
    public TextField DescriptionText;
    public TextField LocationText;
    public ComboBox<Contact> ContactCB;
    public Label DateLabel;
    public Label CustomerIDLabel;
    public Label USerIDLabel;
    public TextField CustomerIDText;
    public Button SaveButton;
    public Button BackButton;
    public DatePicker Date;
    public TextField StartHour;
    public TextField EndHour;
    public TextField EndMinute;
    public TextField StartMinute;
    public Label DateLabel1;
    public DatePicker EndDate;
    public ComboBox<User> UserCB;
    public ComboBox<String> TypeCB;
    private String excMess = "";

    Stage stage;
    Parent scene;
    String[] mType = {"Planning Session","De-Briefing"};
    private Appointment appointmentRef;

    /**
     * Updates appointment
     *
     *
     * @param actionEvent add button action.
     */

    public void SaveButtonClicked(ActionEvent actionEvent) {
        try {

            int appointmentID = Integer.parseInt(ApptIDText.getText());
            String name = TitleText.getText();
            String location = LocationText.getText();
            String description = DescriptionText.getText();
            int contact = ContactCB.getSelectionModel().getSelectedItem().getContactID();
            String type = TypeCB.getValue();
            int customerID = Integer.parseInt(CustomerIDText.getText());
            String user = UserCB.getSelectionModel().getSelectedItem().getUserID();
            LocalDateTime startLDT = Date.getValue().atTime
                    (Integer.parseInt(StartHour.getText()),
                            Integer.parseInt(StartMinute.getText()));
            LocalDateTime endLDT = EndDate.getValue().atTime
                    (Integer.parseInt(EndHour.getText()),
                            Integer.parseInt(EndMinute.getText()));

            LocalDateTime createDate = LocalDateTime.now();
            Timestamp createTS = Timestamp.valueOf(createDate);

            Connection conn = DBConnection.getConnection();
            DBQuery.setStatement(conn);
            Statement statement = DBQuery.getStatement();
            String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = '" + ApptIDText.getText()+"'";
            statement.execute(deleteStatement);
            AppointmentList.getAllAppointments().clear();
            DAOAppointment.getAllAppointments();


            try {


                excMess =isValid( startLDT, endLDT, excMess);
                if (excMess.length() > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Adding Appointment");
                    alert.setHeaderText("Error");
                    alert.setContentText(excMess);
                    excMess = "";
                    alert.showAndWait();
                    BackButton.setVisible(false);

                }

                else {


                    String sql = "INSERT INTO appointments (Appointment_ID,Title,Description,Location,Type,Start,End,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID) Values (?,?,?,?,?,?,?,?,?,?,?,?)";

                    PreparedStatement ps = conn.prepareStatement(sql);

                    ps.setInt(1,appointmentID);
                    ps.setString(2, name);
                    ps.setString(3, description);
                    ps.setString(4, location);
                    ps.setString(5, type);
                    ps.setTimestamp(6, Timestamp.valueOf(startLDT));
                    ps.setTimestamp(7, Timestamp.valueOf(endLDT));
                    ps.setString(8, LoginMenu.lIn);
                    ps.setString(9, LoginMenu.lIn);
                    ps.setInt(10, customerID);
                    ps.setString(11, user);
                    ps.setInt(12, contact);
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

            } catch (SQLException e) {
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
     * Exits to Appointment Menu.
     *
     * Lambda expression here that uses the "ifPresent" method that is used to find out if there is a value present in this Optional instance.
     * If there is no value present, then this method returns false, otherwise it returns true. Using the lambda expression here is easier than
     * writing the whole method.
     *
     * @param actionEvent (response == ButtonType.OK)
     */

    public void BackButtonClicked(ActionEvent actionEvent) throws Exception {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert!");
        alert.setHeaderText("Are you sure you want to cancel?!");
        alert.setContentText("Alert!!");

        alert.showAndWait().ifPresent((response -> {
            if (response == ButtonType.OK) {
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                try {
                    scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentMenu.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }));
    }

    /**
     * Initializes controller
     *
     * @param url
     * @param rb
     */
    public void initialize (URL url, ResourceBundle rb)
    {
        ContactCB.setItems(ContactList.getAllContacts());
        UserCB.setItems(UserList.getAllUsers());
        TypeCB.getItems().setAll(mType);

    }

    /**
     * This method takes the data from the selected appointment in the table view in the AppointmentMenu
     * controller and populates it in the fields
     *
     * @throws IOException
     */

    void ApptDataCarriedOverMethod(Appointment appointment) throws Exception {
        ApptIDText.setText(String.valueOf((appointment).getId()));
        appointmentRef = appointment;
        TitleText.setText(String.valueOf(appointment.getName()));
        LocationText.setText(String.valueOf((appointment).getLocation()));
        DescriptionText.setText(String.valueOf((appointment).getDescription()));
        TypeCB.setValue(appointment.getType());
        ContactCB.setValue(DAOContact.getAllContacts((appointment).getContact()));
        Date.setValue((appointment).getStart().toLocalDate());
        EndDate.setValue((appointment).getEnd().toLocalDate());
        StartHour.setText(String.valueOf((appointment).getStart().getHour()));
        StartMinute.setText(String.valueOf((appointment).getStart().getMinute()));
        EndHour.setText(String.valueOf((appointment).getEnd().getHour()));
        EndMinute.setText(String.valueOf((appointment).getEnd().getMinute()));
        CustomerIDText.setText(String.valueOf(appointment.getCustomerID()));
        UserCB.setValue(DAOUsers.getAllUsers((appointment).getUser()));

    }

    /**
     * Displays alerts
     *
     * @param startLDT the start time
     * @param endLDT the end minimum
     * @param excMess the exceptionMessage
     * @return exceptionMessage the exceptionMessage
     */
    public static String isValid(LocalDateTime startLDT, LocalDateTime endLDT, String excMess) throws Exception {

        if (startLDT.getHour() < 8 || endLDT.getHour() > 22) {

            excMess += "Please Make Sure the hours are between 8:00 AM and 10: 00 PM (8:00 and 22:00)";
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

                excMess += "This appointment is in a time conflict with a current appointment.Please Make sure the appointments are not overlapping before saving";

            }
        }

        return excMess;

    }

}
