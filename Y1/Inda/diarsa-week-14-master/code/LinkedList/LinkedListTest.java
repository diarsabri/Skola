import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Diar on 2017-01-26.
 */
public class LinkedListTest {

    LinkedList<Integer> intL;

    public LinkedListTest()
    {
        intL = new LinkedList<Integer>();
    }

    @Test
    public void addFirst() throws Exception
    {
        intL.addFirst(1);
        intL.addFirst(2);
        intL.addFirst(3);
        assertEquals(3,intL.getFirst().intValue());
    }

    @Test
    public void addLast() throws Exception
    {
        intL.addLast(1);
        assertEquals(1, intL.getLast().intValue());
        intL.addFirst(1);
        intL.addLast(2);
        intL.addLast(3);
        assertEquals(1,intL.getFirst().intValue());
    }

    @Test
    public void getFirst() throws Exception
    {
        intL.addFirst(1);
        intL.addLast(2);
        intL.addLast(3);
        assertEquals(1,intL.getFirst().intValue());
    }

    @Test
    public void getLast() throws Exception
    {
        intL.addFirst(1);
        intL.addLast(2);
        intL.addLast(3);
        assertEquals(3,intL.getLast().intValue());
    }

    @Test
    public void get() throws Exception
    {
        intL.addFirst(1);
        intL.addLast(2);
        intL.addLast(3);
        intL.addLast(4);
        intL.addLast(5);
        assertEquals(3,intL.get(2).intValue());

    }

    @Test
    public void removeFirst() throws Exception
    {
        intL.addFirst(1);
        intL.addLast(2);
        intL.addLast(3);
        intL.addLast(4);
        intL.addLast(5);
        assertEquals(1,intL.removeFirst().intValue());
    }

    @Test
    public void clear() throws Exception
    {
        intL.addFirst(1);
        intL.addLast(2);
        intL.addLast(3);
        intL.addLast(4);
        intL.addLast(5);
        intL.clear();
        assertTrue(intL.isEmpty());
    }

    @Test
    public void size() throws Exception
    {
        intL.addFirst(1);
        intL.addLast(2);
        intL.addLast(3);
        intL.addLast(4);
        intL.addLast(5);
        assertEquals(5,intL.size());
    }

    @Test
    public void isEmpty() throws Exception
    {
        assertTrue(intL.isEmpty());
    }

    @Test
    public void isHealthy() throws Exception
    {
        assertTrue(intL.isHealthy());
    }

    @Test
    public void toStringTest() throws Exception
    {
        intL.addFirst(1);
        intL.addLast(2);
        intL.addLast(3);
        System.out.println(intL.toString());
    }

}