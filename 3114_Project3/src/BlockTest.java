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
        FileInputStream fileInputStream = new FileInputStream(new File(
            "src/sorted.bin"));
        BufferedInputStream inputStream = new BufferedInputStream(
            fileInputStream);

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        byte[] dataArray = buffer.array();
        if (inputStream.read(dataArray) != -1) {
            block = new Block(dataArray);
        }

    }


    /**
     * testing getRecords
     */
    public void testGetRecords() {
        assertEquals(block.getRecords()[0].getKey(), 0, 0.0);
        assertEquals(block.getRecords()[511].getKey(), 511, 0.0);

    }


    /**
     * testing toString
     */
    public void testToString() {
        System.out.print(block.toString());
        assertTrue(systemOut().getHistory().contains("498.0 499.0"));
        assertTrue(systemOut().getHistory().contains("511.0"));

    }

}
