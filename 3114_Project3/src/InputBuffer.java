import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Input buffer
 * 
 * @author yindrew
 * @version 2022.08.10
 */
public class InputBuffer {
    private BufferedInputStream inputStream;
    private FileInputStream fileInputStream;
    private ByteBuffer buffer = ByteBuffer.allocate(8192);
    private byte[] dataArray = buffer.array();
    private int index = 8192;

    /**
     * constructor for the input buffer
     * 
     * @param fileName
     *            the file being read
     * @param numberOfBlocks
     *            number of blocks
     * @throws FileNotFoundException
     *             if we cant find the file
     */
    public InputBuffer(String fileName) throws FileNotFoundException {
        fileInputStream = new FileInputStream(new File(fileName));
        inputStream = new BufferedInputStream(fileInputStream);
    }


    /**
     * gets the remaining available number of bytes
     * 
     * @return the remaining available number of bytes
     * @throws IOException
     *             when input/output is wrong
     */
    public int getAvaliable() throws IOException {
        return inputStream.available();
    }


    /**
     * reads a block from the file
     * 
     * @return a block of data
     * @throws IOException
     *             if there isn't output
     */
    public Block readBlock() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        byte[] dataArray = buffer.array(); // buffer for 1 block
        Block block = null;
        if (inputStream.read(dataArray) != -1) { // reads from inputStream into
                                                 // data array.
            block = new Block(dataArray); // block takes a 8192 sized byte array
                                          // and converts it into 512 records

        }
        return block;

    }


    /**
     * slices an array into smaller pieces
     * 
     * @param arr
     *            the array
     * @param start
     *            the starting index
     * @param end
     *            the ending index
     * @return the sliced array
     */
    private byte[] slice(byte[] arr, int start, int end) {
        byte[] slice = new byte[end - start];

        for (int i = 0; i < slice.length; i++) {
            slice[i] = arr[start + i];
        }

        return slice;
    }


    /**
     * reads a record from the buffer
     * 
     * @return the next buffer
     * @throws IOException
     *             if we there isn't input
     */
    public Record readRecord() throws IOException {
        if (index >= 8192) {
            inputStream.read(dataArray);
            index = 0;
            return readRecord();
        }
        else {
            byte[] sliced = slice(dataArray, index, index + 16);
            Record record = new Record(sliced);
            index += 16;
            return record;
        }

    }
}
