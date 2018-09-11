

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LotTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LotTest
{
    /**
     * Default constructor for test class LotTest
     */
    public LotTest()
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
    public void bidFor()
    {
        Lot lot1 = new Lot(1, "socks");
        Person person1 = new Person("diar");
        Bid bid1 = new Bid(person1, 10);
        Bid bid2 = new Bid(person1, 8);
        Bid bid3 = new Bid(person1, 11);
        assertEquals(true, lot1.bidFor(bid1));
        assertEquals(false, lot1.bidFor(bid2));
        assertEquals(true, lot1.bidFor(bid3));
    }

    @Test
    public void toStringTest()
    {
        Lot lot1 = new Lot(1, "socks");
        assertNotNull(lot1.toString());
    }

    @Test
    public void getNumberTest()
    {
        Lot lot1 = new Lot(1, "socks");
        assertEquals(1, lot1.getNumber());
    }

    @Test
    public void getDescriptionTest()
    {
        Lot lot1 = new Lot(1, "socks");
        assertEquals("socks", lot1.getDescription());
        assertNotNull(lot1.getDescription());
    }

    @Test
    public void getHighestBid()
    {
        Lot lot1 = new Lot(1, "socks");
        assertNull(lot1.getHighestBid());
        Person person1 = new Person("diar");
        Bid bid1 = new Bid(person1, 10);
        lot1.bidFor(bid1);
        assertNotNull(lot1.getHighestBid());
    }
}






