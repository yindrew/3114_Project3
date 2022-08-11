import java.io.FileNotFoundException;
import java.io.IOException;

public class Processor {

    //field
    ReplacementSelection RS;
    String sortedFileName;
    
    public Processor(String fileName) throws IOException {
        
        RS = new ReplacementSelection(fileName);
        RS.getRuns();

        LinkedList<Integer> temp = RS.runsInfo();
        
        boolean flag = true;
        while (flag) {
            temp = mergeProcessor(temp);
            
            if (temp.toArray().length == 1) {
                flag = false;
            }
        }

        print(fileName);
        
    }
    
    /**
     * processes runInfo
     * 
     * @param runsInfo2
     *            array that shows the different runs
     * @throws FileNotFoundException 
     */
    public LinkedList<Integer> mergeProcessor(LinkedList<Integer> runsInfo) throws FileNotFoundException {

        int runsNum = runsInfo.toArray().length;
        
        int eightWay = runsNum / 8;
        int remainder = runsNum % 8;

        for (int i = 0; i < eightWay; i++) {
            new MultiwayMerge("src/runFile.bin", "src/temp1.dat", runsInfo, 8);
        }
        
        new MultiwayMerge("src/runFile.bin", "src/temp1.dat", runsInfo, remainder);
        
        //TODO get correct new runs INFO
        return runsInfo;

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
