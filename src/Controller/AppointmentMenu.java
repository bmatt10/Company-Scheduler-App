package Controller;

/**
 * AppointmentMenu.java
 *
 * This class is the main menu controller the application
 *
 * @author Brendan Matthews
 */
import DAO.DAOAppointment;
import Model.Appointment;
import ModelObservableList.AppointmentList;
import ModelObservableList.CountList;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppointmentMenu implements Initializable {

    public Button AddCustomer;
    public Button viewReportButton;
    public Button AddAppointment;
    public Button ModifyAppointment;
    public Button DeleteAppointment;
    public TableColumn<Appointment, Integer> AppointmentIDColumn;
    public TableColumn<Appointment, String> TilteColumn;
    public TableColumn<Appointment, String> DescriptionColumn;
    public TableColumn<Appointment, String> LocationColumn;
    public TableColumn<Appointment, String> Contactcolumn;
    public TableColumn<Appointment, String> TypeColumn;
    public TableView<Appointment> ApptTableView;
    public TableColumn<Appointment, LocalDateTime> StartDate;
    public TableColumn<Appointment, LocalDateTime> EndDate;
    public TableColumn<Appointment, String> CustomerID;
    public static Connection conn = DBConnection.getConnection();
    public ToggleGroup type;
    public Button Month;
    public Button Week;
    public Button Overall;
    public Button MainPartSearchButton;
    public TextField MainApptSearch;

    Stage stage;
    Parent scene;

    /**
     * Loads the AddCustomer Controller.
     *
     * @param actionEvent Add/modify customer button action.
     * @throws IOException From FXMLLoader.
     */

    public void AddCustomerClicked(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddCustomer.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Loads the Reports Controller.
     *
     * @param actionEvent Report button action.
     * @throws IOException From FXMLLoader.
     */

    public void viewReportButtonClicked(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Reports.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Loads the AddAppointment Controller.
     *
     * @param actionEvent Add button action.
     * @throws IOException From FXMLLoader.
     */

    public void AddAppointmentClicked(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddAppointment.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Deletes the appointment selected from the tableview.
     *
     * Lambda expression below is used public boolean isPresent() method. If no appointment is selected to be deleted then the user is prompted that
     * there is no appointment selected. If there is no value present, then this method returns false, otherwise it returns true. The user must click ok.
     * This is easier to use a lambda expression here than to call out the isPresent() method fully.
     *
     * @param actionEvent Delete button action.
     * @throws SQLException
     * @throws NullPointerException
     */

    public void DeleteAppointmentClicked(ActionEvent actionEvent) throws SQLException, NullPointerException {

        try {
            Appointment appointment = ApptTableView.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(" You are deleting appointment with the id: " + appointment.getId() + " which is of type: " + appointment.getType());
            alert.setContentText("Continue?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                Connection conn = DBConnection.getConnection();
                DBQuery.setStatement(conn);
                Statement statement = DBQuery.getStatement();
                String insertStatement = "DELETE FROM appointments WHERE Appointment_ID = '" + appointment.getId() + "'";
                statement.execute(insertStatement);

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

            }
        }  catch (NullPointerException se) {
            Alert alertSE = new Alert(Alert.AlertType.WARNING);
            alertSE.setTitle("ERROR");
            alertSE.setHeaderText("No appointment was selected to delete");
            alertSE.showAndWait().ifPresent((response -> {
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
    }

    /**
     * Initializes controller
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {

        ApptTableView.setItems(AppointmentList.getAllAppointments());

        AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        TilteColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        StartDate.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndDate.setCellValueFactory(new PropertyValueFactory<>("end"));
        Contactcolumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));

    }

    /**
     * This method generates an alert if there is an appointment that is scheduled within 15 minutes of the current local time
     *
     * @throws SQLException
     */

    public static void FifteenMinuteAlert() throws SQLException {

        String selectStatement = "SELECT * FROM appointments WHERE hour(current_time()) = hour(Start) OR hour(current_time()) = hour(Start)-1  OR hour(current_time()) = hour(Start)+1 ;";
        System.out.println(selectStatement);
        DBQuery.setPreparedStatementSQL(conn,selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        try {
            ps.execute(selectStatement);
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {

                if (LocalDateTime.now().isAfter(rs.getTimestamp("start").toLocalDateTime().minusMinutes(15)) &&
                        LocalDateTime.now().isBefore(rs.getTimestamp("start").toLocalDateTime().plusMinutes(15)) ) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment Schedule");
                    alert.setHeaderText("You have an appointment!");
                    alert.setContentText("You have an appointment ID:" + rs.getInt("Appointment_ID") + " at" + rs.getTimestamp("Start").toLocalDateTime());
                    alert.showAndWait();
                }

                else {

                    System.out.println("Alert");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("Alert");
                    alert.setContentText("You have no current appointments");
                    alert.showAndWait();
                }

            }
            else {

                System.out.println("Alert");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("Alert");
                alert.setContentText("You have no current appointments");
                alert.showAndWait();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Filters appointments in the tableview by current month
     *
     * @param actionEvent Month button action.
     */
    public void MonthViewClicked (ActionEvent actionEvent) {

        ApptTableView.setItems(AppointmentList.getAllAppointmentsMonth());
    }

    /**
     * Shows all appointments
     *
     * @param actionEvent Overall button action.
     */

    public void OverallButtonClicked(ActionEvent actionEvent) {

        ApptTableView.setItems(AppointmentList.getAllAppointments());
    }

    /**
     * Filters appointments in the tableview by current week
     *
     * @param actionEvent Week button action.
     */
    public void WeekViewClicked(ActionEvent actionEvent) {
        ApptTableView.setItems(AppointmentList.getAllAppointmentsWeek());
    }

    /**
     * Loads the UpdateAppointment Controller and carries over the data to fill the appropriate text fields/combo boxes.
     *
     * @param actionEvent Modify button action.
     * @throws IOException From FXMLLoader.
     */

    public void ModifyAppointmentClicked(ActionEvent actionEvent) throws Exception {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateAppointment.fxml"));
            loader.load();

            UpdateAppointment UAMController = loader.getController();
            UAMController.ApptDataCarriedOverMethod(ApptTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (Exception se) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No appointment was selected to modify");
            alert.setContentText("Try Again");
            alert.showAndWait();

        }
    }

    public void searchforAppt(MouseEvent mouseEvent) throws Exception {
        String q = MainApptSearch.getText();

        ObservableList<Appointment> appointments = searchByApptName(q);

        if (appointments.size() == 0){

            try {
                int id = Integer.parseInt(q);
                Appointment a = getApptWithID(id);
                if (a != null)
                    appointments.add(a);
            }
            catch(NumberFormatException e){

            }
        }

        if(appointments.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No part Found");
            alert.setContentText("No part found with name or id "  +q);
            alert.showAndWait();
        } else

            ApptTableView.setItems(appointments);
        MainApptSearch.setText("");
    }

    private ObservableList<Appointment> searchByApptName(String partialName) throws Exception {
        ObservableList<Appointment> namedParts = FXCollections.observableArrayList();

        ObservableList<Appointment> allAppointments = AppointmentList.getAllAppointments();

        for(Appointment  a : allAppointments){
            if(a.getName().contains(partialName)){
                namedParts.add(a);
            }

        }

        return namedParts;
    }

    private Appointment getApptWithID (int id) throws Exception {
        ObservableList<Appointment> allAppointments = AppointmentList.getAllAppointments();

        for (int i = 0; i< allAppointments.size(); i++ ){
            Appointment a = allAppointments.get(i);

            if(a.getId()== id){
                return a;
            }

        }

        return null;
    }
}







