/**
 * Node class
 * 
 * @author yindrew
 * @version 2022.08.10
 * @param <T>
 *            type
 */
public class Node<T> {
    private T element;
    private Node<T> next;
    private Node<T> prev;

    /**
     * basic constructor
     * 
     * @param i
     *            value of node
     */
    public Node(T i) {
        this.element = i;
    }


    /**
     * constructor for node class
     * 
     * @param element
     *            value of node
     * @param next
     *            next node
     * @param prev
     *            previous node
     */
    public Node(T element, Node<T> next, Node<T> prev) {
        this.element = element;
        this.next = next;
        this.prev = prev;
    }


    /**
     * getter method for element
     * 
     * @return element
     */
    public T getElement() {
        return element;
    }
    
    /**
     * setter method for next
     * 
     * @param nextNode next node
     */
    public void setNext(Node<T> nextNode) {
        next = nextNode;
    }

    /**
     * setter method for previous
     * 
     * @param prevNode node that comes before
     */
    public void setPrev(Node<T> prevNode) {
        prev = prevNode;
    }

    /**
     * getter method for next
     * 
     * @return next node
     */
    public Node<T> getNext() {
        return next;
    }


    /**
     * getter method for previous
     * 
     * @return previous node
     */
    public Node<T> getPrev() {
        return prev;
    }

}
