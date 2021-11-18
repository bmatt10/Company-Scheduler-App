package ModelObservableList;

/**
 * AppointmentList Class.java
 *
 * This class gets an ObservableList of Appointments and then adds them to various methods
 *
 * @author Brendan Matthews
 */
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AppointmentList {

    private static final ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> allAppointmentsMonth = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> allAppointmentsWeek = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> allAppointmentsOne = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> allAppointmentsTwo = FXCollections.observableArrayList();
    private static final ObservableList<Appointment> allAppointmentsThree = FXCollections.observableArrayList();

    /**
     * Getters
     *
     */

    public static ObservableList<Appointment> getAllAppointments() { return allAppointments; }

    public static ObservableList<Appointment> getAllAppointmentsMonth() { return allAppointmentsMonth; }

    public static ObservableList<Appointment> getAllAppointmentsWeek() { return allAppointmentsWeek; }

    public static ObservableList<Appointment> getAllAppointmentsOne() {
        return allAppointmentsOne;
    }

    public static ObservableList<Appointment> getAllAppointmentsTwo() {
        return allAppointmentsTwo;
    }

    public static ObservableList<Appointment> getAllAppointmentsThree() {
        return allAppointmentsThree;
    }

    /**
     * Adds the list to various methods
     *
     */

    public static void addAppointment(Appointment newAppointment)
    {
        allAppointments.add(newAppointment);
    }

    public static void addAppointmentMonth(Appointment newAppointment)
    { allAppointmentsMonth.add(newAppointment);}

    public static void addAppointmentWeek(Appointment newAppointment)
    { allAppointmentsWeek.add(newAppointment);}

    public static void addAppointmentOne(Appointment newAppointment)
    { allAppointmentsOne.add(newAppointment);}

    public static void addAppointmentTwo(Appointment newAppointment)
    { allAppointmentsTwo.add(newAppointment);}

    public static void addAppointmentThree(Appointment newAppointment)
    { allAppointmentsThree.add(newAppointment);}

    /**
     * Deletes the list from various methods
     *
     */
    public static boolean deleteAppointment(Appointment selectedAppointment)
    {
        return getAllAppointments().remove(selectedAppointment);
    }


}
