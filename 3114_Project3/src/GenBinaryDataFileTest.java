import java.io.IOException;

/**
 * test class for get binary data test file
 * @author yindrew
 * @version 0.1
 */
public class GenBinaryDataFileTest extends student.TestCase {
    /**
     * testing generateRandom with 5 block
     * @throws IOException in case no input/output file
     */
    public void testGenerateRandom() throws IOException {
        GenBinaryDataFile.generateRandom("random.bin", 5);
    }
    
    /**
     * testing generateSorted with 1 block
     * @throws IOException when failure in IO operations
     */
    public void testGenerateSortedOneBlock() throws IOException {
        GenBinaryDataFile.generateSorted("sorted.bin", 1);
    }
    
    /**
     * testing generateSorted with 2 blocks
     * @throws IOException when failure in IO operations
     */
    public void testGenerateSorted() throws IOException {
        GenBinaryDataFile.generateSorted("sorted2Blocks.bin", 2);
    }
    
    /**
     * testing generateReverseSorted with 1 block
     * @throws IOException when failure in IO operations
     */
    public void testGenerateReverseSorted() throws IOException {
        GenBinaryDataFile.generateReverseSorted("reverseSorted1Block.bin", 1);
    }
    
    /**
     * testing generateReverseSorted wtih 2 blocks
     * @throws IOException IOexception when failure in IO operations
     */
    public void testGenerateReverseSorted2() throws IOException {
        GenBinaryDataFile.generateReverseSorted("reverseSorted2Block.bin", 2);
    }

}
