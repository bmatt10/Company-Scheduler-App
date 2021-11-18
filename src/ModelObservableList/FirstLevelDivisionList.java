package ModelObservableList;

/**
 * FirstLevelDivisionList Class.java
 *
 * This class gets an ObservableList of FirstLevelDivisions and then adds them to various methods
 *
 * @author Brendan Matthews
 */

import Model.FirstLeveLDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FirstLevelDivisionList {


    private static final ObservableList<FirstLeveLDivision> allFirstLevelDivisions = FXCollections.observableArrayList();
    private static final ObservableList<FirstLeveLDivision> allFirstLevelDivisionsUS = FXCollections.observableArrayList();
    private static final ObservableList<FirstLeveLDivision> allFirstLevelDivisionsUK = FXCollections.observableArrayList();
    private static final ObservableList<FirstLeveLDivision> allFirstLevelDivisionsCanada = FXCollections.observableArrayList();

    /**
     * Getter
     *
     */

    public static ObservableList<FirstLeveLDivision> getAllFirstLevelDivisions() { return allFirstLevelDivisions; }
    public static ObservableList<FirstLeveLDivision> getAllFirstLevelDivisionsUS() { return allFirstLevelDivisionsUS; }
    public static ObservableList<FirstLeveLDivision> getAllFirstLevelDivisionsUK() { return allFirstLevelDivisionsUK; }
    public static ObservableList<FirstLeveLDivision> getAllFirstLevelDivisionsCanada() { return allFirstLevelDivisionsCanada; }

    /**
     * Adds the list to various methods
     *
     */

    public static void addFirstLevelDivisions(FirstLeveLDivision newFirstLeveLDivision)
    { allFirstLevelDivisions.add(newFirstLeveLDivision); }

    public static void addFirstLevelDivisionsUS(FirstLeveLDivision newFirstLeveLDivision)
    { allFirstLevelDivisionsUS.add(newFirstLeveLDivision);}

    public static void addFirstLevelDivisionsUK(FirstLeveLDivision newFirstLeveLDivision)
    { allFirstLevelDivisionsUK.add(newFirstLeveLDivision);}

    public static void addFirstLevelDivisionsCanada(FirstLeveLDivision newFirstLeveLDivision)
    { allFirstLevelDivisionsCanada.add(newFirstLeveLDivision);}
}
