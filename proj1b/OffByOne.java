public class OffByOne implements CharacterComparator {

    @Override
    /** Returns true if characters are equal. */
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        if (diff == 1) {
            return true;
        } else {
            return false;
        }
    }
}