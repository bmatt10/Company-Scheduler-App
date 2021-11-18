package Model;
/**
 * User.java
 *
 * @author Brendan Matthews
 */

public class User {
    private String UserID;
    private String UserName;
    private String Password;

    public User(String userID, String userName, String password) {
        this.UserID = userID;
        this.UserName = userName;
        this.Password = password;
    }

    /**
     * Getters User.
     *
     */


    public String getUserID() {
        return UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }


    /**
     * Setter User.
     *
     */

    public void setUserID(String userID) {
        this.UserID = userID;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    /**
     * Fixes combobox selection
     *
     */

    @Override
    public String toString()
    {
        return(UserID);
    }
}

