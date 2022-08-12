import java.io.IOException;

/**
 * testing replacementSelection
 * 
 * @author yindrew
 * @version 2022.08.10
 */
public class ReplacementSelectionTest extends student.TestCase {
    private ReplacementSelection rSelect;
    private ReplacementSelection rSelect1;

    /**
     * setting up the tests
     */
    public void setUp() throws IOException {

        rSelect = new ReplacementSelection("reverse20.bin",
            "reverse20replaced.bin");
        rSelect1 = new ReplacementSelection("reverse2.bin",
            "reverse2replaced.bin");

    }


    /**
     * testing getRuns when more than 8 blocks
     * 
     * @throws IOException
     *             when files don't work
     */
    public void testGetRuns() throws IOException {
        rSelect.getRuns();
        InputBuffer ip = new InputBuffer("reverse20replaced.bin");

        LinkedList<Integer> ll = rSelect.runsInfo();
        Node<Integer> node = ll.getHead();
        System.out.print("number of records in each run: ");
        while (node != null) {
            System.out.print(node.getElement() + " ");
            node = node.getNext();
        }
        assertTrue(systemOut().getHistory().contains("4096 4096 2048"));
        System.out.println();

        int count = 0;
        while (ip.moreToRead() || ip.getAvaliable() >= 16) {
            Record record = ip.readRecord();
            record.printOut();
            count++;
        }
        assertTrue(count == 10240);
        System.out.println();

    }


    /**
     * testing a run with less than 8 blocks
     * 
     * @throws IOException
     *             when files dont work
     */
    public void testRuns1() throws IOException {
        rSelect1.getRuns();
        InputBuffer ip = new InputBuffer("reverse2replaced.bin");

        int count = 0;
        double prev = -10;
        while (ip.moreToRead() || ip.getAvaliable() >= 16) {
            Record record = ip.readRecord();
            record.printOut();
            assertTrue(record.getKey() >= prev);
            prev = record.getKey();
            count++;
        }
        assertTrue(count == 1024);
        System.out.println();
    }

}
