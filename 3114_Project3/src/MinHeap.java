/**
 * min heap implementation
 * uses openDSA max heap and changed it to a min heap
 * 
 * @author yindrew
 * @version 0.1
 */
class MinHeap {
    private Record[] heap;
    private int maxSize = 4096; // maximum 8 blocks
    private int n; // numbers of records in active heap
    private Record lastRemoved = new Record(-Double.MAX_VALUE, Long.valueOf(0));
    private int inactive = 0; // numbers of records in inactive heap
    private LinkedList<Double> runsInfo; // need to finish this

    
    
    /**
     * empty constructor
     */
    public MinHeap(int maxSize) {
        heap = new Record[maxSize];
        this.maxSize = maxSize;
        n = 0;

    }


    /**
     * constructor
     * 
     * @param h
     *            array being input in
     * @param heapSize
     *            number of items in the array
     */
    public MinHeap(Record[] h, int heapSize) {
        heap = h;
        n = heapSize;
        buildHeap();
    }


    /**
     * gets the value at position index
     * 
     * @param index
     *            the index of the heap
     * @return the value of index in heap
     */
    public double getPosition(int index) {
        return heap[index].getKey();

    }


    /**
     * gets the inactive size
     * 
     * @return the inactive size
     */
    public int inactiveSize() {
        return inactive;
    }


    /**
     * gets the heap size
     * 
     * @return the heap size
     */
    public int heapSize() {
        return n;
    }


    /**
     * whether or not the position is a leaf
     * 
     * @param pos
     *            position in heap
     * @return whether or not the position is a leaf
     */
    public boolean isLeaf(int pos) {
        return (n / 2 <= pos) && (pos < n);
    }


    /**
     * gets left child of current node
     * 
     * @param pos
     *            position of current node
     * @return left child of current node
     */
    public static int leftChild(int pos) {
        return 2 * pos + 1;
    }


    /**
     * gets right child of current node
     * 
     * @param pos
     *            position of current node
     * @return right child of the current node
     */
    public static int rightChild(int pos) {
        return 2 * pos + 2;
    }


    /**
     * get the parent of the current node
     * 
     * @param pos
     *            position of current node
     * @return the parent of the current node
     */
    public static int parent(int pos) {
        return (pos - 1) / 2;
    }


    /**
     * easy insert
     * 
     * @param key
     *            value
     */
    public void insert(double key) {
        insert(new Record(key, Long.valueOf(0)));
    }


    /**
     * inserting a Record into the heap
     * 
     * @param key
     *            value being inserted into the heap
     */
    public void insert(Record record) {
        assert n < maxSize : "Heap is full; cannot insert";
        if (record.getKey() < lastRemoved.getKey()) { // put record into
                                                      // inactive
            inactive++;
            heap[maxSize - inactive] = record;
        }
        else {
            heap[n] = record;
            n++;
            siftUp(n - 1);
        }

    }


    /**
     * inserting multiple records into the heap
     * 
     * @param records
     *            record array being inserted
     */
    public void insert(Record[] records) {
        for (int x = 0; x < records.length; x++) {
            insert(records[x]);
        }
    }


    /**
     * builds the heap from the input array
     */
    private void buildHeap() {
        for (int i = parent(n - 1); i >= 0; i--) {
            siftDown(i);
        }
    }


    /**
     * sifts the position of each element down
     * 
     * @param pos
     *            position of the node being sifted down
     */
    private void siftDown(int pos) {
        // assert (0 <= pos && pos < n) : "Invalid heap position";
        while (!isLeaf(pos)) {
            int child = leftChild(pos);
            if ((child + 1 < n) && isLessThan(child + 1, child)) {
                child = child + 1; // we get the lesser of the 2 children
            }
            if (!isLessThan(child, pos)) {
                return; // stop early as the parent is smaller its child
            }
            swap(pos, child);
            pos = child; // keep sifting down
        }
    }


    public boolean isEmpty() {
        return (n == 0 && inactive == 0);
    }


    /**
     * sifts the position of a element up
     * 
     * @param pos
     *            position of the node being sifted up
     */
    private void siftUp(int pos) {
        assert (0 <= pos && pos < n) : "Invalid heap position";
        while (pos > 0) {
            int parent = parent(pos);
            if (isLessThan(parent, pos)) {
                return; // stop early
            }
            swap(pos, parent);
            pos = parent; // keep sifting up
        }
    }


    /**
     * remove the min element from the heap
     * 
     * @return the min element from the heap
     */
    public Record removeMin() {

        if (n == 1) { // remove last element in heap
            n--;
            lastRemoved = heap[n];
            return heap[n];
        }

        else if (n == 0 && inactive != 0) { // active heap empty
            int shift = maxSize - inactive;
            for (int x = 0; x < inactive; x++) {
                heap[x] = heap[x + shift];
            }
            n = inactive;
            inactive = 0;
            this.buildHeap();
            return removeMin();
        }

        else if (n == 0 && inactive == 0) { // active heap and inactive empty
            return null;
        }

        else { // remove the min element in heap
            n--;
            swap(0, n);
            siftDown(0);
            lastRemoved = heap[n];
            return heap[n];
        }

    }


    /**
     * returns the last removed element
     * 
     * @return last removed element
     */
    public Record getLastRemoved() {
        return lastRemoved;
    }


    /**
     * swapping the values of 2 positions
     * 
     * @param pos1
     *            position of first element
     * @param pos2
     *            position of second element
     */
    private void swap(int pos1, int pos2) {
        Record temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }


    /**
     * checking if the heap is full or not
     * 
     * @return whether or not the heap is full
     */
    public boolean isFull() {
        return (n == maxSize);
    }


    public int shiftLeft(int cur, int x) {
        return cur - x;

    }


    /**
     * comparing if one element is less than the other
     * 
     * @param pos1
     *            position of first element
     * @param pos2
     *            position of second element
     * @return whether or not first element is smaller than second
     */
    private boolean isLessThan(int pos1, int pos2) {
        if (heap[pos1].getKey() < heap[pos2].getKey()) {
            return true;
        }
        else {
            return false;
        }
    }
}
