import java.io.IOException;

/**
 * replacement selection sort
 * 
 * @author yindrew
 * @version 2022.08.10
 */
public class ReplacementSelection {
    private InputBuffer inputBuffer;
    private MinHeap minHeap = new MinHeap(4096);
    private OutputBuffer outputBuffer;

    /**
     * constructor
     * 
     * @param file
     *            input file
     * @throws IOException
     *             when error with input/output files
     */
    public ReplacementSelection(String inFile, String outputFile) throws IOException {
        inputBuffer = new InputBuffer(inFile);
        outputBuffer = new OutputBuffer(outputFile);

        // filled heap - phase 1
        while (!minHeap.isFull() && inputBuffer.getAvaliable() >= 16) {
            minHeap.insert(inputBuffer.readBlock().getRecords());
        }

    }
    
    /**
     * getting runs info
     * @return runs info
     */
    public LinkedList<Integer> runsInfo() {
        return minHeap.getRunInfo();
        
    }

    /**
     * get the runs of the file
     * 
     * @throws IOException
     *             when there is a error with input file
     */
    public void getRuns() throws IOException {
        // read from input buffer - fix this part
        while (inputBuffer.getAvaliable() >= 16 || inputBuffer.moreToRead()) {
            if (minHeap.isFull()) {
                Record record = minHeap.removeMin();
                outputBuffer.addRecord(record);
                //record.printOut();
            }
            else {
                minHeap.insert(inputBuffer.readRecord());
            }
        }

        // finishing up the last bits - phase 3
        cleanUp();

        outputBuffer.closeFile();

    }


    /**
     * clean up the end of the file
     * @throws IOException when input is empty
     */
    private void cleanUp() throws IOException {
        while (!minHeap.isEmpty()) {
            Record record = minHeap.removeMin();
            outputBuffer.addRecord(record);    
            //record.printOut();


        }
    }

}
