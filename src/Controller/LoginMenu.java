package Controller;

/**
 * LoginMenu.java
 *
 * This class logs user into the application
 *
 * @author Brendan Matthews
 */

import Utils.DBConnection;
import Utils.DBQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginMenu implements Initializable {
    public Label pLabel;
    public Label uIdLabel;
    public TextField userIdTextField;
    public PasswordField passwordTextField;
    public static String lIn;
    public Button loginB;
    public Label ZoneID;
    Stage stage;
    Parent scene;
    private ResourceBundle rb;

    /**
     * Checks the user table for username and password field and then sends to the AppointmentMenu Controller if successful
     *
     * @param actionEvent Week button action.
     * @throws SQLException
     * @throws IOException
     */

    public void loginButtonClicked(ActionEvent actionEvent) throws SQLException, IOException {


        Connection conn = DBConnection.getConnection();
        String sqlPasswordAndUsernameSelect = "SELECT * FROM users WHERE User_Name='" + userIdTextField.getText() +
                "' AND Password='" + passwordTextField.getText() + "'";
        System.out.println(sqlPasswordAndUsernameSelect);
        DBQuery.setPreparedStatementSQL(conn, sqlPasswordAndUsernameSelect);

        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute();

        ResultSet rs = ps.executeQuery(sqlPasswordAndUsernameSelect);

        if (rs.next()) {

            LoginSuccessful();
            System.out.println("success");
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AppointmentMenu.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
            try {
                AppointmentMenu.FifteenMinuteAlert();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {

            LoginFailed();
            System.out.println("failed");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(rb.getString("IncorrectError"));
            alert.showAndWait();
            userIdTextField.clear();
            passwordTextField.clear();

        }
    }

    /**
     * This method logs the failed attempts in the loginButtonClicked method to a "login_activity.txt" file
     *
     */
    public static void LoginFailed()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileOutputStream(new File("login_activity.txt"),true));
            printwriter.append("At ").append(String.valueOf(LocalDateTime.now())).append(" an attempt to login failed\n");
            printwriter.close();
        }

        catch (FileNotFoundException ex)
        {
            Logger.getLogger(LoginMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method logs the successful attempts in the loginButtonClicked method to a "login_activity.txt" file
     *
     */

    public static void LoginSuccessful()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileOutputStream(new File("login_activity.txt"),true));
            printwriter.append("At ").append(String.valueOf(LocalDateTime.now())).append(" an attempt to login succeeded\n");
            printwriter.close();
        }

        catch (FileNotFoundException ex)
        {
            Logger.getLogger(LoginMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Initializes the LoginMenu controller
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb)
    {
        ZoneId id = ZoneId.systemDefault();

        pLabel.setText(rb.getString("password"));
        uIdLabel.setText(rb.getString("username"));
        loginB.setText(rb.getString("login"));
        ZoneID.setText(id.toString());

        this.rb = rb;


    }
}