import java.io.IOException;

public class ReplacementSelectionTest extends student.TestCase {
    private MinHeap minHeap;
    private InputBuffer iBuffer;
    private InputBuffer iBuffer1;
    private MinHeap randomHeap;
    private ReplacementSelection rSelect;

    public void setUp() throws IOException {
        iBuffer = new InputBuffer("src/reverseSorted2Block.bin");
        Block block1 = iBuffer.readBlock();
        Block block2 = iBuffer.readBlock();
        minHeap = new MinHeap(2000);
        minHeap.insert(block1.getRecords());
        minHeap.insert(block2.getRecords());

        
        randomHeap = new MinHeap(5000);
        iBuffer1 = new InputBuffer("src/random.bin");
        while (!randomHeap.isFull() && iBuffer1.getAvaliable() > 0) {
            randomHeap.insert(iBuffer1.readBlock().getRecords());

        }
        
        
        rSelect = new ReplacementSelection("src/random20.bin");

    }
    
    
    public void testGetRuns() throws IOException {
        rSelect.getRuns();
    }


    public void testMinHeap() {
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.removeMin() + " ");
        }

        assertTrue(systemOut().getHistory().contains("486.0 487.0"));

        System.out.println();
    }


    public void testMinHeapRandom() {
        assertEquals(randomHeap.heapSize(), 2560);

        System.out.println(randomHeap.heapSize());
        while (!randomHeap.isEmpty()) {
            System.out.print(randomHeap.removeMin() + " ");
        }

        assertEquals(randomHeap.heapSize(), 0);

        System.out.println();

    }

}
