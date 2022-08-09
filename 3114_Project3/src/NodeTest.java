/**
 * testing node class
 * 
 * @author yindrew
 * @version 0.1
 */
public class NodeTest extends student.TestCase {
    private Node<Double> node;
    private Node<Double> nodeAhead;
    private Node<Double> nodePrev;

    /**
     * setting up for the tests
     */
    public void setUp() {
        nodeAhead = new Node<Double>((double)3);
        nodePrev = new Node<Double>((double)7);

        node = new Node<Double>((double)5, nodeAhead, nodePrev);

    }


    /**
     * testing the getter and setter methods
     */
    public void testNode() {
        assertEquals(node.getElement(), 5, 0.0);

        assertEquals(node.getPrev().getElement(), 7, 0.0);

        assertEquals(node.getNext().getElement(), 3, 0.0);
        
        node.setPrev(nodeAhead);
        
        node.setNext(nodePrev);
        
        assertEquals(node.getPrev().getElement(), 3, 0.0);

        assertEquals(node.getNext().getElement(), 7, 0.0);
        
    }
    

}
