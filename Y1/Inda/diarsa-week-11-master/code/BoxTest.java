import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by DiarS on 2016-11-30.
 */
public class BoxTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void compareTo() throws Exception {
        Box a = new Box(10,10,10);
        Box b = new Box(10,10,10);
        Box c = new Box(5,6,7);
        Box d = new Box(4,5,6);
        assertEquals(a.compareTo(b),0);
        assertEquals(c.compareTo(a),-1);
        assertEquals(c.compareTo(d),1);
    }

}