import java.io.IOException;

public class ReplacementSelectionTest extends student.TestCase {
    MinHeap minHeap;
    InputBuffer iBuffer;
    InputBuffer iBuffer1;
    MinHeap randomHeap;

    public void setUp() throws IOException {
        iBuffer = new InputBuffer("src/reverseSorted2Block.bin", 2);
        Block block1 = iBuffer.readBlock();
        Block block2 = iBuffer.readBlock();
        minHeap = new MinHeap(2000);
        minHeap.insert(block1.getRecords());
        minHeap.insert(block2.getRecords());

        randomHeap = new MinHeap(5000);
        iBuffer1 = new InputBuffer("src/random.bin", 5);

        while (!randomHeap.isFull() && iBuffer1.getAvaliable() > 0) {
            randomHeap.insert(iBuffer1.readBlock().getRecords());

        }

    }


    public void testMinHeap() {
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.removeMin() + " ");
        }

        assertTrue(systemOut().getHistory().contains("486.0"));

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
