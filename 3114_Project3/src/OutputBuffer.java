import java.io.File;
import java.io.FileNotFoundException;
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
     * @throws FileNotFoundException 
     */
    public OutputBuffer(String fileName) throws FileNotFoundException {
        File runFile = new File(fileName);
        this.file = new RandomAccessFile(runFile, "rw");
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
     * 
     * @throws IOException
     */
    public void closeFile() throws IOException {
        this.file.close();
    }
    
    /**
     * write to file
     * @param file
     * @throws IOException
     */
    private void write() throws IOException {
        ByteBuffer output = ByteBuffer.allocate(capacity);
        for (int i = 0; i < size; i++) {
            ByteBuffer bb = ByteBuffer.allocate(16);
            bb.putLong(records[i].getValue());
            bb.putDouble(records[i].getKey());
            byte[] record = bb.array();
            output.put(record);
        }
        
        this.file.write(output.array());     
    }
  
    
}
