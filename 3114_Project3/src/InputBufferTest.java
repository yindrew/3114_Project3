import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * testing the InputBuffer class
 * 
 * @author yindrew
 * @version 2022.08.10
 */
public class InputBufferTest extends student.TestCase {
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
        inputBuffer = new InputBuffer("src/sorted.bin");
        inputBuffer2 = new InputBuffer("src/sorted2Blocks.bin");
        inputBuffer3 = new InputBuffer("src/reverseSorted2Block.bin");
        inputBuffer4 = new InputBuffer("src/reverseSorted2Block.bin");
        
        iB5 = new InputBuffer("src/random.bin");
        iB6 = new InputBuffer("src/random.bin");

    }
    
    
    /**
     * testing reading 
     * @throws IOException when file doesn't exist
     */
    public void testReading() {
        
    }


    /**
     * testing the readRecord method
     * 
     * @throws IOException
     *             when file doesn't exist
     */
    public void testReadRecord() throws IOException {
        System.out.println(inputBuffer4.getAvaliable() + " left");
        System.out.println(inputBuffer4.readRecord());
        System.out.println(inputBuffer4.readRecord());
        System.out.println(inputBuffer4.getAvaliable() + " left");

        assertTrue(systemOut().getHistory().contains("1024"));
        assertTrue(systemOut().getHistory().contains("1023"));
        assertTrue(systemOut().getHistory().contains("16384 left"));
        assertTrue(systemOut().getHistory().contains("8192 left"));

        System.out.println();
    }


    /**
     * testing the read block method
     * 
     * @throws IOException
     *             when input file doesn't exist
     */
    public void testReadBlock() throws IOException {
        System.out.print(inputBuffer.readBlock().toString());
        assertTrue(systemOut().getHistory().contains("498.0 499.0"));

        System.out.println();

    }


    /**
     * testing the read block method a little more
     * 
     * @throws IOException
     *             when input file doesn't read
     */
    public void testReadBlock1() throws IOException {
        inputBuffer2.readBlock();
        System.out.println(inputBuffer2.getAvaliable() + " bytes left");
        System.out.print(inputBuffer2.readBlock().toString());
        assertTrue(systemOut().getHistory().contains("798.0 799.0"));
        assertTrue(systemOut().getHistory().contains("8192 bytes left"));

        System.out.println();

    }


    /**
     * testing the read block method a little more
     * 
     * @throws IOException
     *             when input file doesn't read
     */
    public void testReadBlock2() throws IOException {
        inputBuffer3.readBlock();
        System.out.print(inputBuffer3.readBlock().toString());
        assertTrue(systemOut().getHistory().contains("504.0 503.0"));

    }

}
