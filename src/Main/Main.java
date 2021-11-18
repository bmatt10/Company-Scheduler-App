
package Main;

import DAO.*;
import Utils.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;


public class Main extends Application {

    @Override
    public void init(){ System.out.println("Starting!!!!"); }

    /**
     * The start method creates the FXML stage and loads the scene.
     *
     * @param primaryStage the FXML stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

            ResourceBundle rb = ResourceBundle.getBundle("Language/Lang", Locale.getDefault());

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Login.fxml")), rb);
            primaryStage.setTitle("Appointment Management Application");
            primaryStage.setScene(new Scene(root, 700, 400));
            primaryStage.show();
        }

    /**
     * The main method is the starting point of the appointment application.
     *
     * The main method loads objects from the database
     *
     * The javadoc files that were generated by this IDE can be found in the folder "Task 1 C195 , Task 1 BM, javadoc"
     *
     * @param args
     */

    public static void main(String[] args) throws Exception {
        try {
        DBConnection.makeConnection();
        DAOAppointment.getAllAppointments();
        DAOAppointment.getAllAppointmentsMonth();
        DAOAppointment.getAllAppointmentsWeek();
        DAOAppointment.getAllAppointmentsContactOne();
        DAOAppointment.getAllAppointmentsContactTwo();
        DAOAppointment.getAllAppointmentsContactThree();

        DAOCountry.getAllCountries();
        DAOContact.getAllContacts();
        DAOCustomers.getAllCustomers();
        DAOFLDivision.getAllFirstLevelDivisions();
        DAOUsers.getAllUsers();

        } catch(NullPointerException exception) {
            System.out.println("Error: " + exception.getMessage());
        }

        launch(args);

        DBConnection.closeConnection();
    }


}
