import java.io.File;
import java.io.IOException;

public class Processor {

    //field
    private ReplacementSelection RS;
    private String sortedFileName;
    private String tempFileName = "src/runFile.bin";
    private int tempName = 0;
    
    
    public Processor(String fileName) throws IOException {
        
        this.sortedFileName = fileName;
        sortedFileName = fileName;
        RS = new ReplacementSelection(fileName);
        RS.getRuns();

        LinkedList<Integer> temp = RS.runsInfo();
        
        boolean flag = true;
        while (flag) {
            temp = mergeProcessor(temp, this.tempFileName);
            
            if (temp.toArray().length == 1) {
                flag = false;
            }
        }
        
        delRename();

        print(this.sortedFileName);
        
    }
    
    public void delRename() {
        File file = new File(this.sortedFileName);
        
        //delete original file
        file.delete();
        
        File oldName = new File(tempFileName);
        File newName = new File(sortedFileName);

        //rename one run sorted file
        oldName.renameTo(newName);
   
    }
    

    
    /**
     * processes runInfo
     * 
     * @param runsInfo2
     *            array that shows the different runs
     * @throws IOException 
     */
    public LinkedList<Integer> mergeProcessor(LinkedList<Integer> runsInfo, String tempFileName) throws IOException {

        int runsNum = runsInfo.toArray().length;
        
        int eightWay = runsNum / 8;
        int remainder = runsNum % 8;
        
        
        MultiWayMerge merge = new MultiWayMerge(tempFileName, "src/" + tempName +".bin", runsInfo);
        for (int i = 0; i < eightWay; i++) {
            merge.fillHeap(8, i*8); // fix
        }
        
        merge.fillHeap(remainder, runsNum - remainder); //fix
        
        // MultiwayMerge("src/runFile.bin", TODO, runsInfo, remainder);
        
        this.tempFileName = "src/" + tempName +".bin";

        tempName++;
        
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
