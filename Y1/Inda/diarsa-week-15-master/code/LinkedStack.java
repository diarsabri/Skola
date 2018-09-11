import java.util.LinkedList;

/**
 * Created by Diar on 2017-02-01.
 */
public class LinkedStack<T> implements Stack<T>
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
    public LinkedStack()
    {
        first = null;
        last = null;
        size = 0;
    }


    @Override
    public void push(T o)
    {
        if(size == 0) {
            last = new ListElement<T>(o);
            first = last;
        } else {
            ListElement<T> newFirst = new ListElement<T>(o);
            newFirst.next = first;
            first = newFirst;
        }
        size++;
    }

    @Override
    public T pop() {
        if (size != 0) {
            ListElement<T> second = first;
            first = first.next;
            size--;
            return second.data;
        }
        return null;
    }
        /*
        if (first == null) {
            return null;
        } else if(size == 1) {
            ListElement<T> x = new ListElement<T>(first.data);
            first = last;
            return x.data;
        } else if(size > 1) {
            ListElement<T> y = new ListElement<>(first.data);
            first = first.next;
            size--;
            return y.data;
        }
        return null;

        */


    @Override
    public T top()
    {
        if(size == 1) {
            return first.data;
        }
        return last.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }
}
