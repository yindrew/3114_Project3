import java.io.IOException;

public class ReplacementSelectionTest extends student.TestCase {
    MinHeap minHeap;
    InputBuffer iBuffer;
    
    
    public void setUp() throws IOException {
        iBuffer = new InputBuffer("src/reverseSorted2Block.bin", 2);
        Block block1 = iBuffer.readBlock();
        Block block2 = iBuffer.readBlock();

        minHeap = new MinHeap();
        minHeap.insert(block1.getRecords());
        minHeap.insert(block2.getRecords());
        
        
        
    }
    
    
    public void testMinHeap() {
        while(!minHeap.isEmpty()) {
            System.out.print(minHeap.removeMin() + " ");
        }
        
        assertTrue(systemOut().getHistory().contains("486.0"));

    }

}
