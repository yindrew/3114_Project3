import java.io.IOException;
/**
 * 
 * @author yindrew
 * @version 2022.08.10
 */
public class ProcessorTest extends student.TestCase {

    private Processor processor;

    /**
     * testing the processor
     * @throws IOException if file doesn't exist
     */
    public void test() throws IOException {
        processor = new Processor("src/sampleInput16 (2).bin");

    }

}
