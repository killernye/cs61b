import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test(timeout = 1000)
    public void randomTest() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        assertEquals(ads1.size(), sad1.size());

        int i = 0;
        while (i < 10000) {
            i += 1;
            int k = StdRandom.uniform(4);
            Integer x = StdRandom.uniform(999);
            if (k == 0) {
                sad1.addFirst(x);
                ads1.addFirst(x);
                assertEquals(ads1.size(), sad1.size());
            } else if (k == 1) {
                sad1.addLast(x);
                ads1.addLast(x);
                assertEquals(ads1.size(), sad1.size());
            } else if (k == 2) {
                if (sad1.size() == 0) {
                    continue;
                } else {
                    ads1.addFirst(x);
                    sad1.addFirst(x);
                    Integer actual = sad1.removeFirst();
                    Integer expected = ads1.removeFirst();
                    assertEquals("\naddFirst(" + x + ")\nremoveLast()", expected, actual);
                    assertEquals(ads1.size(), sad1.size());
                }
            } else {
                if (sad1.size() == 0) {
                    continue;
                } else {
                    ads1.addLast(x);
                    sad1.addLast(x);
                    Integer actual = sad1.removeLast();
                    Integer expected = ads1.removeLast();
                    assertEquals("\naddLast(" + x + ")\nremoveLast()", expected, actual);
                    assertEquals(ads1.size(), sad1.size());
                }
            }
        }
    }
}

