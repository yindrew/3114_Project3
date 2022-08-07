import java.io.FileNotFoundException;
import java.io.IOException;

public class InputBufferTest extends student.TestCase {
    InputBuffer inputBuffer;
    
    
    public void setUp() throws FileNotFoundException {
        inputBuffer = new InputBuffer("src/sorted.bin");

    }
    
    public void testReadBlock() throws IOException {
        inputBuffer.readBlock();
        inputBuffer.readBlock();
        
        
    }

}
