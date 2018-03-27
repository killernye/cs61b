public class OffByN implements CharacterComparator {
    int _N;

    public OffByN(int N) {
        _N = N;
    }

    @Override
    /** Returns True if the given chars are off by N in the ASCII table.
    * Otherwise, returns false.
    */
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        if (diff == Math.abs(_N)) {
            return true;
        } else {
            return false;
        }
    }
}
