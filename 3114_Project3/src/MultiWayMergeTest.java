import java.io.FileNotFoundException;
import java.io.IOException;

public class MultiWayMergeTest extends student.TestCase {
    private newMultiwayMerge multiMerge;
    private ReplacementSelection rS;

    /**
     * setting up tests
     * 
     * @throws IOException
     */
    public void setUp() throws IOException {
        rS = new ReplacementSelection("src/reverse20.bin");
        rS.getRuns();

    }


    /**
     * testing getRunInfo
     * @throws IOException 
     */
    public void testRunInfoGetter() throws IOException {
        LinkedList<Integer> ll = rS.runsInfo();
        multiMerge = new newMultiwayMerge("src/reverse20.bin",
            "src/testRunFile.bin", ll);
        
        multiMerge.fillHeap();
        

    }


    public void testing() {

    }
}
