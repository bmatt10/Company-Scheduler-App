package ModelObservableList;

/**
 * CountList Class.java
 *
 * This class gets an ObservableList of Counts and then adds them to various methods
 *
 * @author Brendan Matthews
 */

import Model.Count;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountList {

    private static final ObservableList<Count> allCountsType1 = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsType2 = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsTypeJan = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsTypeFeb = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsTypeMar = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsTypeApr = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsTypeMay = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsTypeJun = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsTypeJul = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsTypeAug = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsTypeSep = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsTypeOct = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsTypeNov = FXCollections.observableArrayList();
    private static final ObservableList<Count> allCountsTypeDec = FXCollections.observableArrayList();

    /**
     * Getters
     *
     */

    public static ObservableList<Count> getAllCountsType1() { return allCountsType1; }
    public static ObservableList<Count> getAllCountsType2() { return allCountsType2; }
    public static ObservableList<Count> getAllCountsTypeJan() { return allCountsTypeJan; }
    public static ObservableList<Count> getAllCountsTypeFeb() { return allCountsTypeFeb; }
    public static ObservableList<Count> getAllCountsTypeMar() { return allCountsTypeMar; }
    public static ObservableList<Count> getAllCountsTypeApr() { return allCountsTypeApr; }
    public static ObservableList<Count> getAllCountsTypeMay() { return allCountsTypeMay; }
    public static ObservableList<Count> getAllCountsTypeJun() { return allCountsTypeJun; }
    public static ObservableList<Count> getAllCountsTypeJul() { return allCountsTypeJul; }
    public static ObservableList<Count> getAllCountsTypeAug() { return allCountsTypeAug; }
    public static ObservableList<Count> getAllCountsTypeSep() { return allCountsTypeSep; }
    public static ObservableList<Count> getAllCountsTypeOct() { return allCountsTypeOct; }
    public static ObservableList<Count> getAllCountsTypeNov() { return allCountsTypeNov; }
    public static ObservableList<Count> getAllCountsTypeDec() { return allCountsTypeDec; }

    /**
     * Adds the list to various methods
     *
     */
    public static void addCountsType1(Count newCount)
    { allCountsType1.add(newCount);}
    public static void addCountsType2(Count newCount)
    { allCountsType2.add(newCount);}
    public static void addCountsTypeJan(Count newCount)
    { allCountsTypeJan.add(newCount);}
    public static void addCountsTypeFeb(Count newCount)
    { allCountsTypeFeb.add(newCount);}
    public static void addCountsTypeMar(Count newCount)
    { allCountsTypeMar.add(newCount);}
    public static void addCountsTypeApr(Count newCount)
    { allCountsTypeApr.add(newCount);}
    public static void addCountsTypeMay(Count newCount)
    { allCountsTypeMay.add(newCount);}
    public static void addCountsTypeJun(Count newCount)
    { allCountsTypeJun.add(newCount);}
    public static void addCountsTypeJul(Count newCount)
    { allCountsTypeJul.add(newCount);}
    public static void addCountsTypeAug(Count newCount)
    { allCountsTypeAug.add(newCount);}
    public static void addCountsTypeSep(Count newCount)
    { allCountsTypeSep.add(newCount);}
    public static void addCountsTypeOct(Count newCount)
    { allCountsTypeOct.add(newCount);}
    public static void addCountsTypeNov(Count newCount)
    { allCountsTypeNov.add(newCount);}
    public static void addCountsTypeDec(Count newCount)
    { allCountsTypeDec.add(newCount);}


}
