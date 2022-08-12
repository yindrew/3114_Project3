import java.nio.ByteBuffer;
import student.TestCase;

/**
 * Holds a single record
 * 
 * @author CS Staff
 * @version 2022.08.10
 */
public class RecordTest extends TestCase {

    private byte[] aBite;
    private byte[] bBite;
    private Record record;
    private Record record1;

    /**
     * The setup for the tests
     */
    public void setUp() {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES + Double.BYTES);
        buffer.putLong(7);
        buffer.putDouble(8, 1);
        aBite = buffer.array();
        buffer = ByteBuffer.allocate(Long.BYTES + Double.BYTES);
        buffer.putLong(4);
        buffer.putDouble(8, 0.33);
        bBite = buffer.array();
        record = new Record(5, Long.valueOf(3));
        record1 = new Record(10, Long.valueOf(5));

    }


    /**
     * test toBinary method
     */
    public void testToBinary() {
        Record rec = new Record(aBite);
        byte[] recBinary = rec.toBinary();
        for (int x = 0; x < recBinary.length; x++) {
            assertEquals(recBinary[x], aBite[x]);
        }
    }


    /**
     * Tests the first constructor
     */
    public void testConstruct1() {
        Record rec = new Record(aBite);
        assertEquals((double)1, rec.getKey(), 0.00);
        assertTrue(rec.toString().equals("1.0"));
    }


    /**
     * Tests the second constructor
     */
    public void testConstruct2() {
        record1.printOut();
        assertTrue(systemOut().getHistory().contains("10.0"));
        assertEquals(record1.getKey(), 10.0, 0.0);
        assertTrue(record1.getValue() == 5);

    }


    /**
     * Tests the getter methods
     */
    public void testGetter() {
        assertEquals(record.getKey(), 5, 0.0);
        assertTrue(record.getValue() == 3);
    }


    /**
     * testing the toString method
     */
    public void testToString() {
        assertEquals(record.toString(), "5.0");
    }


    /**
     * Tests the first constructor
     */
    public void testCompareTo() {
        Record aRec = new Record(aBite);
        Record recToBeCompared = new Record(aBite);
        assertEquals(aRec.compareTo(recToBeCompared), 0);
        Record bRec = new Record(bBite);
        assertEquals(aRec.compareTo(bRec), 1);
    }

}
