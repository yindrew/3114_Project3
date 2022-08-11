import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

/**
 * MultiWayMerge
 * 
 * @author hadenlee
 * @version 2022.08.10
 */
public class MultiWayMerge {
    
    

    //field
    private LinkedList<Integer> runInfo; // number of records in each run in a LL
    private int[] runsInfo; // Integer form
    private RandomAccessFile file; // file to read into  
    private MinHeap heap; // holds the min values of each of 8 Input Buffers
    private boolean ProcessorFlag = false;
    private String fileName;
    private final int blockRecordsNum = 512;
    private InputBuffer IB0;
    private InputBuffer IB1;
    private InputBuffer IB2;
    private InputBuffer IB3;
    private InputBuffer IB4;
    private InputBuffer IB5;
    private InputBuffer IB6;
    private InputBuffer IB7;   
    private OutputBuffer output;
    /**
     * empty constructor 
     */
    public MultiWayMerge() {
        //get info
    }
    /**
     * contructor for multiway merge
     * @param runInfo the run info for this merge
     * @param inputFile the file being read
     * @throws FileNotFoundException when we can't find the file
     */
    public MultiWayMerge(LinkedList<Integer> runInfo, String inputFile, String outputFile) throws FileNotFoundException {
        //get info
        this.runInfo = runInfo;
        this.fileName = inputFile;
        IB0 = new InputBuffer(inputFile);
        IB1 = new InputBuffer(inputFile);
        IB2 = new InputBuffer(inputFile);
        IB3 = new InputBuffer(inputFile);
        IB4 = new InputBuffer(inputFile);
        IB5 = new InputBuffer(inputFile);
        IB6 = new InputBuffer(inputFile);
        IB7 = new InputBuffer(inputFile);
        output = new OutputBuffer(outputFile);
 
    }

    /**
     * processes runInfo
     * @param runsInfo array that shows the different runs
     */
    public void Processor(int[] runsInfo) {
        int runsNum = runsInfo.length;
        
        int EightWayMergeTimes = runsNum / 8;
        int lastRunsNum = runsNum % 8;
        
        for (int i = 0; i < EightWayMergeTimes; i++) {
                //EightWayMerge(runsInfo, i, );
            
        }         
    }
    
    public void fillHeap() {
        MinHeap heap = new MinHeap(8);

    }
    


    /**
     * eight way merge
     * @param output 
     * @param runsInfo
     * @param currStart
     * @param runInfoOffset
     * @return
     * @throws IOException
     */
    public int EightWayMerge(int[] runsInfo, int currStart, int runInfoOffset) throws IOException {
        int[] IB0Info = BufferHelper(IB0, 0, runsInfo, currStart, runInfoOffset);
        int[] IB1Info = BufferHelper(IB1, 1, runsInfo, currStart, runInfoOffset);
        int[] IB2Info = BufferHelper(IB2, 2, runsInfo, currStart, runInfoOffset);
        int[] IB3Info = BufferHelper(IB3, 3, runsInfo, currStart, runInfoOffset);
        int[] IB4Info = BufferHelper(IB4, 4, runsInfo, currStart, runInfoOffset);
        int[] IB5Info = BufferHelper(IB5, 5, runsInfo, currStart, runInfoOffset);
        int[] IB6Info = BufferHelper(IB6, 6, runsInfo, currStart, runInfoOffset);
        int[] IB7Info = BufferHelper(IB7, 7, runsInfo, currStart, runInfoOffset);        
        //store next merge start record
        
        int startRecord = 0;
        for (int i = runInfoOffset; i < runInfoOffset + 8; i++) {
            startRecord += runsInfo[i];
        }
        
        
        //create a minHeap
        MinHeap heap = new MinHeap(8);
        
        boolean flag = true;
        while (flag || !heap.isEmpty()) {
           
            if (IB0Info[1] < IB0Info[0]) {
                heap.insert(IB0.readRecord());
                IB0Info[1]++;
            }
            
            if (IB1Info[1] < IB1Info[0]) {
                heap.insert(IB1.readRecord());
                IB1Info[1]++;
            }
            
            if (IB2Info[1] < IB2Info[0]) {
                heap.insert(IB2.readRecord());
                IB2Info[1]++;
            }
            
            if (IB3Info[1] < IB3Info[0]) {
                heap.insert(IB3.readRecord());
                IB3Info[1]++;
            }
            
            if (IB4Info[1] < IB4Info[0]) {
                heap.insert(IB4.readRecord());
                IB4Info[1]++;
            }
            
            if (IB5Info[1] < IB5Info[0]) {
                heap.insert(IB5.readRecord());
                IB5Info[1]++;
            }
            
            if (IB6Info[1] < IB6Info[0]) {
                heap.insert(IB6.readRecord());
                IB6Info[1]++;
            }
            
            if (IB7Info[1] < IB7Info[0]) {
                heap.insert(IB7.readRecord());
                IB7Info[1]++;
            }
            
            flag = IBHelper(IB0Info) || IBHelper(IB1Info) || IBHelper(IB2Info) || IBHelper(IB3Info) || 
                IBHelper(IB4Info) || IBHelper(IB5Info) || IBHelper(IB6Info) || IBHelper(IB7Info);
            
            output.addRecord(heap.removeMin());
            
        
        }
        
        return startRecord;       
    }
        
    private int[] BufferHelper(InputBuffer ib, int bufferIndex, 
        int[] runsInfo, int currStart, int runInfoOffset) throws IOException {
        
        int[] ret = new int[2];
      
        //get record number for buffer
        int IBrunsInfo = runsInfo[runInfoOffset + bufferIndex];
        
        //get start record for buffer
        int IBStartRecord = currStart;
        for (int i = 0; i < bufferIndex; i++) {
            IBStartRecord += runsInfo[runInfoOffset + i];
        }
        
        for (int i = 0; i < IBStartRecord; i++) {
            ib.readRecord();
        }
        
        //record number for buffer
        ret[0] = IBrunsInfo;
        
        //reading time
        ret[1] = 0;
        
        return ret;
        
    }
    
    private boolean IBHelper(int[] info) {
        
        return !(info[1] + 1 == info[0]);

    }
    
    /**
     * get runInfo array
     * @param runInfo
     * @return
     */
    public int[] runInfoGetter(LinkedList<Integer> runInfo) {
        
        int[] output = new int[runInfo.size()];
        Node<Integer> temp = runInfo.getHead();
        
        for (int i = 0; i < runInfo.size(); i++) {
            output[i] = (int)temp.getElement();
            temp = temp.getNext();
        }
        
        return output;
    }

}
