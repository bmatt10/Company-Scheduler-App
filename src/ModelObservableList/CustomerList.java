package ModelObservableList;

/**
 * CustomerList Class.java
 *
 * This class gets an ObservableList of Customers and then adds them to various methods
 *
 * @author Brendan Matthews
 */

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerList {

    private static final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**
     * Getter
     *
     */

    public static ObservableList<Customer> getAllCustomers() { return allCustomers; }

    /**
     * Adds the list to various methods
     *
     */

    public static void addCustomer(Customer newCustomer)
    {
        allCustomers.add(newCustomer);
    }

    /**
     * Deletes the list from various methods
     *
     */
    public static boolean deleteCustomer(Customer selectedCustomer)
    {
        return getAllCustomers().remove(selectedCustomer);
    }
}

