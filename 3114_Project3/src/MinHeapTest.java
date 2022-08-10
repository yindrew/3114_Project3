
/**
 * testing the MinHeap class
 * 
 * @author yindrew
 * @version 0.1
 */
public class MinHeapTest extends student.TestCase {
    private MinHeap reverse;
    private MinHeap full;
    private MinHeap complete;

    /**
     * setting up the tests
     */
    public void setUp() {
        
        Record[] h = new Record[4096];
        for (int x = 10; x > 0; x--) {
            h[10 - x] = new Record(x, Long.valueOf(0));
        }
        reverse = new MinHeap(h, 10);
        
        complete = new MinHeap(5);
        Record[] forwardRecord = new Record[5];
        for(int x = 0; x < 5; x++) {
            forwardRecord[x] = new Record(x, Long.valueOf(0));
        }
        complete.insert(forwardRecord);

    }
    
    

    /**
     * testing a heap where the input record is reversed
     */
    public void testReverseHeap() {

        assertEquals(reverse.activeSize(), 10);

        assertFalse(reverse.isFull());

        assertTrue(reverse.isLeaf(7));
        assertTrue(reverse.isLeaf(8));
        assertTrue(reverse.isLeaf(9));
        assertFalse(reverse.isLeaf(1));

        assertEquals(reverse.getPosition(1), (double)2, 0.00);
        assertEquals(reverse.getPosition(2), (double)4, 0.00);
        assertEquals(reverse.getPosition(7), (double)10, 0.00);
        assertEquals(reverse.getPosition(9), (double)9, 0.00);
        reverse.insert(new Record(0, Long.valueOf(3)));
        assertEquals(reverse.activeSize(), 11);
        assertEquals(reverse.getPosition(0), (double)0, 0.00);
        assertEquals(reverse.getPosition(10), (double)6, 0.00);
        assertEquals(reverse.getPosition(9), (double)9, 0.00);
        assertEquals(reverse.getPosition(1), (double)1, 0.00);

        assertEquals(reverse.removeMin().getKey(), (double)0, 0.00);
        assertEquals(reverse.activeSize(), 10);
    }


    /**
     * testing a full heap
     */
    public void testIsFull() {
        Record[] fullRecord = new Record[4096];
        for (int x = 0; x < 4096; x++) {
            fullRecord[x] = new Record(x, Long.valueOf(0));
        }
        full = new MinHeap(fullRecord, 4096);
        assertTrue(full.isFull());
        

    }
    
    /**
     * testing a heap including removing the min and adding things after
     */
    public void testCompleteHeap() {
        // comma is inactive, period is active
        assertTrue(complete.isFull()); // 0 1 2 3 4 ., 
        assertEquals(complete.activeSize(), 5);            
        assertEquals(complete.getPosition(0), 0, 0.0);          
        
        complete.removeMin(); // 1 3 2 4 . 0,
        complete.removeMin(); // 2 3 4 . 1 0,
        assertFalse(complete.isFull());
        assertEquals(complete.activeSize(), 3);
        assertEquals(complete.getPosition(0), 2, 0.0);
        
        complete.insert(.5); // 2 3 4 . 1 , 1/2
        assertEquals(complete.inactiveSize(), 1);
        assertEquals(complete.getPosition(4), .5, 0.0);
        assertEquals(complete.activeSize(), 3);
        
        complete.insert(6); // 2 3 4 6 ., 1/2
        assertEquals(complete.getPosition(3), 6, 0.0);
        
        Record min = complete.removeMin(); // 3 6 4 . 2 , 1/2
        assertEquals(min.getKey(), 2, 0.0);
        assertEquals(complete.getPosition(0), 3, 0.0);
        
        complete.insert(1.5); // 3 6 4 ., 3/2 1/2
        assertEquals(complete.inactiveSize(), 2);
        assertEquals(complete.getPosition(4), .5, 0.0);
        assertEquals(complete.getPosition(3), 1.5, 0.0);
        assertEquals(complete.activeSize(), 3);
        
        
        complete.removeMin(); 
        complete.removeMin();
        complete.removeMin(); // 6 4 3 ., 3/2 1/2
        
        assertEquals(complete.activeSize(), 0);
        assertEquals(complete.inactiveSize(), 2);
        
        Record min1 = complete.removeMin();
        assertEquals(min1.getKey(), .5, 0.0);
        Record min2 = complete.removeMin();
        assertEquals(min2.getKey(), 1.5, 0.0);
        assertNull(complete.removeMin());
          
        
    }
    
    
    public void testInsert() {
        complete.removeMin(); 
        complete.insert(-1);
        //complete.insert(-2);

    }

}
