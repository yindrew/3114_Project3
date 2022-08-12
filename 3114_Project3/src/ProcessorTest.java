import java.io.IOException;
/**
 * 
 * @author yindrew
 * @version 2022.08.10
 */
public class ProcessorTest extends student.TestCase {
    
    /**
     * set up the files
     * @throws IOException 
     */
    public void setUp() throws IOException {
        GenBinaryDataFile.generateReverseSorted("reverse8multi.bin", 8);
        GenBinaryDataFile.generateReverseSorted("reverse20multi.bin", 20);
        GenBinaryDataFile.generateReverseSorted("reverse60multi.bin", 60);

    }
    private Processor processor;
    private Processor processor1;
    private Processor processor2;
    /**
     * testing the processor
     * @throws IOException if file doesn't exist
     */
    public void test() throws IOException {
        processor2 = new Processor("sampleInput16.bin");
//        System.out.println();
//        System.out.println("---------------------------------------------------------------");
//        System.out.println("---------------------------------------------------------------");
//        System.out.println();
//        
//        System.out.println();
//        System.out.println("---------------------------------------------------------------");
//        System.out.println("---------------------------------------------------------------");
//        System.out.println("---------------------------------------------------------------");
//        processor = new Processor("reverse20multi.bin");
//        System.out.println();
//        System.out.println("---------------------------------------------------------------");
//        System.out.println("---------------------------------------------------------------");
//        System.out.println("---------------------------------------------------------------");
//
//        processor1 = new Processor("reverse60multi.bin");


    }

}
