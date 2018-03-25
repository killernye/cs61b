/** A Double-ended queue based on Array. */
public class ArrayDeque<Item> {
    Item[] items;
    int size;
    int nextFirst;
    int nextLast;

    /*
    Invariants:
    1. method addFirst always inserts item at the index NEXTFIRST
    2. method addLast always inserts item at the index NEXTLAST
    3. method plusOne(i) returns the index after I
    4. method minusOne(i) returns the index before I
    5. Deque starts at the index which is behind NEXTFIRST
    6. Deque ends at the index which is in front of NEXTLAST
    7. When Deque reach the capacity limit of the underlying array, it should double the size.
    */

    /** Creates an empty ArrayDeque .*/
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Adds an item of type Item to the front of the deque. */
    public void addFirst(Item item) {
        if (nextFirst == nextLast) {
            resize(2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /** Adds an item of type Item to the back of the deque. */
    public void addLast(Item item) {
        if (nextFirst == nextLast) {
            resize(2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, return null. */
    public Item removeFirst() {
        if(size == 0) {
            return null;
        }
        int first = plusOne(nextFirst);
        Item firstItem = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;
        if (usage() < 0.25 && items.length > 8) {
            resize(0.5);
        }
        return firstItem;
    }

    /** Gets the item at the given index. If no such item exists, returns null. */
    public Item get(int index) {
        if (index >= size) {
            return null;
        } else {
            int zeroIndex = plusOne(nextFirst);
            return items[plusX(zeroIndex, index)];
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, return null. */
    public Item removeLast() {
        if(size == 0) {
            return null;
        }
        int last = minusOne(nextLast);
        Item lastItem = items[last];
        items[last] = null;
        nextLast = last;
        size -=1 ;
        if (usage() < 0.25 && items.length > 8) {
            resize(0.5);
        }
        return lastItem;
    }


    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        System.out.print("[ ");
        int k = plusOne(nextFirst);
        while (k != nextLast) {
            System.out.print(items[k] + " ");
            k = plusOne(k);
        }
        System.out.println("]");
    }

    /** Resizing the underlying array. */
    private void resize(double factor) {
        int newLength = (int) (factor * items.length);
        Item[] newItems = (Item[]) new Object[newLength];
        int margin = items.length - 1 - nextFirst;
        int firstIndex = plusOne(nextFirst);
        if (size > margin) {
            System.arraycopy(items, firstIndex, newItems, 0, margin);
            System.arraycopy(items, 0, newItems, margin, size - margin);
        } else {
            System.arraycopy(items, firstIndex, newItems, 0, size);
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /** Computes the usage rage of the underlying array. */
    public double usage() {
        return (double) size / items.length;
    }

    /** Returns the index immediately before the given index. */
    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        } else {
            return index - 1;
        }
    }

    /** Returns the index immediately after the given index. */
    private int plusOne(int index) {
        return plusX(index, 1);
    }

    /** Returns the index X steps away from the given index. */
    private int plusX(int index, int x) {
        int margin = items.length - 1 - index;
        if (x <= margin) {
            return index + x;
        } else {
            return x - margin - 1;
        }
    }

    /** Returns true if deque is emptu, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }
}
