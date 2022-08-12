import java.io.IOException;

/**
 * testing multiway merge
 * 
 * @author yindrew
 * @version 2022.08.12
 */
public class MultiWayMergeTest extends student.TestCase {
    private MultiWayMerge multiMerge;
    private ReplacementSelection rS;

    /**
     * setting up tests
     * 
     * @throws IOException
     */
    public void setUp() throws IOException {
        rS = new ReplacementSelection("reverse20multi.bin",
            "replacement20.bin");
        rS.getRuns();

    }


    /**
     * testing getRunInfo
     * 
     * @throws IOException
     *             if file doesn't exist
     */
    public void testFillHeap() throws IOException {

        // ll is the runsInfo we get from replacement selection
        LinkedList<Integer> ll = rS.runsInfo();
        multiMerge = new MultiWayMerge("replacement20.bin", "testMulti.bin",
            ll);
        // we first fill the heap of multiway merge with the number of runs
        // given
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
        System.out.println("Records read: " + info[0]);
        System.out.println("records total to read: " + info[1]);
        System.out.println("last read key value: " + info[2]);

        System.out.println();

    }


    /**
     * testing increment
     * 
     * @throws IOException
     *             if file doesn't exist
     */
    public void testingIncrement() throws IOException {
        LinkedList<Integer> ll = rS.runsInfo();
        multiMerge = new MultiWayMerge("replacement20.bin", "testRunFile.bin",
            ll);
        multiMerge.fillHeap(ll.size(), 0);
        // increment removes the min and adds from the removed buffers next
        // value
        multiMerge.increment();

        InputBuffer[] ib = multiMerge.getIB();
        // check if the heap updated correctly
        MinHeap heap1 = multiMerge.getHeap();
        assertEquals(2.0, heap1.getPosition(0), 0.0);
        assertEquals(2049.0, heap1.getPosition(2), 0.0);
        assertEquals(6145.0, heap1.getPosition(1), 0.0);

        double[] info2 = ib[2].getBufferInfo();
        System.out.println("Records read: " + info2[0]);
        System.out.println("records total to read: " + info2[1]);
        System.out.println("last read key value: " + info2[2]);
        System.out.println();
    }


    /**
     * testing the whole process
     * 
     * @throws IOException
     *             when file doesnt read
     */
    public void testWhole() throws IOException {
        System.out.println();

        LinkedList<Integer> ll = rS.runsInfo(); // get run info from the rS
        multiMerge = new MultiWayMerge("replacement20.bin", "multiMerge20.bin",
            ll);

        multiMerge.merge(3, 0); // merge 3 run

        InputBuffer iB = new InputBuffer("multiMerge20.bin");

        Record prev = new Record(-Double.MAX_VALUE, Long.valueOf(0));

        int count = 0; // number of values read - should be 10240

        while (iB.getAvaliable() >= 16 || iB.moreToRead()) {
            count++;
            Record cur = iB.readRecord();
            System.out.print(cur.getKey() + " ");
            assert (prev.getKey() <= cur.getKey());
            prev = cur;
        }
        assertEquals(count, 10240);

    }


    /**
     * testing the whole process
     * 
     * @throws IOException
     *             when file doesnt read
     */
    public void testWhole1() throws IOException {
        System.out.println();
        System.out.println();
        ReplacementSelection rS1 = new ReplacementSelection("reverse60.bin",
            "replacement60.bin");
        rS1.getRuns();
        System.out.println();

        LinkedList<Integer> ll = rS1.runsInfo(); // get run info from the rS
        multiMerge = new MultiWayMerge("replacement60.bin", "Merge60.bin", ll);

        multiMerge.merge(8, 0); // merge 3 run

        InputBuffer iB1 = new InputBuffer("Merge60.bin");

        Record prev = new Record(-Double.MAX_VALUE, Long.valueOf(0));

        int count = 0; // number of values read - should be 10240

        while (iB1.getAvaliable() >= 16 || iB1.moreToRead()) {
            count++;
            Record cur = iB1.readRecord();
            System.out.print(cur.getKey() + " ");
            assert (prev.getKey() <= cur.getKey());
            prev = cur;
        }
        assertEquals(count, 30720);

    }

}
