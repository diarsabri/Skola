import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Diar on 2017-02-07.
 */
public class StringHashTest
{
    private StringHash sh = new StringHash(25);

    @Test
    public void addTest() throws Exception
    {

        String x = "Hej";
        assertTrue(sh.add(x));
    }

    @Test
    public void removeTest() throws Exception
    {
        String x = "Hejdå";
        sh.add(x);
        assertTrue(sh.remove(x));
    }
}