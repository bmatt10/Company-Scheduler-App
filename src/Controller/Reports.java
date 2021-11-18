package Controller;

/**
 * Report.java
 *
 * This class shows the reports controller
 *
 * @author Brendan Matthews
 */

import DAO.DAOAppointment;
import DAO.DAOCount;
import Model.Appointment;
import Model.Contact;
import Model.Count;
import ModelObservableList.AppointmentList;
import ModelObservableList.ContactList;
import ModelObservableList.CountList;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class Reports implements Initializable {
    public Button BackButton;
    public TableView<Appointment> ApptTableView;
    public TableColumn<Appointment, Integer> AppointmentIDColumn;
    public TableColumn<Appointment, String> TilteColumn;
    public TableColumn<Appointment, String> DescriptionColumn;
    public TableColumn<Appointment, String> LocationColumn;
    public TableColumn<Appointment, String> TypeColumn;
    public TableColumn<Appointment, LocalDateTime>  StartDate;
    public TableColumn<Appointment, LocalDateTime>  EndDate;
    public TableColumn<Appointment, Integer> CustomerID;

    public ComboBox<Contact> ReportCB;
    public ComboBox<String> TypeCB;

    public TableColumn<Count,String> CountCol;
    public TableView<Count> CountTableView;
    public ComboBox<String> MonthCB;
    public BarChart<String, Number> barChart;
    public CategoryAxis caDevices;
    public NumberAxis naVisits;
    private Connection connection;

    Stage stage;
    Parent scene;
    String[] mType = {"Planning Session","De-Briefing"};
    String[] xType = {"January","February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    /**
     * Exits to Appointment Menu.
     *
     * @param actionEvent back button action.
     * @throws IOException From FXMLLoader.
     */
    public void BackButtonClicked(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the contact combo box that when selected shows the appointments per contact in the tableview
     *
     * @param actionEvent contact combo box action.
     */

    public void ReportCBClicked(ActionEvent actionEvent) throws Exception {
        if (ReportCB.getValue().getContactName().equals("Anika Costa"))
        {
            AppointmentList.getAllAppointmentsOne().clear();
            DAOAppointment.getAllAppointmentsContactOne();
            ApptTableView.setItems(AppointmentList.getAllAppointmentsOne());
        }

        else if (ReportCB.getValue().getContactName().equals("Daniel Garcia"))
        {
            AppointmentList.getAllAppointmentsTwo().clear();
            DAOAppointment.getAllAppointmentsContactTwo();
            ApptTableView.setItems(AppointmentList.getAllAppointmentsTwo());
        }

        else if (ReportCB.getValue().getContactName().equals("Li Lee"))
        {
            AppointmentList.getAllAppointmentsThree().clear();
            DAOAppointment.getAllAppointmentsContactThree();
            ApptTableView.setItems(AppointmentList.getAllAppointmentsThree());
        }

    }

    /**
     * Initializes the Reports controller
     *
     * @param url
     * @param rb
     */

    public void initialize (URL url, ResourceBundle rb)
    {
        loadChart();

        ReportCB.setItems(ContactList.getAllContacts());
        TypeCB.getItems().setAll(mType);
        MonthCB.getItems().setAll(xType);
        ApptTableView.setItems(AppointmentList.getAllAppointments());

        CountTableView.setItems(CountList.getAllCountsType1());

        AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        TilteColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        StartDate.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndDate.setCellValueFactory(new PropertyValueFactory<>("end"));
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        CountCol.setCellValueFactory(new PropertyValueFactory<>("Count"));

    }

    /**
     * This method loads the bar chart that shows the number of appointments per customer and
     * was a requirement as part of section A3f
     *
     */

    public void loadChart() {

        String sqlStatement = "SELECT Customer_ID , COUNT(*) AS Report FROM appointments GROUP BY Customer_ID ";
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        try {
            DBConnection.makeConnection();
            DBQuery.makeQuery(sqlStatement);
            ResultSet rs = DBQuery.getResult();

            while (rs.next()) {
                String customerID =rs.getString("Customer_ID");
                int report =rs.getInt("Report");
                series.getData().add(new XYChart.Data<>(customerID, report ));

            }
            barChart.getData().add(series);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * This is the type combo box that when selected shows the count per type in the tableview
     *
     * @param actionEvent Type combo box  action.
     */

    public void TypeCBClicked(ActionEvent actionEvent) {

        try  {
            if (TypeCB.getValue().equals("Planning Session")) {
                CountList.getAllCountsType1().clear();
                DAOCount.getAllCount1();
                CountTableView.setItems(CountList.getAllCountsType1());
            }
            else if (TypeCB.getValue().equals("De-Briefing")) {

                CountList.getAllCountsType2().clear();
                DAOCount.getAllCount2();
                CountTableView.setItems(CountList.getAllCountsType2());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This is the month combo box that when selected shows the count per month in the tableview
     *
     * @param actionEvent Month combo box action.
     */
    public void MonthCBClicked(ActionEvent actionEvent) throws Exception {
        if (MonthCB.getValue().equals("January")) {
            CountList.getAllCountsTypeJan().clear();
            DAOCount.getAllCountJan();
            CountTableView.setItems(CountList.getAllCountsTypeJan());
        }
        else if (MonthCB.getValue().equals("February"))
        {
            CountList.getAllCountsTypeFeb().clear();
            DAOCount.getAllCountFeb();
            CountTableView.setItems(CountList.getAllCountsTypeFeb());
        }
        else if (MonthCB.getValue().equals("March"))
        {
            CountList.getAllCountsTypeMar().clear();
            DAOCount.getAllCountMar();
            CountTableView.setItems(CountList.getAllCountsTypeMar());
        }
        else if (MonthCB.getValue().equals("April"))
        {
            CountList.getAllCountsTypeApr().clear();
            DAOCount.getAllCountApr();
            CountTableView.setItems(CountList.getAllCountsTypeApr());
        }
        else if (MonthCB.getValue().equals("May"))
        {
            CountList.getAllCountsTypeMay().clear();
            DAOCount.getAllCountMay();
            CountTableView.setItems(CountList.getAllCountsTypeMay());
        }
        else if (MonthCB.getValue().equals("June"))
        {
            CountList.getAllCountsTypeJun().clear();
            DAOCount.getAllCountJun();
            CountTableView.setItems(CountList.getAllCountsTypeJun());
        }
        else if (MonthCB.getValue().equals("July"))
        {
            CountList.getAllCountsTypeJul().clear();
            DAOCount.getAllCountJul();
            CountTableView.setItems(CountList.getAllCountsTypeJul());
        }
        else if (MonthCB.getValue().equals("August"))
        {
            CountList.getAllCountsTypeAug().clear();
            DAOCount.getAllCountAug();
            CountTableView.setItems(CountList.getAllCountsTypeAug());
        }
        else if (MonthCB.getValue().equals("September"))
        {
            CountList.getAllCountsTypeSep().clear();
            DAOCount.getAllCountSep();
            CountTableView.setItems(CountList.getAllCountsTypeSep());
        }
        else if (MonthCB.getValue().equals("October"))
        {
            CountList.getAllCountsTypeOct().clear();
            DAOCount.getAllCountOct();
            CountTableView.setItems(CountList.getAllCountsTypeOct());
        }
        else if (MonthCB.getValue().equals("November"))
        {
            CountList.getAllCountsTypeNov().clear();
            DAOCount.getAllCountNov();
            CountTableView.setItems(CountList.getAllCountsTypeNov());
        }
        else if (MonthCB.getValue().equals("December"))
        {
            CountList.getAllCountsTypeDec().clear();
            DAOCount.getAllCountDec();
            CountTableView.setItems(CountList.getAllCountsTypeDec());
        }

    }



}
