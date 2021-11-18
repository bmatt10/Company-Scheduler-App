package ModelObservableList;

/**
 * UserList Class.java
 *
 * This class gets an ObservableList of Users and then adds them to various methods
 *
 * @author Brendan Matthews
 */

import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserList {
    private static final ObservableList<User> allUsers = FXCollections.observableArrayList();

    /**
     * Getter
     *
     */

    public static ObservableList<User> getAllUsers() { return allUsers; }

    /**
     * Adds the list to various methods
     *
     */
    public static void addUser(User newUser)
    {
        allUsers.add(newUser);
    }

}
