import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Diar on 2017-02-01.
 */
public class LinkedStackTest
{
    LinkedStack<Integer> intL = new LinkedStack<>();

    @Test
    public void pushAndTop() throws Exception
    {
        intL.push(1);
        intL.push(2);
        assertEquals(1,intL.top().intValue());

    }

    @Test
    public void popAndTop() throws Exception
    {
        intL.push(1);
        intL.push(2);
        intL.pop();
        assertEquals(2,intL.top().intValue());
    }


    @Test
    public void size() throws Exception
    {
        intL.push(1);
        intL.push(2);
        assertEquals(2,intL.size());
    }

    @Test
    public void isEmpty() throws Exception
    {
        assertTrue(intL.isEmpty());
        intL.push(1);
        intL.push(2);
        assertFalse(intL.isEmpty());
    }

}