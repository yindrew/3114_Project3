import java.io.IOException;

/**
 * test class for get binary data test file
 * @author yindrew
 * @version 0.1
 */
public class GenBinaryDataFileTest extends student.TestCase {
    /**
     * testing generateRandom 
     * @throws IOException in case no input/output file
     */
    public void testGenerateRandom() throws IOException {
        GenBinaryDataFile.generateRandom("random.bin", 5);
    }
    
    public void testGenerateSorted() throws IOException {
        GenBinaryDataFile.generateSorted("sorted2Blocks.bin", 2);
    }

}
