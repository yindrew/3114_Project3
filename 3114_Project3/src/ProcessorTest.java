import java.io.IOException;

public class ProcessorTest extends student.TestCase {
    Processor processor;

    public void setUp() throws IOException {
    }


    public void testing() throws IOException {
        // processor = new Processor("src/reverse20.bin"); // 10240 10239
        // InputBuffer Ib = new InputBuffer("src/reverse20.bin"); // 1 2 3

        // Ib.readRecord().printOut(); // 1
        // Ib.readRecord().printOut(); // 2
        // System.out.println();
        //InputBuffer iP = new InputBuffer("src/reverse20.bin");


    }


    public void test() throws IOException {
        processor = new Processor("src/reverse20.bin");

    }

}
