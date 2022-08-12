import java.io.File;
import java.io.IOException;

/**
 * test class for get binary data test file
 * 
 * @author yindrew
 * @version 2022.08.10
 */
public class GenBinaryDataFileTest extends student.TestCase {
    /**
     * testing generateRandom with 5 block
     * 
     * @throws IOException
     *             in case no input/output file
     */
    public void testGenerateRandom() throws IOException {
        GenBinaryDataFile.generateRandom("random.bin", 5);
        GenBinaryDataFile.generateRandom("random20.bin", 20);
        GenBinaryDataFile.generateRandom("random8.bin", 8);
        GenBinaryDataFile.generateRandom("random9.bin", 9);
        
        assertTrue(new File("random.bin").isFile());
        assertTrue(new File("random20.bin").isFile());
        assertTrue(new File("random8.bin").isFile());
        assertTrue(new File("random9.bin").isFile());

    }


    /**
     * testing generateSorted with 2 blocks
     * 
     * @throws IOException
     *             when failure in IO operations
     */
    public void testGenerateSorted() throws IOException {
        GenBinaryDataFile.generateSorted("sorted.bin", 1);
        GenBinaryDataFile.generateSorted("sorted2.bin", 2);
        
        assertTrue(new File("sorted2.bin").isFile());
        assertTrue(new File("sorted.bin").isFile());

    }


    /**
     * testing generateReverseSorted with 1 block
     * 
     * @throws IOException
     *             when failure in IO operations
     */
    public void testGenerateReverseSorted() throws IOException {
        GenBinaryDataFile.generateReverseSorted("reverse.bin", 1);
        GenBinaryDataFile.generateReverseSorted("reverse2.bin", 2);
        GenBinaryDataFile.generateReverseSorted("reverse20.bin", 20);
        GenBinaryDataFile.generateReverseSorted("reverse20multi.bin", 20);
        GenBinaryDataFile.generateReverseSorted("reverse60.bin", 60);

        assertTrue(new File("reverse.bin").isFile());
        assertTrue(new File("reverse2.bin").isFile());
        assertTrue(new File("reverse20.bin").isFile());
    }

}
