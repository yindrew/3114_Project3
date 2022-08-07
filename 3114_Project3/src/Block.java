public class Block {
    int n = 0; // curr size
    int size = 8192; // maximum size
    Record[] recordArr = new Record[512]; // one block

    
    public Block(byte[] arr) {
        for(int x = 0; x < 512; x ++) {
            byte[] sliced = slice(arr, x*16, x*16 + 16);
            Record record = new Record(sliced);
            recordArr[x] = record;
        }
        
    }
    
    
    private byte[] slice(byte[] arr, int start, int end) {
        byte[] slice = new byte[end - start];
        
        for (int i = 0; i < slice.length; i++) {
            slice[i] = arr[start + i];
        }
        
        return slice;
    }
    
    
    public String toString() {
        String sum = "";
        for(int x = 0; x < 512; x++) {
            sum = sum + recordArr[x].toString() + " ";
            
        }
        return sum;
    }

}
