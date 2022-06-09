import org.junit.Test;
import static org.junit.Assert.*;

//to test offByN
public class TestOffByN {

    @Test
    public void testOffByN(){
        offByN obo = new offByN(5);
        assertTrue(obo.equalChars('a', 'f'));
        assertTrue(obo.equalChars('f', 'a'));
        assertFalse(obo.equalChars('f', 'h'));
        assertFalse(obo.equalChars('g', 'e'));
    }
}
