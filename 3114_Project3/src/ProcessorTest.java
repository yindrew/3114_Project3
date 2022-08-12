import java.io.IOException;
/**
 * 
 * @author yindrew
 * @version 2022.08.10
 */
public class ProcessorTest extends student.TestCase {
    Processor processor;

    public void setUp() throws IOException {
    }


    public void test() throws IOException {
        processor = new Processor("src/sampleInput16 (2).bin");

    }

}
