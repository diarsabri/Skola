

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BidTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BidTest
{
    /**
     * Default constructor for test class BidTest
     */
    public BidTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }


    @Test
    public void getBidderTest()
    {
        Person person1 = new Person("diar");
        Bid bid1 = new Bid(person1, 10);
        assertNotNull(bid1.getBidder());
    }

    @Test
    public void getValueTest()
    {
        Person person1 = new Person("diar");
        Bid bid1 = new Bid(person1, 10);
        assertEquals(10, bid1.getValue());
    }
}



