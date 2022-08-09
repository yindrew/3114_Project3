import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
/**
 * Input buffer
 * @author yindrew
 * @version 0.1
 */
public class InputBuffer {
    private BufferedInputStream inputStream;
    private FileInputStream fileInputStream;

    /**
     * constructor for the input buffer
     * @param fileName the file being read
     * @param numberOfBlocks number of blocks
     * @throws FileNotFoundException if we cant find the file
     */
    public InputBuffer(String fileName) throws FileNotFoundException {
        fileInputStream = new FileInputStream(new File(fileName));
        inputStream = new BufferedInputStream(fileInputStream); // read 8192 bytes from the file

    }
    
    /**
     * gets the remaining available number of bytes
     * @return the remaining available number of bytes
     * @throws IOException when input/output is wrong
     */
    public int getAvaliable() throws IOException {
        return inputStream.available();
    }
    
    /**
     * reads a block from the file
     * @return a block of data
     * @throws IOException if there isn't output
     */
    public Block readBlock() throws IOException {
        
        ByteBuffer buffer = ByteBuffer.allocate(8192); 
        byte[] dataArray = buffer.array(); // buffer for 1 block
        Block block = null;
        if (inputStream.read(dataArray) != -1) { // reads from inputStream into data array.
           block = new Block(dataArray); // block takes a 8192 sized byte array and converts it into 512 records
           
        }
        return block;
        
    }

    public Record readRecord() throws IOException {
        
        ByteBuffer buffer = ByteBuffer.allocate(16); 
        byte[] dataArray = buffer.array(); // buffer for 1 record
        Record record = null;
        if (inputStream.read(dataArray) != -1) { // reads from inputStream into data array.
           record = new Record(dataArray); // block takes a 16 sized byte array and converts it into 512 records
           
        }
        return record;
        
    }
}


