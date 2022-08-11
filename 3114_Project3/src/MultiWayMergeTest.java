import java.io.IOException;

public class MultiWayMergeTest extends student.TestCase {
    private MultiWayMerge multiMerge;
    private ReplacementSelection rS;

    /**
     * setting up tests
     * 
     * @throws IOException
     */
    public void setUp() throws IOException {
        rS = new ReplacementSelection("src/reverse20.bin");
        rS.getRuns();

    }


    /**
     * testing getRunInfo
     * @throws IOException 
     */
    public void testFillHeap() throws IOException {
        
        // ll is the runsInfo we get from replacement selection
        LinkedList<Integer> ll = rS.runsInfo();
        multiMerge = new MultiWayMerge("src/runFile.bin",
            "src/testRunFile.bin", ll);
        // we first fill the heap of multiway merge with the number of runs given 
        // by the replacement selection
        multiMerge.fillHeap(ll.size(), 0);
        // we check if the heap is proper
        MinHeap heap = multiMerge.getHeap();
        assertEquals(1.0, heap.getPosition(0), 0.0);
        assertEquals(2049.0, heap.getPosition(2), 0.0);
        assertEquals(6145.0, heap.getPosition(1), 0.0);
        // we check if the bufferInfo for inputBuffer is correct
        InputBuffer[] ib = multiMerge.getIB();
        double[] info = ib[0].getBufferInfo();
        System.out.println("Records read: " + info[0] );
        System.out.println("records total to read: " + info[1] );
        System.out.println("last read key value: " + info[2] );
        
        
        System.out.println();


    }


    public void testingIncrement() throws IOException {
        LinkedList<Integer> ll = rS.runsInfo();
        multiMerge = new MultiWayMerge("src/runFile.bin",
            "src/testRunFile.bin", ll);
        multiMerge.fillHeap(ll.size(), 0);
        // increment removes the min and adds from the removed buffers next value
        multiMerge.increment();
        
        InputBuffer[] ib = multiMerge.getIB();
        // check if the heap updated correctly
        MinHeap heap1 = multiMerge.getHeap();
        assertEquals(2.0, heap1.getPosition(0), 0.0);
        assertEquals(2049.0, heap1.getPosition(2), 0.0);
        assertEquals(6145.0, heap1.getPosition(1), 0.0);
        
        double[] info2 = ib[2].getBufferInfo();
        System.out.println("Records read: " + info2[0] );
        System.out.println("records total to read: " + info2[1] );
        System.out.println("last read key value: " + info2[2] );
        System.out.println();
    }
    
    
    
    public void testWhole() throws IOException {
        System.out.println();
        System.out.println();

        LinkedList<Integer> ll = rS.runsInfo();
        multiMerge = new MultiWayMerge("src/runFile.bin",
            "src/testRunFile.bin", ll);
        
        multiMerge.merge(3, 0); // merge 3 run 

        InputBuffer IB = new InputBuffer("src/testRunFile.bin");
        
        Record prev = new Record(-Double.MAX_VALUE, Long.valueOf(0));
        int count = 0;
        while(IB.getAvaliable() >= 16 || IB.moreToRead()) {
            count++;
            Record cur = IB.readRecord();
            assert(prev.getKey() <= cur.getKey());
            prev = cur;
        }
        assertEquals(count, 10240);

        
    }
    
    
    
}
