import java.io.FileNotFoundException;
import java.io.IOException;

public class Processor {

    //field
    ReplacementSelection RS;
    LinkedList<Integer> runsInfo;
    String sortedFileName;
    
    public Processor(String fileName) throws IOException {
        
        RS = new ReplacementSelection(fileName);
        RS.getRuns();
        runsInfo = RS.runsInfo();
        mergeProcessor(runsInfo);
        print(sortedFileName);
        
    }
    
    /**
     * processes runInfo
     * 
     * @param runsInfo2
     *            array that shows the different runs
     */
    public void mergeProcessor(LinkedList<Integer> runsInfo) {

        int eightWay = numRuns / 8;
        int remainder = numRuns % 8;

        for (int i = 0; i < eightWay; i++) {

        }

    }
    
    /**
     * print
     * @param sortedFileName
     *          sorted file name
     * @throws IOException
     */
    public void print(String sortedFileName) throws IOException {
        InputBuffer IB = new InputBuffer(sortedFileName);
        
        //get all bytes number
        int byteNum = IB.getAvaliable();
        int blokNum = 0;
        
        //get blocks number
        if (byteNum % 8192 > 0) {
            blokNum = byteNum / 8192 + 1;
        } else {
            blokNum = byteNum / 8192;
        }
        
        Record[] printRecord = null;
        
        //get all first record in each block
        for (int i = 0; i < blokNum; i++) {
            printRecord[i] = IB.readBlock().getRecords()[0];
        }
        
        int blockCounter = 0;
        int printCounter = 0;
        
        //print
        while (blockCounter < blokNum) {

            //Check if new line
            if (printCounter == 5) {
                System.out.print("\n");
                printCounter = 0;
            }

            // Print value
            System.out.print(printRecord[blockCounter].getValue() + " ");

            // Print key
            System.out.print(printRecord[blockCounter].getKey() + " ");

            // Increment block counter
            blockCounter++;
            printCounter++;
        }
    }
}
