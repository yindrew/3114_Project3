/**
 * min heap implementation
 * uses openDSA max heap and changed it to a min heap
 * 
 * @author yindrew
 * @version 2022.08.10
 */
class MinHeap {
    private Record[] heap;
    private int maxSize = 4096;
    private int activeNum;
    private int inactiveNum = 0;
    private Record lastRemoved = new Record(-Double.MAX_VALUE, Long.valueOf(0));
    private LinkedList<Integer> runsInfo = new LinkedList<Integer>();
    private int recordsInRun = 0;

    /**
     * empty constructor
     */
    public MinHeap(int maxSize) {
        heap = new Record[maxSize];
        this.maxSize = maxSize;
        activeNum = 0;

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
        activeNum = heapSize;
        buildHeap();
    }
    
    /**
     * gets run info and returns it
     * @return runs info
     */
    public LinkedList<Integer> getRunInfo() {
        return runsInfo;
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
        return inactiveNum;
    }


    /**
     * gets the heap size
     * 
     * @return the heap size
     */
    public int activeSize() {
        return activeNum;
    }


    /**
     * whether or not the position is a leaf
     * 
     * @param pos
     *            position in heap
     * @return whether or not the position is a leaf
     */
    public boolean isLeaf(int pos) {
        return (activeNum / 2 <= pos) && (pos < activeNum);
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

        assert (activeNum
            + inactiveNum) < maxSize : "Heap is full; cannot insert";

        if (record.getKey() < lastRemoved.getKey()) {
            inactiveNum++;
            heap[maxSize - inactiveNum] = record;
        }
        else {
            heap[activeNum] = record;
            activeNum++;
            siftUp(activeNum - 1);
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
        for (int i = parent(activeNum - 1); i >= 0; i--) {
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
            if ((child + 1 < activeNum) && isLessThan(child + 1, child)) {
                child = child + 1; // we get the lesser of the 2 children
            }
            if (!isLessThan(child, pos)) {
                return; // stop early as the parent is smaller its child
            }
            swap(pos, child);
            pos = child; // keep sifting down
        }
    }


    /**
     * checks if the heap is empty
     * 
     * @return whether or not the heap is empty
     */
    public boolean isEmpty() {
        return (activeNum == 0 && inactiveNum == 0);
    }


    /**
     * sifts the position of a element up
     * 
     * @param pos
     *            position of the node being sifted up
     */
    private void siftUp(int pos) {
        assert (0 <= pos && pos < activeNum) : "Invalid heap position";
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
     * remove the min element from the heap.
     * precondition: 1. heap is full
     * 2. clean up
     * 
     * @return the min element from the heap
     */
    public Record removeMin() {

        if (activeNum == 1) { // remove last element in heap
            activeNum--;
            recordsInRun++;
            lastRemoved = heap[activeNum];
            runsInfo.add(recordsInRun);
            recordsInRun = 0;

            return heap[activeNum];
        }

        else if (activeNum == 0 && inactiveNum != 0) { // active heap empty reset
            int shift = maxSize - inactiveNum;
            for (int x = 0; x < inactiveNum; x++) {
                heap[x] = heap[x + shift];
            }
            activeNum = inactiveNum;
            inactiveNum = 0;
            lastRemoved = new Record(-Double.MAX_VALUE, Long.valueOf(0));
            this.buildHeap();
            System.out.println();

            return removeMin();
        }

        else if (activeNum == 0 && inactiveNum == 0) { // active heap and
            return null;
        }

        else { // remove the min element in heap
            recordsInRun++;
            activeNum--;
            swap(0, activeNum);
            siftDown(0);
            lastRemoved = heap[activeNum];
            return heap[activeNum];
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
        return ((activeNum + inactiveNum) == maxSize);
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
