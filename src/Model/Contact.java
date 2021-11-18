package Model;

/**
 * Contact.java
 *
 * @author Brendan Matthews
 */

public class Contact {
    private int contactID;
    private String contactName;
    private String email;

    /**
     * A constructor used to create a Contact object
     * @param contactID
     * @param contactName
     * @param email
     */

    public Contact(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Fixes combobox selection
     *
     */
    @Override
    public String toString()
    {
        return(contactName);
    }

    public Contact(String contactName) {
    }

    /**
     * Getters Contact.
     *
     */

    public int getContactID() {
        return contactID;
    }

    public  String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Setters Contact.
     *
     */

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }



}
