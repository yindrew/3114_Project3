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
    private LinkedList<Integer> runInfo;
    private int[] runsInfo;
    private RandomAccessFile file;
    private MinHeap heap;
    private boolean ProcessorFlag = false;
    private String fileName;
    private final int blockRecordsNum = 512;
    
    /**
     * 
     * @param runInfo
     * @param runFile
     * @throws FileNotFoundException 
     */
    public MultiWayMerge(LinkedList<Integer> runInfo, String fileName) throws FileNotFoundException {
        //get info
        this.runInfo = runInfo;
        this.fileName = fileName;
 
    }

    public void Processor(int[] runsInfo) {
        int runsNum = runsInfo.length;
        
        int EightWayMergeTimes = runsNum / 8;
        int lastRunsNum = runsNum % 8;
        
        if (EightWayMergeTimes > 0) {
            for (int i = 0; i < EightWayMergeTimes; i++) {
                EightWayMerge();
            }
        } else {
            
        }
        
    }

    
    public int EightWayMerge(String fileName, OutputBuffer output, int[] runsInfo, int currStart, int runInfoOffset) throws IOException {
        InputBuffer IB0 = new InputBuffer(fileName);
        InputBuffer IB1 = new InputBuffer(fileName);
        InputBuffer IB2 = new InputBuffer(fileName);
        InputBuffer IB3 = new InputBuffer(fileName);
        InputBuffer IB4 = new InputBuffer(fileName);
        InputBuffer IB5 = new InputBuffer(fileName);
        InputBuffer IB6 = new InputBuffer(fileName);
        InputBuffer IB7 = new InputBuffer(fileName);
        
        //setup buffer ang get info
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
        if (info[1] + 1 == info[0]) {
            return false;
        }
        
        return true;
    }
    
    /**
     * get runInfo array
     * @param runInfo
     * @return
     */
    public int[] runInfoGetter(LinkedList<Integer> runInfo) {
        
        int[] output = new int[runInfo.size()];
        Node temp = runInfo.getHead();
        
        for (int i = 0; i < runInfo.size(); i++) {
            output[i] = (int)temp.getElement();
            temp = temp.getNext();
        }
        
        return output;
    }

}
