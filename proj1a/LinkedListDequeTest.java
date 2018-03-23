import org.junit.Test;
import static org.junit.Assert.*;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}
	@Test
	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public void addIsEmptySizeTest() {
		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
		boolean expected = true;
		boolean actual = lld1.isEmpty();
		assertEquals(expected, actual);

		lld1.addFirst("front");
		assertEquals(1, lld1.size());
		assertEquals(false, lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());
	}

	@Test
	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public void addRemoveTest() {

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		lld1.addFirst(10);

		int returnValue = lld1.removeFirst();
		assertEquals(10, returnValue);
		assertEquals(true, lld1.isEmpty());

		lld1.addLast(20);
		int value2 = lld1.removeLast();
		assertEquals(20, value2);
		assertEquals(true, lld1.isEmpty());
	}

	@Test
	/** Test get method */
	public void getTest() {
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
		lld1.addFirst(10);
		lld1.addFirst(20);
		lld1.addFirst(30);

		int actual1 = lld1.getRecursive(1);
		int actual2 = lld1.getRecursive(2);

		assertEquals(20, actual1);
		assertEquals(10, actual2);
	}
} 