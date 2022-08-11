/**
 * doubly linked list
 * 
 * @author yindrew
 * @version 2022.08.10
 * @param <T>
 *            type
 */
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * constructor
     */
    public LinkedList() {
        size = 0;
    }


    /**
     * getter method for the head node
     * 
     * @return head
     */
    public Node<T> getHead() {
        return head;
    }


    /**
     * getter method for the tail node
     * 
     * @return tail
     */
    public Node<T> getTail() {
        return tail;
    }


    /**
     * get the size
     * 
     * @return size
     */
    public int size() {
        return size;
    }

    
    /**
     * is empty or not
     * 
     * @return whether or not the linked list is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * adding values to the linked list
     * 
     * @param element
     *            element to be added
     */
    public void add(T element) {
        Node<T> tmp = new Node<T>(element, null, tail);
        if (tail != null) {
            tail.setNext(tmp);
        }
        tail = tmp;
        if (head == null) {
            head = tmp;
        }
        size++;
    }

}
