import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class InputBuffer {
    BufferedInputStream inputBuffer;
    FileInputStream fileInputStream;
    int lastStop;
    Record[] recordArr = new Record[512];

    public InputBuffer(String fileName) throws FileNotFoundException {
        fileInputStream = new FileInputStream(new File(fileName));
    }
    
    
    public void readBlock() throws IOException {
        inputBuffer = new BufferedInputStream(fileInputStream, 8192);
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES + Double.BYTES);
        byte[] dataArray = buffer.array();
        Record record;
        int index = 0;

        while (inputBuffer.read(dataArray) != -1) {
            record = new Record(dataArray);
            recordArr[index] = record;
            index++;
            System.out.println(record.toString());
        }
        
        
    }

}
