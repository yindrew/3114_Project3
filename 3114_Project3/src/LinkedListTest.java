/**
 * testing Linked List
 * 
 * @author yindrew
 * @version 2022.08.10
 */
public class LinkedListTest extends student.TestCase {
    private LinkedList<Integer> linkedList;

    /**
     * setting up tests
     */
    public void setUp() {
        linkedList = new LinkedList<Integer>();

    }


    /**
     * testing methods
     */
    public void testAddLast() {
        assertEquals(linkedList.size(), 0);
        assertTrue(linkedList.isEmpty());

        linkedList.add(5);

        assertTrue(linkedList.getHead().getElement() == 5);
        assertTrue(linkedList.getTail().getElement() == 5);

        assertEquals(linkedList.size(), 1);
        assertFalse(linkedList.isEmpty());

        linkedList.add(7);

        assertTrue(linkedList.getHead().getElement() == 5);
        assertTrue(linkedList.getTail().getElement() == 7);

        assertTrue(linkedList.getHead().getNext().getElement() == 7);
        assertTrue(linkedList.getTail().getPrev().getElement() == 5);
        linkedList.add(9);

        //assertTrue(linkedList.getHead().getNext().getNext().getElement() == 9);

    }
    
    /**
     * testing toArray
     */
    public void testToArr() {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        ll.add(5);
        ll.add(6);
        ll.add(10);
        ll.add(4);
        
        int[] testing = ll.toArray();
        Node node = ll.getHead();
        for (int x = 0; x < testing.length; x++) {
            assertEquals(testing[x], node.getElement());
            node = node.getNext();
        }
        assertEquals(testing[0], 5);
        assertEquals(testing[3], 4);
    }

}
