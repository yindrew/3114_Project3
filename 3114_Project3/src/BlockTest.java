import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * test class for Block
 * 
 * @author yindrew
 * @version 2022.08.10
 */
public class BlockTest extends student.TestCase {
    private Block block;


    /**
     * setting up the tests
     */
    public void setUp() throws IOException {
        GenBinaryDataFile.generateSorted("sorted.bin", 1);

    }


    /**
     * testing getRecords
     * @throws IOException 
     */
    public void testGetRecords() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(
            "sorted.bin"));
        BufferedInputStream inputStream = new BufferedInputStream(
            fileInputStream);

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        byte[] dataArray = buffer.array();
        if (inputStream.read(dataArray) != -1) {
            block = new Block(dataArray);
        }
        assertEquals(block.getRecords()[0].getKey(), 0, 0.0);
        assertEquals(block.getRecords()[511].getKey(), 511, 0.0);

    }


    /**
     * testing toString
     * @throws IOException 
     */
    public void testToString() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(
            "sorted.bin"));
        BufferedInputStream inputStream = new BufferedInputStream(
            fileInputStream);

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        byte[] dataArray = buffer.array();
        if (inputStream.read(dataArray) != -1) {
            block = new Block(dataArray);
        }
        System.out.print(block.toString());
        assertTrue(systemOut().getHistory().contains("498.0 499.0"));
        assertTrue(systemOut().getHistory().contains("511.0"));

    }

}
