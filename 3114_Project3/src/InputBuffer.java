import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class InputBuffer {
    BufferedInputStream inputStream;
    FileInputStream fileInputStream;
    int lastStop;
    Record[] recordArr = new Record[512]; // one block
    int blocks;

    public InputBuffer(String fileName, int numberOfBlocks) throws FileNotFoundException {
        fileInputStream = new FileInputStream(new File(fileName));
        inputStream = new BufferedInputStream(fileInputStream); // read 8192 bytes from the file
        blocks = numberOfBlocks;

    }
    
    
    public void readBlock() throws IOException {
        
        ByteBuffer buffer = ByteBuffer.allocate(8192); 
        byte[] dataArray = buffer.array(); // buffer for 1 block


        while (inputStream.read(dataArray) != -1) { // reads from inputStream into data array.
           Block block = new Block(dataArray); // block takes a 8192 sized byte array and converts it into 512 records
           System.out.println(block.toString());
           
        }
        
    }

}


