import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * Output buffer
 * 
 * @author hadenlee
 * @version 08/07/2022
 */
public class OutputBuffer {

    //field
    private Record[] records = new Record[512];
    private int size = 0;
    private int capacity = 512;
    private RandomAccessFile file;
    
    /**
     * constructor
     * @param recordNum
     */
    public OutputBuffer(RandomAccessFile file) {
        this.file = file;
        
    }
    
    
    public Record lastRecord() {
        return records[size - 1];
    }
    
    /**
     * add record
     * @param record
     * @throws IOException 
     */
    public void addRecord(Record record) throws IOException {
        if(size == capacity) {
            write();
            size = 0;
        }
        records[size] = record;
        size++;
    }
    
    /**
     * write to file
     * @param file
     * @throws IOException
     */
    public void write() throws IOException {
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
