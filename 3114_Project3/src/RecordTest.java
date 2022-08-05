
import java.nio.ByteBuffer;
import student.TestCase;

/**
 * Holds a single record
 * 
 * @author CS Staff
 * @version 2020-10-15
 */
public class RecordTest extends TestCase {

    private byte[] aBite;
    private byte[] bBite;


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
    }


    /**
     * Tests the first constructor
     */
    public void testConstruct1() {
        Record rec = new Record(aBite);
        assertEquals((double)1, rec.getKey(), 0.00);
        assertEquals(aBite, rec.getCompleteRecord());
        assertTrue(rec.toString().equals("1.0"));
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
        for (int a = 5; a > 0; a --) {
            System.out.print(a);
        }
    }

}
