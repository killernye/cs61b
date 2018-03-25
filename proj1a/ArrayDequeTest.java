import org.junit.Test;
import static org.junit.Assert.*;

/** Perform some tests for ArrayDeque.*/
public class ArrayDequeTest {

    @Test
    public void isEmptyTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        assertEquals(true, ad1.isEmpty());
    }

    @Test
    public void addFirstTest(){
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addFirst("front");
        ad1.addFirst("middle");
        ad1.addFirst("back");

        assertEquals(3, ad1.size());
    }

    @Test
    public void addLastTest(){
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");
        ad1.printDeque();
        assertEquals(3, ad1.size());
    }

    @Test
    public void removeTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        String item = ad1.removeFirst();
        assertEquals("front", item);
        assertEquals("middle", ad1.removeFirst());
        assertEquals("back", ad1.removeFirst());
        assertEquals(null, ad1.removeFirst());

        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        assertEquals("back", ad1.removeLast());
        assertEquals("middle", ad1.removeLast());
        assertEquals("front", ad1.removeLast());
    }

    @Test
    public void getTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        assertEquals("front", ad1.get(0));
        assertEquals("back", ad1.get(2));
        assertEquals(null, ad1.get(15));
    }

    @Test
    public void resizeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            ad1.addLast(i);
        }
        assertEquals(10, ad1.size());
        for (int i = 0; i < 8; i++) {
            ad1.removeFirst();
        System.out.println(ad1.usage());
        }
    }

}
