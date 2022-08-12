import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * testing the InputBuffer class
 * 
 * @author yindrew
 * @version 2022.08.10
 */
public class InputBufferTest extends student.TestCase {

    // field
    private InputBuffer inputBuffer;
    private InputBuffer inputBuffer2;
    private InputBuffer inputBuffer3;
    private InputBuffer inputBuffer4;
    private InputBuffer iB5;
    private InputBuffer iB6;

    /**
     * setting up the tests
     */
    public void setUp() throws FileNotFoundException {
        inputBuffer = new InputBuffer("sorted.bin");
        inputBuffer2 = new InputBuffer("sorted2.bin");
        inputBuffer3 = new InputBuffer("reverse2.bin");
        inputBuffer4 = new InputBuffer("reverse2.bin");

        iB5 = new InputBuffer("random.bin");
        iB6 = new InputBuffer("random.bin");

    }


    /**
     * testing the getAvaliable method
     * 
     * @throws IOException
     *             when file doesn't exist
     */
    public void testGetAvaliable() throws IOException {
        assertEquals(inputBuffer4.getAvaliable(), 16384);
        inputBuffer4.readRecord();
        assertEquals(inputBuffer4.getAvaliable(), 8192);
        inputBuffer4.readRecord();
        assertEquals(inputBuffer4.getAvaliable(), 8192);

    }


    /**
     * testing the readBlock method
     * 
     * @throws IOException
     *             when input file doesn't exist
     */
    public void testReadBlock() throws IOException {
        System.out.println(inputBuffer.readBlock().toString());

        assertTrue(systemOut().getHistory().contains("498.0 499.0"));

    }


    /**
     * testing reading
     * 
     * @throws IOException
     *             when file doesn't exist
     */
    public void testReading() throws IOException {
        assertFalse(iB5.moreToRead());
        Record record = iB5.readRecord();
        Record record1 = iB6.readRecord();
        assertTrue(iB5.moreToRead());
        assertEquals(record.getKey(), record1.getKey(), 0.0);

        iB5.setBufferInfo(0, new int[] { 2, 3 });
        assertEquals(iB5.getBufferInfo()[0], 0., 0);
        iB5.getBufferInfo()[0]++;
        iB5.getBufferInfo()[0]++;
        assertEquals(iB5.getBufferInfo()[0], 2.0, 0);
        System.out.println();

    }


    /**
     * testing the read block method a little more
     * 
     * @throws IOException
     *             when input file doesn't read
     */
    public void testReadBlock3() throws IOException {
        InputBuffer iB = new InputBuffer("reverse2.bin");
        int count = 0;
        while (iB.moreToRead() || iB.getAvaliable() >= 16) {
            iB.readRecord().printOut();
            count++;
        }
        assertEquals(count, 1024);
    }
}
