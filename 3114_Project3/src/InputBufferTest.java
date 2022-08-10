import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * testing the inputbuffer class
 * @author yindrew
 * @version 0.1
 */
public class InputBufferTest extends student.TestCase {
    private InputBuffer inputBuffer;
    private InputBuffer inputBuffer2;
    private InputBuffer inputBuffer3;
    

    
    /**
     * setting up the tests
     */
    public void setUp() throws FileNotFoundException {
        inputBuffer = new InputBuffer("src/sorted.bin");
        inputBuffer2 = new InputBuffer("src/sorted2Blocks.bin");
        inputBuffer3 = new InputBuffer("src/reverseSorted2Block.bin");



    }
    
    /**
     * testing the read block method
     * @throws IOException when input file doesn't exist
     */
    public void testReadBlock() throws IOException {
        System.out.print(inputBuffer.readBlock().toString());
        assertTrue(systemOut().getHistory().contains("498.0 499.0"));

        System.out.println("---------------");
        
    }
    
    /**
     * testing the read block method a little more
     * @throws IOException when input file doesn't read
     */
    public void testReadBlock1() throws IOException {
        inputBuffer2.readBlock();
        System.out.println(inputBuffer2.getAvaliable() + " bytes left");
        System.out.print(inputBuffer2.readBlock().toString());
        assertTrue(systemOut().getHistory().contains("798.0 799.0"));
        assertTrue(systemOut().getHistory().contains("8192 bytes left"));


        System.out.println("---------------");

    }
    
    /**
     * testing the read block method a little more
     * @throws IOException when input file doesn't read
     */
    public void testReadBlock2() throws IOException {
        inputBuffer3.readBlock();
        System.out.print(inputBuffer3.readBlock().toString());
        assertTrue(systemOut().getHistory().contains("504.0 503.0"));

        
    }

}