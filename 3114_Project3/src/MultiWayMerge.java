import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * Multiway merge
 * @author yindrew
 * @version 2022.08.11
 */
public class MultiWayMerge {
    private int[] runInfo; 
    private MinHeap heap;
    private OutputBuffer output;
    private InputBuffer[] IB;

    public MultiWayMerge(String in, String out, LinkedList<Integer> runInfo)
        throws FileNotFoundException {
<<<<<<< HEAD
        IB = new InputBuffer[8];
        this.runInfo = runInfo.toArray();
=======
        
        this.runInfo = runInfo.toArray();
        IB = new InputBuffer[8];
>>>>>>> abff981 (working multiway merge)
        for (int x = 0; x < IB.length; x++) {
            IB[x] = new InputBuffer(in);
        }
        output = new OutputBuffer(out);

    }
    

    /**
     * merge a certain number of runs
     * @param runs number of runs to be merged
     * @param index starting position of the merged runs - first 8, next 8, last 8
     * @throws IOException 
     */
    public void merge(int runs, int index) throws IOException {
        fillHeap(runs, index); // start filling the heap from starting index
        
        
        int numIncrements = 0;
        for(int x = index; x < index + runs; x++) {
            numIncrements += runInfo[x];
        }
        
        for(int i = 0; i < numIncrements; i++) {
            increment();
        }
        
        
    }
    
    /**
     * array of all the input buffers
     * @return
     */
    public InputBuffer[] getIB(){
        return IB;
    }
    
    /**
     * gets the output buffer
     * @return output buffer
     */
    public OutputBuffer getOutput() {
        return output;
    }
    
    /**
     * fills a heap of length x. initial run.
     * @param length number of runs
     * @param offset how far into the file are you. first 8 run/ next 8 run
     * @throws IOException if file doesn't exist
     */
    public void fillHeap(int length, int offset) throws IOException {
        heap = new MinHeap(length);
        for(int i = 0; i < length; i++) {
            IB[i].setBufferInfo(i+offset, runInfo);
            update(IB[i].getBufferInfo(), IB[i]);
            
        }
    }
    
    /**
     * remove min from heap. add to outputbuffer.
     * increment the inputbuffer that removed the value
     * @throws IOException
     */
    public void increment() throws IOException {
        // remove the min from heap
        Record record = heap.removeMin();
        // check for which record the remove belonged too
        for (int x = 0; x < IB.length; x++) {
            // if the last removed element is same as min value
            if (IB[x].getBufferInfo()[2] == record.getKey()) {
                // we update by adding next value in that IB
                // and update the buffer info. given there is 
                // still more to read in the run
                if(IBHelper(IB[x])) {
                    update(IB[x].getBufferInfo(), IB[x]);
                }
                break;
            }
        }
        System.out.print(record.getKey() + " ");
        output.addRecord(record);
    }
    
    /**
     * checks if we should continue reading or not from the inputBuffer
     * @param iP the input buffer
     * @return if we should continue reading
     */
    private boolean IBHelper(InputBuffer iP) {
        return iP.getBufferInfo()[0] != iP.getBufferInfo()[1];

    }
    
    
    

    

    /**
     * adds a record to heap
     * @param bufferInfo helper information for buffer
     * @param buffer buffer that reads values
     * @throws IOException when file doesn't exist
     */
    public void update(double[] bufferInfo, InputBuffer buffer) throws IOException {
        // reads the next record and inserts it
        Record record = buffer.readRecord();
        heap.insert(record);
        // adds one to the numbers of records added
        bufferInfo[0]++;
        // updates the last removed value
        bufferInfo[2] = record.getKey();

    }
    


    /**
     * 
     * @param offset
     *            offset in the runsInfo
     * @return records read, total needed to read, last read key value
     * @throws IOException if file not found
     */
    public double[] bufferHelper(int offset, InputBuffer buffer) throws IOException {
        int sum = 0;
        for (int x = 0; x < offset; x++) {
            sum += runInfo[x];
        }
        
        for(int i = 0; i < sum; i++) {
            buffer.readRecord();
        }
        return new double[] {0, runInfo[offset], 0};
    }

    public MinHeap getHeap() {
        return heap;
    }




}
