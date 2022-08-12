import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * testing output buffer
 * 
 * @author yindrew
 * @version 2022.08.10
 *
 */
public class OutputBufferTest extends student.TestCase {
    private OutputBuffer outputBuffer;
    

    /**
     * setting up the tests
     */
    public void setUp() throws FileNotFoundException {
        outputBuffer = new OutputBuffer("src/runFile.bin");
    }


    /**
     * testing addRecord
     * 
     * @throws IOException
     */
//    public void testAddRecord() throws IOException {
//        Record record = new Record(10, Long.valueOf(5));
//        outputBuffer.addRecord(record);
//        assertEquals(outputBuffer.getSize(), 1);
//        assertEquals(outputBuffer.lastRecord().getKey(), record.getKey(), 0.0);
//
//        Record record1 = new Record(105, Long.valueOf(5));
//        outputBuffer.addRecord(record1);
//        assertEquals(outputBuffer.getSize(), 2);
//        assertEquals(outputBuffer.lastRecord().getKey(), 105, 0.0);
//
//        for (int x = 0; x < 510; x++) {
//            outputBuffer.addRecord(new Record((double)x, Long.valueOf(0)));
//        }
//        assertEquals(outputBuffer.getSize(), 512);
//        assertEquals(outputBuffer.lastRecord().getKey(), 509, 0.0);
//
//        outputBuffer.addRecord(new Record(1000, Long.valueOf(0)));
//        assertEquals(outputBuffer.getSize(), 1);
//
//        InputBuffer inputBuffer = new InputBuffer("src/runFile.bin");
//
//        assertEquals(inputBuffer.readRecord().getKey(), 10, 0.0);
//
//    }
//    
    
    public void testOB() throws IOException {
        OutputBuffer oB = new OutputBuffer("src/finaltesting.bin");
        for(int x = 0; x < 2048; x++) {
            oB.addRecord(new Record(x, Long.valueOf(5)));
        }
        System.out.print(oB.isFull());



        InputBuffer iB = new InputBuffer("src/finaltesting.bin");
        int count = 0;
        while(iB.moreToRead() || iB.getAvaliable() >= 16) {
            count++;
            iB.readRecord().printOut();
        }
        System.out.println();
        System.out.println(count);
    }

}
