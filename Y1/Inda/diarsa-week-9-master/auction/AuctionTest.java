

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AuctionTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AuctionTest
{
    private Auction auction1;
    private Person diar;

    /**
     * Default constructor for test class AuctionTest
     */
    public AuctionTest()
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
        auction1 = new Auction();
        diar = new Person("diar");
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
    public void getLotTest()
    {
        assertNull(auction1.getLot(0));
        auction1.enterLot("s");
        assertNull(auction1.getLot(0));
        assertNotNull(auction1.getLot(1));
    }

    @Test
    public void showLotsTest()
    {
        auction1.showLots();
        auction1.enterLot("s");
        auction1.showLots();
    }

    @Test
    public void makeABidTest()
    {
        auction1.enterLot("s");
        auction1.makeABid(1, diar, 10);
        auction1.makeABid(1, diar, -10);
        auction1.makeABid(0, diar, 10);
    }
}







