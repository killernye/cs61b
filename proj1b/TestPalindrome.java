import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("ababa"));
        assertTrue(palindrome.isPalindrome("ABABA"));
    }

    @Test
    public void testIsPalindromeRecursive() {
        assertFalse(palindrome.isPalindromeRecursive("cat"));
        assertTrue(palindrome.isPalindromeRecursive(""));
        assertTrue(palindrome.isPalindromeRecursive("a"));
        assertTrue(palindrome.isPalindromeRecursive("ababa"));
    }

    @Test
    public void testIsPalindromeCC() {
        CharacterComparator cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("aa", cc));
        assertFalse(palindrome.isPalindrome("ABA", cc));
        assertTrue(palindrome.isPalindrome("ab", cc));
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("a", cc));

        CharacterComparator ccN = new OffByN(3);
        assertTrue(palindrome.isPalindrome("add", ccN));
    }
}
