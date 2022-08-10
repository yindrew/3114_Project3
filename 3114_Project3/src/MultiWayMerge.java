import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * MultiWayMerge
 * 
 * @author hadenlee
 * @version 2022.08.10
 */
public class MultiWayMerge {
    
    

    //field
    private LinkedList<Integer> runInfo;
    private RandomAccessFile file;
    private MinHeap heap;
    
    /**
     * 
     * @param runInfo
     * @param runFile
     * @throws FileNotFoundException 
     */
    public MultiWayMerge(LinkedList<Integer> info, String fileName) throws FileNotFoundException {
        //use runsInfo to create heap
        this.runInfo = info;
        heap = new MinHeap(runInfo.size());
        
        //get the runFile
        File runFile = new File(fileName);
        this.file = new RandomAccessFile(runFile, "rw");
    }
    
    public void merge() {
        

        
    }

}
