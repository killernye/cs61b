/** A Double Linked List using a circular sentinel topology. */
public class LinkedListDeque<T> {
    /** Hidden structure of LinkedListDeque */
    private class TNode {
        T item;
        TNode next;
        TNode prev;

        public TNode(T i) {
            item = i;
            next = null;
            prev = null;
        }

        public TNode(T i, TNode p, TNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }


    TNode sentinel;
    int size;

    /** empty constructor */
    public LinkedListDeque() {
        sentinel = new TNode(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Constructor with an item */
    public LinkedListDeque(T item) {
        sentinel = new TNode(null);
        sentinel.next = new TNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Return the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Print the items in the deque from first to last, separated by space. */
    public void printDeque() {
        System.out.print("[ ");
        TNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println("]");
    }

    /** Add an item of type T to the front of the deque. */
    public void addFirst(T item) {
        TNode newNode = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    /** Add an item of type to the back of the deque. */
    public void addLast(T item) {
        TNode newNode = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    /** Get the item at the given index. If no such item exists, return null. */
    public T get(int index) {
        if (index >= size)
            return null;
        TNode ptr = sentinel.next;
        while (index > 0) {
            ptr = ptr.next;
            index -= 1;
        }
        return ptr.item;
    }

    /** Get the ith item using recursion. */
    public T getRecursive(int index) {
        if (index >= size)
            return null;
        else {
            return getHelper(sentinel.next, index).item;
        }
    }


    private TNode getHelper(TNode n, int index) {
        if (index == 0) {
            return n;
        } else {
            return getHelper(n.next, index - 1);
        }
    }


    /** Remove and return the item at the front of the deque. if no such item exists, return null. */
    public T removeFirst() {
        if (size == 0)
            return null;
        TNode returnNode = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return returnNode.item;
    }

    /** Remove and return the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0)
            return null;
        TNode returnNode = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return returnNode.item;
    }

    /**  Return true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return size == 0;
    }
}
