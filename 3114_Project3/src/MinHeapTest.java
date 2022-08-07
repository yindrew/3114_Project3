/**
 * testing the MinHeap Class
 * 
 * @author yindrew
 * @version 0.1
 */
import java.util.Random;

public class MinHeapTest extends student.TestCase {
    private MinHeap minHeap;
    private Record[] h;

    /**
     * setting up the tests
     */
    public void setUp() {
        h = new Record[4096];
        for (int x = 10; x > 0; x--) {
            h[10 - x] = new Record(x, Long.valueOf(0));
        }

        minHeap = new MinHeap(h, 10);

    }


    /**
     * testing heapSize
     */
    public void testHeapSize() {
        assertEquals(minHeap.heapSize(), 10);

    }


    /**
     * testing isLeaf
     */
    public void testIsLeaf() {
        assertTrue(minHeap.isLeaf(7));
        assertTrue(minHeap.isLeaf(8));
        assertTrue(minHeap.isLeaf(9));
        assertFalse(minHeap.isLeaf(1));

    }


    /**
     * testing insert
     */
    public void testInsert() {
        assertEquals(minHeap.getPosition(1), (double)2, 0.00);
        assertEquals(minHeap.getPosition(2), (double)4, 0.00);
        assertEquals(minHeap.getPosition(7), (double)10, 0.00);
        assertEquals(minHeap.getPosition(9), (double)9, 0.00);

        minHeap.insert(new Record(0, Long.valueOf(3)));
        assertEquals(minHeap.heapSize(), 11);
        assertEquals(minHeap.getPosition(0), (double)0, 0.00);
        assertEquals(minHeap.getPosition(10), (double)6, 0.00);
        assertEquals(minHeap.getPosition(9), (double)9, 0.00);
        assertEquals(minHeap.getPosition(1), (double)1, 0.00);

    }


    /**
     * testing removeMin
     */
    public void testRemoveMin() {
        assertEquals(minHeap.removeMin().getKey(), (double)1, 0.00);
        assertEquals(minHeap.heapSize(), 9);

    }


    /**
     * testing remove
     */
    public void testRemove() {
        assertEquals(minHeap.remove(2).getKey(), (double)4, 0.00);
        assertEquals(minHeap.remove(7).getKey(), (double)10, 0.00);

    }

    
    
    

}
