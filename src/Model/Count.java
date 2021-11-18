package Model;

/**
 * Count.java
 *
 * @author Brendan Matthews
 */

public class Count {

    public String count;

    /**
     * A constructor used to create a Count object
     * @param count
     */
    public Count(String count) {
        this.count = count;

    }

    /**
     * Fixes combobox selection
     *
     */
    @Override
    public String toString()
    {
        return(count);
    }

    /**
     * Getter
     *
     */
    public String getCount() {
        return count;
    }

    /**
     * Setter
     *
     */
    public void setCount(String count) {
        this.count = count;
    }
}
