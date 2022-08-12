import java.io.IOException;

/**
 * testing main method
 * @author yindrew
 * @version 2022.08.10
 */
public class ExternalsortTest extends student.TestCase {
    
    /**
     * testing the main method
     * @throws IOException if file doesn't exist
     */
    public void testMain() throws IOException {
        Externalsort.main(new String[] {"src/reverse20.bin"});
        
        assertTrue(systemOut().getHistory().contains(
            "1025 1025.0 1537 1537.0 2049 2049.0"));

    }

}
