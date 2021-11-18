package Model;

/**
 * Customer.java
 *
 * @author Brendan Matthews
 */

public class Customer {

    public int customerID;
    public String customerName;
    public String address;
    public String postalCode;
    public String country;
    public String state;
    public String phone;

    /**
     * A constructor used to create a Customer object
     * @param customerID
     * @param customerName
     * @param address
     * @param postalCode
     * @param country
     * @param state
     * @param phone
     */

    public Customer(int customerID, String customerName, String address, String postalCode, String country, String state, String phone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.state = state;
        this.phone = phone;
    }

    /**
     * Getters Customer.
     *
     */

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getState() { return state; }

    public String getPhone() {
        return phone;
    }


    /**
     * Setters Customer.
     *
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Fixes combobox selection
     *
     */
    @Override public String toString(){
        return (state);
    }
}
