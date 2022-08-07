import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * Output buffer
 * 
 * @author hadenlee
 * @version 08/07/2022
 */
public class outputBuffer {

    //field
    private Record[] records;
    private int size;
    private int capacity;
    
    /**
     * constructor
     * @param recordNum
     */
    public outputBuffer(int recordNum) {
       records = new Record[recordNum];
       size = 0;
       this.capacity = recordNum * 16;
    }
    
    /**
     * add record
     * @param record
     */
    public void addRecord(Record record) {
        records[size] = record;
        size++;
    }
    
    /**
     * write to file
     * @param file
     * @throws IOException
     */
    public void write(RandomAccessFile file) throws IOException {
        ByteBuffer output = ByteBuffer.allocate(capacity);
        for (int i = 0; i < size; i++) {
            ByteBuffer bb = ByteBuffer.allocate(16);
            bb.putLong(records[i].getValue());
            bb.putDouble(records[i].getKey());
            byte[] record = bb.array();
            output.put(record);
        }
        
        file.write(output.array());     
    }
  
    
}
