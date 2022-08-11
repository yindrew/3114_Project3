import java.io.IOException;
/**
 * testing replacementSelection
 * @author yindrew
 * @version 2022.08.10
 */
public class ReplacementSelectionTest extends student.TestCase {
    private ReplacementSelection rSelect;
    

    /**
     * setting up the tests
     */
    public void setUp() throws IOException {
        
        rSelect = new ReplacementSelection("src/reverse20.bin");

    }
    
    
    
    /**
     * testing getRuns
     * @throws IOException when files don't work
     */
    public void testGetRuns() throws IOException {
        rSelect.getRuns();
        assertTrue(systemOut().getHistory().contains("3586.0 3587.0 "));
        assertTrue(systemOut().getHistory().contains("6103.0 6104.0"));
        InputBuffer ip = new InputBuffer("src/runFile.bin");
        assertEquals(ip.readRecord().getKey(), 6145, 0.0);
        
        assertEquals(ip.readRecord().getKey(), 6146, 0.0);
        assertEquals(ip.readRecord().getKey(), 6147, 0.0);
        assertEquals(ip.readRecord().getKey(), 6148, 0.0);
        
        LinkedList<Integer> ll = rSelect.runsInfo();
        System.out.println();

        Node<Integer> node = ll.getHead();
        while(node != null) {
            System.out.print(node.getElement() + " ");
            node = node.getNext();
        }
        assertTrue(systemOut().getHistory().contains("4096 4096 2049"));



    }




}






                  
               