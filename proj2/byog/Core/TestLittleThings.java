package byog.Core;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.LinkedList;

public class TestLittleThings {
    @Test
    public void testAddMultipleElementsToAnList(){
        LinkedList expected = new LinkedList();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        Integer[] ints = new Integer[]{1, 2, 3};
        LinkedList actual = new LinkedList();
        actual.addAll(Arrays.asList(ints));
        assertEquals(expected, actual);
    }

    @Test
    public void testPrintException() {
        try{
            throwException();
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testAddALl() {
        LinkedList<Integer> expected = new LinkedList<>();
        expected.addAll(Arrays.asList(1,2,3));
        LinkedList<Integer> actual = new LinkedList<>();
        actual.addAll(expected);
        assertEquals(expected, actual);
        expected.addAll(actual);
        System.out.println(expected);
    }

    public void throwException() {
        throw new RuntimeException("for the fun!");
    }

    @Test
    public void testSubstring() {
        String abcd = "abcd";
        String ab = abcd.substring(1,abcd.length() -1 );
        System.out.println(ab);
    }

    @Test
    public void testdivision() {
        int expected = 0;
        int actual = -1 / 2;
        assertEquals(expected, actual);
    }

}
