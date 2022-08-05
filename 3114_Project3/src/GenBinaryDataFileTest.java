import java.io.IOException;

/**
 * test class for get binary data test file
 * @author yindrew
 * @version 0.1
 */
public class GenBinaryDataFileTest extends student.TestCase {
    
    public void testGenerateRandom() throws IOException {
        GenBinaryDataFile.generateRandom("names", 5);
    }

}
