import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Input buffer
 * 
 * @author yindrew hadenlee
 * @version 2022.08.10
 */
public class InputBuffer {

    // field
    private BufferedInputStream inputStream;
    private FileInputStream fileInputStream;
    private ByteBuffer buffer = ByteBuffer.allocate(8192);
    private byte[] dataArray = buffer.array();
    private double[] bufferInfo;
    private int index = 8192;

    /**
     * constructor for the input buffer
     * 
     * @param fileName
     *            the file being read
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
     * @return block
     *         a block of data
     * @throws IOException
     *             if there isn't output
     */
    public Block readBlock() throws IOException {

        ByteBuffer blockBuffer = ByteBuffer.allocate(8192);
        byte[] dataArray1 = blockBuffer.array(); // buffer for 1 block
        Block block = null;
        if (inputStream.read(dataArray1) != -1) { // reads from inputStream into
                                                  // data array.
            block = new Block(dataArray1); // block takes a 8192 sized byte
                                           // array
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
     * @return array
     *         the sliced array
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
     * @return Record
     *         the next buffer
     * @throws IOException
     *             if we there isn't input
     */
    public Record readRecord() throws IOException {
        // if finish parsing current block of data, read new block in
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


    /**
     * check if theres more to read
     * 
     * @return true or false
     *         if index is less than 8192
     */
    public boolean moreToRead() {
        return (index < 8192);
    }


    /**
     * sets the buffers position
     * 
     * @param offset
     *            where the buffer position should be
     * @param runInfo
     *            all the records before it
     * @throws IOException
     *             error
     */
    public void setBufferInfo(int offset, int[] runInfo) throws IOException {
        // sum is the total number of records that exist before the offset
        int sum = 0;
        for (int x = 0; x < offset; x++) {
            sum += runInfo[x];
        }
        // we read until we hit the desired point
        for (int i = 0; i < sum; i++) {
            readRecord();
        }
        // we set bufferInfo to hold 3 values
        // the number of values read, the total number of records in the run
        // and the last record we Outputted
        bufferInfo = new double[] { 0, runInfo[offset], 0 };

    }


    /**
     * gets bufferInfo
     * 
     * @return bufferInfo
     */
    public double[] getBufferInfo() {
        return bufferInfo;
    }
}
