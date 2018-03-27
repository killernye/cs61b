import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    @Test
    public void testEqualChars() {
        OffByN obn1 = new OffByN(2);
        assertFalse(obn1.equalChars('a', 'a'));
        assertFalse(obn1.equalChars('a', 'b'));
        assertTrue(obn1.equalChars('a', 'c'));
        assertTrue(obn1.equalChars('a', '_'));

        OffByN obn2 = new OffByN(-2);
        assertFalse(obn2.equalChars('a', 'a'));
        assertFalse(obn2.equalChars('a', 'b'));
        assertTrue(obn2.equalChars('a', 'c'));
        assertTrue(obn2.equalChars('a', '_'));
    }
}
