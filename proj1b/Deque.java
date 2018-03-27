/** Superclass over LinkedListDeque and ArrayDeque. */
public interface Deque<T> {
    /** Return the number of items in the deque. */
    public int size();

    /** Print the items in the deque from first to last, separated by space. */
    public void printDeque();

    /** Add an item of type T to the front of the deque. */
    public void addFirst(T item);

    /** Add an item of type to the back of the deque. */
    public void addLast(T item);

    /** Get the item at the given index. If no such item exists, return null. */
    public T get(int index);

    /** Remove and return the item at the front of the deque.
     * if no such item exists, return null. */
    public T removeFirst();

    /** Remove and return the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast();

    /**  Return true if deque is empty, false otherwise.*/
    public boolean isEmpty();
}
