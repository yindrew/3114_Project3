import java.nio.ByteBuffer;

/**
 * Holds a single record
 * 
 * @author CS Staff
 * @version 2022
 */
public class Record implements Comparable<Record> {

    private double key;
    private Long value;
    
    
    public Record(double key, Long value) {
        this.key = key;
        this.value = value;
        
    }

    /**
     * The constructor for the Record class
     * 
     * @param record
     *            The byte for this object
     */
    public Record(byte[] record) {
        ByteBuffer buff = ByteBuffer.wrap(record);
        key = buff.getDouble(8);
        value = buff.getLong(0);
    }


    public double getKey() {
        return key;
    }

    public Long getValue() {
        return value;
    }
    
    /**
     * Compare Two Records based on their keys
     * 
     * @param o
     *            - The Record to be compared.
     * @return A negative integer, zero, or a positive integer as this employee
     *         is less than, equal to, or greater than the supplied record
     *         object.
     */
    @Override
    public int compareTo(Record toBeCompared) {
        return Double.compare(key, toBeCompared.getKey());
    }


    /**
     * Outputs the record as a String
     * 
     * @return the record's key as a string
     */
    public String toString() {
        return String.valueOf(getKey());
    }

}
