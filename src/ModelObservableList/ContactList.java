package ModelObservableList;

/**
 * ContactList Class.java
 *
 * This class gets an ObservableList of Contacts and then adds them to various methods
 *
 * @author Brendan Matthews
 */

import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactList {
    private static final ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    /**
     * Getter
     *
     */

    public static ObservableList<Contact> getAllContacts() { return allContacts; }

    /**
     * Adds the list to various methods
     *
     */

    public static void addContact(Contact newContact)
    {
        allContacts.add(newContact);
    }



}
