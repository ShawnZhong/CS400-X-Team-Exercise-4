import java.util.ArrayList;

/*
 * LinkedList implementation used for Graph
 * limited functions of a Linked list
 * does not allow duplicates
 */
public class LinkedList<T> {
    /**
     * Instance variables and constructors
     */
    private LinkedNode<T> root;

    LinkedList() {
        root = null;
    }

    /**
     * Checks if link is empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Search for item within the list
     *
     * @param key - data that the list is searching for
     * @return - returns the data if found
     */
    public T search(T key) {
        if (root == null) {
            return null;
        }
        if (root.data.equals(key)) {
            return root.data;
        } else {
            LinkedNode<T> list = root;
            while (list.next != null) {
                if (list.next.data.equals(key)) {
                    return list.data;
                }
                list = list.next;
            }
        }
        return null;
    }

    /**
     * Add value to the linked list according to CS400 convention
     *
     * @param T item - item to be inserted
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void add(T item) throws DuplicateException {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        if (root == null) {
            root = new LinkedNode<T>(item);
        } else if (root.data.equals(item)) {
            throw new DuplicateException();
        } else {
            LinkedNode<T> list = root;
            while (list.next != null) {
                // if item is within the list
                if (item == list.next.data) {
                    throw new DuplicateException();
                } else {
                    list = list.next;
                }
            }
            // inserts item at the very end of the list
            list.next = new LinkedNode(item, list.next);
        }
    }

    /**
     * Removes the given value from the linked list
     *
     * @param item - value to be removed
     */
    public void remove(T item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        // list is empty
        if (root == null)
            throw new NullPointerException();
        // item == root
        if (root.data.equals(item)) {
            root = root.next;
            return;
        }
        // stores current and previous nodes
        LinkedNode<T> cur = root;
        LinkedNode<T> prev = null;

        // iterates through the list to find item to be removed
        while (cur != null && !cur.data.equals(item)) {
            prev = cur;
            cur = cur.next;
        }

        // item was not within the list
        if (cur == null)
            throw new IndexOutOfBoundsException("item is not within the list");

        // delete cur node
        prev.next = cur.next;
    }

    /**
     * Converts linked list into an array list
     *
     * @return - array list with linked list data
     */
    public ArrayList<T> toArrayList() {
        LinkedNode<T> list = root;
        ArrayList<T> toReturn = new ArrayList<T>();
        while (list != null) {
            toReturn.add(list.data);
            list = list.next;
        }
        return toReturn;
    }

    /**
     * Inner generic Class for nodes of the linked list
     */
    private class LinkedNode<A> {
        /**
         * Instance variables and constructors
         */
        private A data;
        private LinkedNode<A> next;

        LinkedNode(A data) {
            this.data = data;
            next = null;
        }

        LinkedNode(A data, LinkedNode<A> next) {
            this.data = data;
            this.next = next;
        }
    }

}
