/** A class full of functions about Palindrome. */
public class Palindrome {

    /** Returns a deque where the characters appear in the
     * same order as in the given string.
     * @param word Any given string
     * @return A Deque
     */
    public Deque<Character> wordToDeque(String word){
        Deque<Character> D = new LinkedListDeque<>();
        for (char letter: word.toCharArray()) {
            D.addLast(letter);
        }
        return D;
    }

    /** Returns true if the given String is a palindrome, otherwise false. */
    public boolean isPalindrome(String word) {
        Deque<Character> D = wordToDeque(word);
        while (D.size() > 1) {
            if (D.removeFirst() != D.removeLast()) {
                return false;
            }
        }
        return true;
    }

    /** Returns true if the given String is a Palindrome based on the given
     * CharacterComparator, otherwise false.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> D = wordToDeque(word);
        while (D.size() > 1) {
            if (!cc.equalChars(D.removeFirst(), D.removeLast())) {
                return false;
            }
        }
        return true;
    }

    /** Implement method isPalindrome using Recursion. */
    public boolean isPalindromeRecursive(String word) {
        return isPalindromeHelper(word, 0, word.length() - 1);
    }

    /** Helper Function for isPalindromeRecursive methond. */
    private boolean isPalindromeHelper(String word, int first, int last) {
        if (last - first < 2) {
            return true;
        } else {
            boolean firstEqualsLast = word.charAt(first) == word.charAt(last);
            return firstEqualsLast && isPalindromeHelper(word, first + 1, last - 1);
        }
    }
}
