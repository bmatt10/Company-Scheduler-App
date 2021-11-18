package Utils;

import java.sql.*;
import static Utils.DBConnection.conn;

public class DBQuery {

    private static PreparedStatement preparedStatement;
    private static Statement statement;

    public static void setPreparedStatementSQL(Connection conn, String sqlStatement) throws SQLException
    {
        preparedStatement = conn.prepareStatement(sqlStatement);
    }

    public static PreparedStatement getPreparedStatement()
    {
        return preparedStatement;
    }

    public static void setStatement(Connection conn) throws SQLException {
        statement = conn.createStatement();
    }

    public static Statement getStatement()
    {
        return statement;
    }

    private static ResultSet result;

    public static void makeQuery(String q){
        try{
            Statement stmt = conn.createStatement();
            // determine query execution
            if(q.toLowerCase().startsWith("select"))
                result= stmt.executeQuery(q);
            if(q.toLowerCase().startsWith("delete")|| q.toLowerCase().startsWith("insert")|| q.toLowerCase().startsWith("update"))
                stmt.executeUpdate(q);

        }
        catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }
    public static ResultSet getResult(){
        return result;
    }
}
