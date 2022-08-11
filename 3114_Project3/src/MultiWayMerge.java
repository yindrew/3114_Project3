import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MultiwayMerge {
    private int[] runInfo; 
    private MinHeap heap;
    private InputBuffer IB1;
    private InputBuffer IB2;
    private InputBuffer IB3;
    private InputBuffer IB4;
    private InputBuffer IB5;
    private InputBuffer IB6;
    private InputBuffer IB7;
    private InputBuffer IB8;
    private OutputBuffer output;
    private InputBuffer[] IB;

    public MultiwayMerge(String in, String out, LinkedList<Integer> runInfo, int bufferNum)
        throws FileNotFoundException {
        IB = new InputBuffer[bufferNum];
        this.runInfo = runInfo.toArray();
        
        IB1 = new InputBuffer(in);
        IB2 = new InputBuffer(in);
        IB3 = new InputBuffer(in);
        IB4 = new InputBuffer(in);
        IB5 = new InputBuffer(in);
        IB6 = new InputBuffer(in);
        IB7 = new InputBuffer(in);
        IB8 = new InputBuffer(in);
        
        for (int x = 0; x < IB.length; x++) {
            IB[x] = new InputBuffer(in);
        }
        output = new OutputBuffer(out);

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
     * @throws IOException if file doesn't exist
     */
    public void fillHeap(int length) throws IOException {
        heap = new MinHeap(length);
        for(int i = 0; i < length; i++) {
            IB[i].setBufferInfo(i, runInfo);
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
     * fill the empty heap when more than or equal to 8 run
     * @throws IOException when file has error
     */
    public void fillHeapMax() throws IOException {
        // instantiate the heap
        heap = new MinHeap(8);
        
        // get helper values for the buffers
        double[] IB1Info = bufferHelper(0, IB1);
        double[] IB2Info = bufferHelper(1, IB2);
        double[] IB3Info = bufferHelper(2, IB3);
        double[] IB4Info = bufferHelper(3, IB4);
        double[] IB5Info = bufferHelper(4, IB5);
        double[] IB6Info = bufferHelper(5, IB6);
        double[] IB7Info = bufferHelper(6, IB7); 
        double[] IB8Info = bufferHelper(7, IB8);
        
        // adds a record into heap, increments helper values
        // helper values - numbers of records read, last read key value
        update(IB1Info, IB1);
        update(IB2Info, IB2);
        update(IB3Info, IB3);
        update(IB4Info, IB4);
        update(IB5Info, IB5);
        update(IB6Info, IB6);
        update(IB7Info, IB7);
        update(IB8Info, IB8);

        
    }
    
    /**
     * adds a record to heap
     * @param bufferInfo helper information for buffer
     * @param buffer buffer that reads values
     * @throws IOException when file doesn't exist
     */
    public void update(double[] bufferInfo, InputBuffer buffer) throws IOException {
        // reads the next record and inserts it
        Record record1 = buffer.readRecord();
        heap.insert(record1);
        // adds one to the numbers of records added
        bufferInfo[0]++;
        // updates the last removed value
        bufferInfo[2] = record1.getKey();
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
