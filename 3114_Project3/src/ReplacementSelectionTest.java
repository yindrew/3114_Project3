import java.io.IOException;
/**
 * testing replacementSelection
 * @author yindrew
 * @version 2022.08.10
 */
public class ReplacementSelectionTest extends student.TestCase {
    private ReplacementSelection rSelect;

    public void setUp() throws IOException {
        
        rSelect = new ReplacementSelection("src/reverse20.bin");

    }
    
    
    
    
    public void testGetRuns() throws IOException {
        rSelect.getRuns();
        assertTrue(systemOut().getHistory().contains("3586.0 3587.0 "));
        assertTrue(systemOut().getHistory().contains("6103.0 6104.0"));

    }




}






                  
               