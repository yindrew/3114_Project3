import java.io.FileNotFoundException;
import java.io.IOException;

public class InputBufferTest extends student.TestCase {
    InputBuffer inputBuffer;
    InputBuffer inputBuffer2;

    
    
    public void setUp() throws FileNotFoundException {
        inputBuffer = new InputBuffer("src/sorted.bin", 1);
        inputBuffer2 = new InputBuffer("src/sorted2Blocks.bin", 2);


    }
    
    public void testReadBlock() throws IOException {
        //inputBuffer.readBlock();
        //inputBuffer.readBlock();
        
        
    }
    
    public void testReadBlock1() throws IOException {
        inputBuffer2.readBlock();
        inputBuffer2.readBlock();
        
        
    }

}
