import java.io.IOException;
import java.nio.ByteBuffer;

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
    public ReplacementSelection(String file) throws IOException {
        inputBuffer = new InputBuffer(file);
        outputBuffer = new OutputBuffer(file);

        // filled heap - phase 1
        while (!minHeap.isFull() && inputBuffer.getAvaliable() >= 16) {
            minHeap.insert(inputBuffer.readBlock().getRecords());
        }

    }


    /**
     * get the runs of the file
     * 
     * @throws IOException
     *             when there is a error with iput file
     */
    public void getRuns() throws IOException {
        // removeMin to output and add from input - phase 2
        while (inputBuffer.getAvaliable() >= 16) {
            if (minHeap.isFull()) {
                System.out.print(minHeap.removeMin() + " ");
            }
            else {
                minHeap.insert(inputBuffer.readRecord());
            }
        }

        for (int x = 0; x < 1024; x++) { // reads last block in
            if (minHeap.isFull()) {
                System.out.print(minHeap.removeMin() + " ");
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
     */
    private void cleanUp() {
        while (!minHeap.isEmpty()) {
            minHeap.removeMin().printOut();
            ;
        }
    }

}
