import java.util.*;

/**
 * A hash table of strings.
 *
 * @author Stefan Nilsson
 * @version 2010-07-21
 */
public class StringHash implements StringDictionary
{
    private List<String>[] table;
    /**
     * Creates a hash table with the given capacity.
     *
     * @throws IllegalArgumentException if capacity <= 0.
     */
    public StringHash(int capacity)
    {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity=" + capacity);

        @SuppressWarnings("unchecked")
        List<String>[] t = new LinkedList[capacity];

        table = t;
    }

    /**
     * Adds the given string to this dictionary.
     * Returns <code>true</code> if the dictionary
     * did not already contain the given string.
     * <p>
     * Complexity: O(1) expected time.
     */
    @Override
    public boolean add(String s)
    {
        int a = Math.abs(s.hashCode() % table.length);
        if(table[a] == null) {
            LinkedList<String> list = new LinkedList<>();
            list.add(s);
            table[a] = list;
            return true;
        }

        if((table[a] != null) && table[a].contains(s)) {
            return false;
        }

        else {
            table[a].add(s);
            return true;
        }
    }

    /**
     * Removes the given string from this dictionary
     * if it is present. Returns <code>true</code> if
     * the dictionay contained the specified element.
     * <p>
     * Complexity: O(1) expected time.
     */
    @Override
    public boolean remove(String s)
    {
        int a = Math.abs(s.hashCode() % table.length);

        if(contains(s)) {
            int b = table[a].indexOf(s);
            table[a].remove(b);
            return true;
        }
        return false;
    }

    /**
     * Returns <code>true</code> if the string is
     * in this dictionary.
     * <p>
     * Complexity: O(1) expected time.
     */
    @Override
    public boolean contains(String s)
    {
        int a = Math.abs(s.hashCode() % table.length);

        if (table[a] == null) {
            return false;
        } else if (table[a].contains(s)) {
            return true;
        }
        return false;
    }
}
