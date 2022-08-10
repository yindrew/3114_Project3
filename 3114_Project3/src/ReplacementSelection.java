import java.io.IOException;

public class ReplacementSelection {
    private InputBuffer inputBuffer;
    private MinHeap minHeap = new MinHeap(4096);
    private OutputBuffer outputBuffer;

    public ReplacementSelection(String file) throws IOException {
        inputBuffer = new InputBuffer(file);
        outputBuffer = new  OutputBuffer(file);
        while (!minHeap.isFull() && inputBuffer.getAvaliable() >= 16) { // maybe
                                                                        // shouldn't
                                                                        // be 16
            minHeap.insert(inputBuffer.readBlock().getRecords());
        }
        // filled heap - phase 1

    }


    public void getRuns() throws IOException {
        // while the heap isn't empty or there is more to read
        while (!minHeap.isEmpty() || inputBuffer.getAvaliable() >= 16) {
            
            // if heap isn't full and there is more to read, add new record
            if (!minHeap.isFull() && inputBuffer.getAvaliable() >= 16) {
                minHeap.insert(inputBuffer.readRecord());
            }
            
            // if heap isn't full and there is no more to read, remove
            else if (!minHeap.isEmpty() && inputBuffer.getAvaliable() == 0) {
                // store min in output
                //outputBuffer.addRecord(minHeap.removeMin()); 
                //System.out.print(outputBuffer.lastRecord());
                System.out.println(minHeap.removeMin());

            }
            
            else {
                //outputBuffer.addRecord(minHeap.removeMin()); 
                //System.out.print(outputBuffer.lastRecord());
                System.out.println(minHeap.removeMin());


            }

        }
        outputBuffer.closeFile();

    }

}
