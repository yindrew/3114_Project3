import java.nio.ByteBuffer;

/**
 * Holds a single record
 * 
 * @author CS Staff
 * @version 2022
 * 
 */
public class Record implements Comparable<Record> {

    private double key;
    private Long value;

    /**
     * Constructor for the Record class
     * 
     * @param key key value
     * @param value value of key
     */
    public Record(double key, Long value) {
        this.key = key;
        this.value = value;

    }
    
    /**
     * converting to binary arr
     * @return binary arr
     */
    public byte[] toBinary() {
        ByteBuffer bb = ByteBuffer.allocate(16);
        bb.putLong(value);
        bb.putDouble(key);
        byte[] record = bb.array();
        return record;
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


    /**
     * getter method for the key
     * 
     * @return the key
     */
    public double getKey() {
        return key;
    }

    /**
     * printing out as a string
     */
    public void printOut() {
        System.out.print(toString() + " ");

    }


    /**
     * getter method for the value
     * 
     * @return the value
     */
    public Long getValue() {
        return value;
    }


    /**
     * compare two records
     * @param toBeCompared the record to be compared to
     * @return if its greater or less than
     */
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
