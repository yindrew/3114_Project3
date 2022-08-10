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
        assertTrue(linkedList.getHead().getNext().getNext().getElement() == 9);

    }

}
