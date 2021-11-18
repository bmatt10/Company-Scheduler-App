package Controller;

/**
 * AddCustomer.java
 *
 * This class adds and modifies customers to the application
 *
 * @author Brendan Matthews
 */
import DAO.*;
import Model.Countries;
import Model.FirstLeveLDivision;
import ModelObservableList.*;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {
    public Button Backbutton;
    public TextField CustomerIDText;
    public TextField NameText;
    public TextField AddressText;
    public TextField PostalCodeText;
    public TextField PhoneText;
    public ComboBox<Countries> CountryCB;
    public ComboBox<FirstLeveLDivision> FLDCB;
    public Button AddCustomerButton;
    private static int customerIDCount = 0;
    public TableView<Model.Customer> CustomerTable;
    public TableColumn<Model.Customer, Integer> CustomerIDColumn;
    public TableColumn<Model.Customer, String> NameColumn;
    public TableColumn<Model.Customer, String> AddressColumn;
    public TableColumn<Model.Customer, String> PostalCodeColumn;
    public TableColumn<Model.Customer, String> PhoneColumn;
    public TableColumn<Model.Customer, String> FirstLevelDivisionColumn;
    public TableColumn<Model.Customer, String> CountryColumn;
    public Button ModifyButton;
    public Button DeleteButton;
    public Button SaveButton;
    public Button CancelButton;
    private int customerID;
    private String excMess = "";

    Stage stage;
    Parent scene;

    /**
     * Exits to Appointment Menu.
     *
     * @param actionEvent back button action.
     * @throws IOException From FXMLLoader.
     */

    public void backbuttonClicked(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Adds new customer
     *
     *
     * @param actionEvent add button action.
     */

    public void AddCustomerButtonClicked(ActionEvent actionEvent) {
        try {

            String name = NameText.getText();
            String address = AddressText.getText();
            String postal = PostalCodeText.getText();
            String phone = PhoneText.getText();
            String country = CountryCB.getSelectionModel().getSelectedItem().getCountryName();
            FirstLeveLDivision FLD = FLDCB.getValue();
            LocalDateTime createDate = LocalDateTime.now();
            Timestamp createTS = Timestamp.valueOf(createDate);

            try {

                Connection conn = DBConnection.getConnection();

                String sql = "INSERT INTO customers (Customer_ID,Customer_Name,Address,Postal_Code,Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                        "Values (?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, customerID);
                ps.setString(2, name);
                ps.setString(3, address);
                ps.setString(4, postal);
                ps.setString(5, phone);
                ps.setTimestamp(6, createTS);
                ps.setString(7, LoginMenu.lIn);
                ps.setTimestamp(8, createTS);
                ps.setString(9, LoginMenu.lIn);
                ps.setInt(10, FLD.getDivisionID());

                ps.execute();
                System.out.println(ps);

                CustomerList.addCustomer(
                        new Model.Customer(customerID, name, address, postal, country, FLD.toString(), phone));


                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddCustomer.fxml")));
                stage.setScene(new Scene(scene));
                stage.show();


            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Left Blank");
            alert.setHeaderText("Please Make Sure All Fields are Filled Out");
            alert.setContentText("Try Again");
            alert.showAndWait();

        }

    }

    /**
     * Initializes controller and generates the ID
     *
     * @param url
     * @param rb
     */

    public void initialize(URL url, ResourceBundle rb) {
        CountryCB.setItems(CountryList.getAllCountries());
        FLDCB.setItems(FirstLevelDivisionList.getAllFirstLevelDivisions());
        customerID = getappointmentIDCount();
        CustomerIDText.setText(" " + customerID);
        SaveButton.setVisible(false);
        CancelButton.setVisible(false);

        CustomerTable.setItems(CustomerList.getAllCustomers());
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        PostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        CountryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        FirstLevelDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

    }

    /**
     * This method increments the the ID number
     *
     */
    public static int getappointmentIDCount() {
        customerIDCount++;
        return customerIDCount + 3;
    }

    /**
     * This method modifies the customer selected from the customer table
     *
     *
     * @param actionEvent modify button action.
     */

    public void ModifyButtonClicked(ActionEvent actionEvent) throws Exception {

        ModifyButton.setVisible(false);
        DeleteButton.setVisible(false);
        SaveButton.setVisible(true);
        AddCustomerButton.setVisible(false);
        CancelButton.setVisible(true);

        Model.Customer customerSelect = CustomerTable.getSelectionModel().getSelectedItem();
        CustomerIDText.setText(String.valueOf((customerSelect).getCustomerID()));
        NameText.setText(String.valueOf((customerSelect).getCustomerName()));
        AddressText.setText(String.valueOf((customerSelect).getAddress()));
        PostalCodeText.setText(String.valueOf((customerSelect).getPostalCode()));
        CountryCB.setValue(DAOCountry.getAllCountries((customerSelect).getCountry()));
        FLDCB.setValue(DAOFLDivision.getAllFirstLevelDivisions((customerSelect).getState()));
        PhoneText.setText(String.valueOf((customerSelect).getPhone()));

    }

    /**
     * Deletes the customer
     *
     *
     * @param actionEvent delete button action.
     */

    public void DeleteButtonClicked(ActionEvent actionEvent) {
        try {
            Model.Customer customer = CustomerTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(" You are deleting customer " + customer.getCustomerID());
            alert.setContentText("Continue?");

            Optional<ButtonType> result = alert.showAndWait();

            try {
                if (result.get() == ButtonType.OK) {
                    Connection conn = DBConnection.getConnection();
                    DBQuery.setStatement(conn);
                    Statement statement = DBQuery.getStatement();
                    String insertStatement = "DELETE FROM WJ05NPM.customers WHERE Customer_ID = '" + customer.getCustomerID() + "'";
                    statement.execute(insertStatement);

                    CustomerList.deleteCustomer(customer);
                    CustomerList.getAllCustomers().clear();
                    Alert alertSE = new Alert(Alert.AlertType.CONFIRMATION);
                    alertSE.setTitle("Alert");
                    alertSE.setHeaderText("Customer " + customer.getCustomerID() + " has been deleted");
                    alertSE.show();

                    try {
                        DAOCustomers.getAllCustomers();
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }

                }
            } catch (SQLException e) {
                Alert alertSE = new Alert(Alert.AlertType.WARNING);
                alertSE.setTitle("ERROR");
                alertSE.setHeaderText("Please delete appointments for customer " + customer.getCustomerID() + " first");
                alertSE.show();
            }
        } catch (NullPointerException se) {
            Alert alertSE = new Alert(Alert.AlertType.WARNING);
            alertSE.setTitle("ERROR");
            alertSE.setHeaderText("No customer was selected to delete");
            alertSE.show();
        }
    }

    /**
     * Cancels the modification of the selected customer.
     *
     * Same lambda function explained in the "UpdateAppointment" controller.
     * @param actionEvent cancel button action.
     */
    public void CancelButtonClicked(ActionEvent actionEvent)  {
        // Lambda expression in this method

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
     * This method filters the FirstLevelDivision items based on the country selected in the combo box
     *
     *
     * @param actionEvent combo box selection action.
     */

    public void CountryCBClicked(ActionEvent actionEvent) throws Exception {
        if (CountryCB.getValue().getCountryID() == 1)
        {
             DAOFLDivision.getAllFirstLevelDivisionsUS();
             FLDCB.setItems(FirstLevelDivisionList.getAllFirstLevelDivisionsUS());
        }

        else if  (CountryCB.getValue().getCountryID() == 2)
        {
            DAOFLDivision.getAllFirstLevelDivisionsUK();
            FLDCB.setItems(FirstLevelDivisionList.getAllFirstLevelDivisionsUK());
        }

        else if  (CountryCB.getValue().getCountryID() == 3)
        {
            DAOFLDivision.getAllFirstLevelDivisionsCanada();
            FLDCB.setItems(FirstLevelDivisionList.getAllFirstLevelDivisionsCanada());
        }

    }

    /**
     * Adds new customer and adds it to the tableview
     *
     *
     * @param actionEvent save button action.
     */

    public void SaveButtonClicked(ActionEvent actionEvent) {

        try {

            String name = NameText.getText();
            String address = AddressText.getText();
            String postal = PostalCodeText.getText();
            String phone = PhoneText.getText();
            String country = CountryCB.getSelectionModel().getSelectedItem().getCountryName();
            FirstLeveLDivision FLD = FLDCB.getValue();
            LocalDateTime createDate = LocalDateTime.now();
            Timestamp createTS = Timestamp.valueOf(createDate);


            try {

                if (excMess.length() > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Adding Appointment");
                    alert.setHeaderText("Error");
                    alert.setContentText(excMess);
                    excMess = "";
                    alert.showAndWait();
                } else {

                    Connection conn = DBConnection.getConnection();

                    String sql = "UPDATE `WJ05NPM`.`customers` SET `Customer_Name` = ?, `Address` = ?," +
                            "`Postal_Code` = ?,`Phone` = ?,`Create_Date` = ?,`Created_By` = ?,`Last_Update` = ?,`Last_Updated_By` = ?, `Division_ID` = ? " +
                            "WHERE (`Customer_ID` = "+ CustomerIDText.getText() + ")";


                    PreparedStatement ps = conn.prepareStatement(sql);

                    ps.setString(1, name);
                    ps.setString(2, address);
                    ps.setString(3, postal);
                    ps.setString(4, phone);
                    ps.setTimestamp(5, createTS);
                    ps.setString(6, LoginMenu.lIn);
                    ps.setTimestamp(7, createTS);
                    ps.setString(8, LoginMenu.lIn);
                    ps.setInt(9, FLD.getDivisionID());

                    ps.execute();
                    System.out.println(ps);

                    CustomerList.addCustomer(
                            new Model.Customer(customerID, name, address, postal, country, FLD.toString(), phone));

                    CustomerList.getAllCustomers().clear();

                    try
                    {
                        DAOCustomers.getAllCustomers();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error!");
                    }

                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddCustomer.fxml")));
                    stage.setScene(new Scene(scene));
                    stage.show();

                }
            } catch (SQLException e) {
                System.out.println(e);
            }


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Left Blank");
            alert.setHeaderText("Please Make Sure All Fields are Filled Out");
            alert.setContentText("Try Again");
            alert.showAndWait();

        }

    }


}
