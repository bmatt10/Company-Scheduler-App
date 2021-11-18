package DAO;

/**
 * DAOCount.java
 *
 * This count loads appointment objects from the database
 *
 * @author Brendan Matthews
 */


import Model.Count;
import ModelObservableList.CountList;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;

public class DAOCount {

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Type = 'Planning Session' and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<Count> getAllCount1() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Type = 'Planning Session' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsType1(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Type = 'De-Briefing' and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<Count> getAllCount2() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Type = 'De-Briefing' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsType2(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Start is in January and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<Count> getAllCountJan() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Start LIKE '_____01%%%' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsTypeJan(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Start is in February and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<Count> getAllCountFeb() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Start LIKE '_____02%%%' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsTypeFeb(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Start is in March and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */

    public static ObservableList<Count> getAllCountMar() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Start LIKE '_____03%%%' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsTypeMar(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Start is in April and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<Count> getAllCountApr() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Start LIKE '_____04%%%' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsTypeApr(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Start is in May and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */

    public static ObservableList<Count> getAllCountMay() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Start LIKE '_____05%%%' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsTypeMay(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Start is in June and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */
    public static ObservableList<Count> getAllCountJun() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Start LIKE '_____06%%%' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsTypeJun(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Start is in July and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */

    public static ObservableList<Count> getAllCountJul() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Start LIKE '_____07%%%' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsTypeJul(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Start is in August and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */

    public static ObservableList<Count> getAllCountAug() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Start LIKE '_____08%%%' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsTypeAug(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Start is in September and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */

    public static ObservableList<Count> getAllCountSep() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Start LIKE '_____09%%%' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsTypeSep(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Start is in October and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */

    public static ObservableList<Count> getAllCountOct() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Start LIKE '_____10%%%' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsTypeOct(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Start is in November and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */

    public static ObservableList<Count> getAllCountNov() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Start LIKE '_____11%%%' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsTypeNov(new Count(type));
        }

        return allCounts;
    }

    /**
     * This method grabs a "COUNT" from the appointments table from the database where Start is in December and returns an observable list
     *
     * @throws IOException
     * @returns ObservableList
     */

    public static ObservableList<Count> getAllCountDec() throws Exception {

        ObservableList<Count> allCounts = FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement = "SELECT COUNT(*) FROM appointments WHERE Start LIKE '_____12%%%' ";
        DBQuery.makeQuery(sqlStatement);
        ResultSet rs = DBQuery.getResult();


        while (rs.next()) {

            String type = rs.getString("COUNT(*)");

            CountList.addCountsTypeDec(new Count(type));
        }

        return allCounts;
    }
}
