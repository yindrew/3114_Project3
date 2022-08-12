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
        assertTrue(new File("src/random.bin").isFile());
        assertTrue(new File("src/random20.bin").isFile());
        assertTrue(new File("src/random8.bin").isFile());
        assertTrue(new File("src/random9.bin").isFile());

    }


    /**
     * testing generateSorted with 1 block
     * 
     * @throws IOException
     *             when failure in IO operations
     */
    public void testGenerateSortedOneBlock() throws IOException {
        GenBinaryDataFile.generateSorted("sorted.bin", 1);
        assertTrue(new File("src/sorted.bin").isFile());
    }


    /**
     * testing generateSorted with 2 blocks
     * 
     * @throws IOException
     *             when failure in IO operations
     */
    public void testGenerateSorted() throws IOException {
        GenBinaryDataFile.generateSorted("sorted2Blocks.bin", 2);
        assertTrue(new File("src/sorted2Blocks.bin").isFile());
        
        GenBinaryDataFile.generateSorted("OutputBufferTest.bin", 2);

    }


    /**
     * testing generateReverseSorted with 1 block
     * 
     * @throws IOException
     *             when failure in IO operations
     */
    public void testGenerateReverseSorted() throws IOException {
        GenBinaryDataFile.generateReverseSorted("reverseSorted1Block.bin", 1);
        assertTrue(new File("src/reverseSorted1Block.bin").isFile());

    }


    /**
     * testing generateReverseSorted wtih 2 blocks
     * 
     * @throws IOException
     *             IOexception when failure in IO operations
     */
    public void testGenerateReverseSorted2() throws IOException {
        GenBinaryDataFile.generateReverseSorted("reverseSorted2Block.bin", 2);
        GenBinaryDataFile.generateReverseSorted("reverse20.bin", 20);
        GenBinaryDataFile.generateReverseSorted("reverse20multi.bin", 20);

        GenBinaryDataFile.generateReverseSorted("reverse60.bin", 60);


        assertTrue(new File("src/reverseSorted2Block.bin").isFile());
        assertTrue(new File("src/reverse20.bin").isFile());

    }

}
