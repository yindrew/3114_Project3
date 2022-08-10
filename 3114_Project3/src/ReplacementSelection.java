import java.io.IOException;
import java.nio.ByteBuffer;

public class ReplacementSelection {
    private InputBuffer inputBuffer;
    private MinHeap minHeap = new MinHeap(4096);
    private OutputBuffer outputBuffer;

    public ReplacementSelection(String file) throws IOException {
        inputBuffer = new InputBuffer(file);
        outputBuffer = new OutputBuffer(file);
        while (!minHeap.isFull() && inputBuffer.getAvaliable() >= 16) { 
            minHeap.insert(inputBuffer.readBlock().getRecords());
        }
        //System.out.println(minHeap.isFull() + "  ");
        // filled heap - phase 1

    }
    
    public MinHeap getMinHeap() {
        return minHeap;
    }


    public void getRuns() throws IOException {
        // removeMin to output and add from input - phase 2
        while (inputBuffer.getAvaliable() >= 16) {
            if( minHeap.isFull() ) {
                System.out.println(minHeap.removeMin());

                
            }
            else {
                minHeap.insert(inputBuffer.readRecord());

            }

        }
        
        for(int x = 0; x < 1024; x++) {
            if( minHeap.isFull() ) {
                System.out.println(minHeap.removeMin());

            }
            else {
                minHeap.insert(inputBuffer.readRecord());

            }
        }

        // finishing up the last bits - phase 3
        cleanUp();

        outputBuffer.closeFile();

    }
    
    
    private void cleanUp() {
        while(!minHeap.isEmpty()) {
            minHeap.removeMin().printOut();;
        }
    }

}
