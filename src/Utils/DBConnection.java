package Utils;

import java.sql.*;
public class DBConnection {

    //JDBC url parts

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";

    private static final String serverName = "//wgudb.ucertify.com/WJ05NPM";

    private static final String databaseURL = protocol + vendorName + serverName + "?connectionTimeZone = SERVER";

    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";

    private static final String username = "U05NPM";
    private static final String password = "53688551970";


    static Connection conn;
    public static void makeConnection()throws Exception
    {
        Class.forName(MYSQLJDBCDriver);
        conn= DriverManager.getConnection(databaseURL,username,password);
        System.out.println("Connection Successful");
    }
    public static void closeConnection()throws  Exception{
        conn.close();
        System.out.println("Connection Closed");
    }

    public static Connection getConnection () {
        return conn;
    }


}
