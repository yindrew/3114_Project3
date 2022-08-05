/**
 * min heap implementation
 * uses openDSA max heap and changed it to a min heap
 * 
 * @author yindrew
 *
 */
class MinHeap {
    private double[] heap; // Pointer to the heap array
    private int maxSize = 4096; // 8 * 512. 8 blocks, each block has 512 records
    private int n; // Number of things now in heap

    // Constructor supporting preloading of heap contents
    public MinHeap(double[] h, int heapSize) {
        heap = h;
        n = heapSize;
        buildHeap();
    }

    /**
     * gets the value at position index
     * @param index the index of the heap
     * @return the value of index in heap
     */
    public double getPosition(int index) {
        return heap[index];
        
    }

    // Return current size of the heap
    public int heapSize() {
        return n;
    }


    // Return true if pos a leaf position, false otherwise
    public boolean isLeaf(int pos) {
        return (n / 2 <= pos) && (pos < n);
    }


    // Return position for left child of pos
    public static int leftChild(int pos) {
        return 2 * pos + 1;
    }


    // Return position for right child of pos
    public static int rightChild(int pos) {
        return 2 * pos + 2;
    }


    // Return position for parent
    public static int parent(int pos) {
        return (pos - 1) / 2;
    }


    // Insert val into heap
    public void insert(double key) {
        assert n < maxSize : "Heap is full; cannot insert";
        heap[n] = key;
        n++;
        siftUp(n - 1);
    }


    // Heapify contents of Heap
    private void buildHeap() {
        for (int i = parent(n - 1); i >= 0; i--) {
            siftDown(i);
        }
    }


    // Moves an element down to its correct place
    private void siftDown(int pos) {
        assert (0 <= pos && pos < n) : "Invalid heap position";
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


    // Moves an element up to its correct place
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


    // Remove and return maximum value
    public double removeMin() {
        assert n > 0 : "Heap is empty; cannot remove";
        n--;
        swap(0, n); // Swap maximum with last value
        siftDown(0); // Put new heap root val in correct place
        return heap[n];
    }


    // Remove and return element at specified position
    public double remove(int pos) {
        assert (0 <= pos && pos < n) : "Invalid heap position";
        n--;
        swap(pos, n); // Swap with last value
        update(pos); // Move other value to correct position
        return heap[n];
    }


    // Modify the value at the given position
    public void modify(int pos, double newVal) {
        assert (0 <= pos && pos < n) : "Invalid heap position";
        heap[pos] = newVal;
        update(pos);
    }


    // The value at pos has been changed, restore the heap property
    private void update(int pos) {
        siftUp(pos); // priority goes up
        siftDown(pos); // unimportant goes down
    }


    // swaps the elements at two positions
    private void swap(int pos1, int pos2) {
        double temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }


    // does comparison used for checking heap validity
    private boolean isLessThan(int pos1, int pos2) {
        if (heap[pos1] < heap[pos2]) {
            return true;
        }
        else {
            return false;
        }
    }
}
