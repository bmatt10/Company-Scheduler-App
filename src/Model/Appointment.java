package Model;

import java.time.LocalDateTime;

/**
 * Appointment.java
 *
 * @author Brendan Matthews
 */

public class Appointment {

String name, type, description, location;
LocalDateTime startTS, endTS;
int id;
int CustomerID;
int contact;
String user;

    /**
     * A constructor used to create an FLDivision object
     * @param id
     * @param name
     * @param type
     * @param description
     * @param location
     * @param startTS
     * @param endTS
     * @param contact
     * @param user
     */
    public Appointment(int id, String name, String description, String location, String type, int contact, LocalDateTime startTS, LocalDateTime endTS, int customerID, String user) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.location = location;
        this.startTS = startTS;
        this.endTS = endTS;
        CustomerID = customerID;
        this.contact = contact;
        this.user = user;
    }

    /**
     * Getters Appointment.
     *
     */
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getStart() { return startTS; }

    public LocalDateTime getEnd() {
        return endTS;
    }

    public int getId() {
        return id;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public int getContact() {
        return contact;
    }

    public String getUser() {
        return user;
    }

    /**
     * Setters Appointment.
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStart(LocalDateTime startTS) {
        this.startTS = startTS;
    }

    public void setEnd(LocalDateTime endTS) {
        this.endTS = endTS;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public void setUser(String user) {
        this.user = user;
    }

}


