import java.util.List;

/**
 * A singly linked list.
 *
 * @author Diar Sabri
 * @version 1
 */
public class LinkedList<T>
{
    private ListElement<T> first;   // First element in list.
    private ListElement<T> last;    // Last element in list.
    private int size;               // Number of elements in list.

    /**
     * A list element.
     */
    private static class ListElement<T>
    {
        public T data;
        public ListElement<T> next;

        public ListElement(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Creates an empty list.
     */
    public LinkedList()
    {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Inserts the given element at the beginning of this list.
     */
    public void addFirst(T element)
    {
        if(size == 0) {
            last = new ListElement<T>(element);
            first = last;
        }

        if(size != 0) {
            ListElement<T> newFirst = new ListElement<>(element);
            newFirst.next = first;
            first = newFirst;
        }
        size ++;
    }

    /**
     * Inserts the given element at the end of this list.
     */
    public void addLast(T element)
    {
        if(size == 0) {
            first = last;
            ListElement<T> x = new ListElement<T>(element);
            last = x;
        }
        if (size != 0) {
            ListElement<T> x = new ListElement<T>(element);
            last.next = x;
            last = x;
        }
        size++;
    }

    /**
     * Returns the first element of this list.
     * Returns <code>null</code> if the list is empty.
     */
    public T getFirst()
    {
        if(size != 0) {
            return first.data;
        }
        return null;
    }

    /**
     * Returns the last element of this list.
     * Returns <code>null</code> if the list is empty.
     */
    public T getLast()
    {
        if(size != 0) {
            return last.data;
        }
        return null;
    }

    /**
     * Returns the element at the specified position in this list.
     * Returns <code>null</code> if <code>index</code> is out of bounds.
     */
    public T get(int index)
    {
        if(size() == 0) {
            return null;
        }
        if(index < 0 || index > size) {
            return null;
        }
        else {
            ListElement<T> current = first;
            for(int i = 0; i< index;i++) {
                current = current.next;
            }
            return current.data;
        }
    }

    /**
     * Removes and returns the first element from this list.
     * Returns <code>null</code> if the list is empty.
     */
    public T removeFirst()
    {
        if(size != 0) {
            ListElement<T> second = first;
            first = first.next;
            size --;
            return second.data;
        }
        return null;
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear()
    {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Returns the number of elements in this list.
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns <code>true</code> if this list contains no elements.
     */
    public boolean isEmpty()
    {
        if(size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of this list. The string
     * representation consists of a list of the elements enclosed in
     * square brackets ("[]"). Adjacent elements are separated by the
     * characters ", " (comma and space). Elements are converted to
     * strings by the method toString() inherited from Object.
     */

    public String toString()
    {
        if(size == 1) {
            return "The only element in this list is: " +first.data;
        }
        String x = "[ ";
        ListElement<T> current = first;
        if (size > 0) {
            for (int i = 0; i < size && current != null; i++) {
                x += "" + current.data + "";
                if (i < (size - 1)) {
                    x += ", ";
                }
                current = current.next;
            }
            x += "]";
            return x;
        }
        return null;
    }

    /**
     * This TEST METHOD returns true if the following invariants hold:
     * <ul>
     *   <li> size equals the number of list elements, </li>
     *   <li> if size == 0, first == null and last == null, </li>
     *   <li> if size > 0, first != null and last != null, </li>
     *   <li> if size == 1, first == last, </li>
     *   <li> last.next == null. </li>
     * </ul>
     */
    public boolean isHealthy()
    {
        if((size == 0 && first == null && last == null) ||
                (size > 0 && first != null && last == null) ||
                (size == 1 && first == last) ||
                (last.next == null)) {
            return true;
        }
        int i = 0;
        while(i<=size) {
            i++;
        }
        if(i == size) {
            return true;
        }
        return false;
    }
}
