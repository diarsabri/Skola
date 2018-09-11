import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Diar on 2017-03-02.
 * This is the test class in which we test all the four variations of the quicksort.
 */
public class QSTest {
    int[] a1,a2,a3,a4,a5,a6,a7;         //initiating seven different arrays
    int[] b1,b2,b3,b4;                  //and four arrays to which they should correspond after sorting
    QSFP qsfp;                          //initiating all the quicksort variations
    QSRP qsrp;
    QSIFP qsifp;
    QSIRP qsirp;

    @Before
    public void setUp() throws Exception {
        qsfp = new QSFP();
        qsrp = new QSRP();
        qsifp = new QSIFP();
        qsirp = new QSIRP();

        a1 = new int[] {1,2,3,4,5,6,7};     //already sorted
        a2 = new int[] {7,6,5,4,3,2,1};     //sorted backwards
        a3 = new int[] {3,6,1,4,2,7,5};     //randomly selected
        a4 = new int[] {7,7,7,7,7,7,7};     //array of all the same values
        a5 = new int[] {0,1,2,3,4,5,6};     //zero at start
        a6 = new int[] {1,2,3,4,5,6,0};     //zero at end
        a7 = new int[] {0,1,2,3,4,5,0};     //zero at start & end

        b1 = new int[] {1,2,3,4,5,6,7};
        b2 = new int[] {7,7,7,7,7,7,7};
        b3 = new int[] {0,1,2,3,4,5,6};
        b4 = new int[] {0,0,1,2,3,4,5};
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testQSFP()
    {
        qsfp.sort(a1);
        assertArrayEquals(b1,a1);
        qsfp.sort(a2);
        assertArrayEquals(b1,a2);
        qsfp.sort(a3);
        assertArrayEquals(b1,a3);
        qsfp.sort(a4);
        assertArrayEquals(b2,a4);
        qsfp.sort(a5);
        assertArrayEquals(b3,a5);
        qsfp.sort(a6);
        assertArrayEquals(b3,a6);
        qsfp.sort(a7);
        assertArrayEquals(b4,a7);
    }

    @Test
    public void testQSRP()
    {
        qsrp.sort(a1);
        assertArrayEquals(b1,a1);
        qsrp.sort(a2);
        assertArrayEquals(b1,a2);
        qsrp.sort(a3);
        assertArrayEquals(b1,a3);
        qsrp.sort(a4);
        assertArrayEquals(b2,a4);
        qsrp.sort(a5);
        assertArrayEquals(b3,a5);
        qsrp.sort(a6);
        assertArrayEquals(b3,a6);
        qsrp.sort(a7);
        assertArrayEquals(b4,a7);
    }

    @Test
    public void testQSIFP()
    {
        qsifp.sort(a1);
        assertArrayEquals(b1,a1);
        qsifp.sort(a2);
        assertArrayEquals(b1,a2);
        qsifp.sort(a3);
        assertArrayEquals(b1,a3);
        qsifp.sort(a4);
        assertArrayEquals(b2,a4);
        qsifp.sort(a5);
        assertArrayEquals(b3,a5);
        qsifp.sort(a6);
        assertArrayEquals(b3,a6);
        qsifp.sort(a7);
        assertArrayEquals(b4,a7);
    }

    @Test
    public void testQSIRP()
    {
        qsirp.sort(a1);
        assertArrayEquals(b1,a1);
        qsirp.sort(a2);
        assertArrayEquals(b1,a2);
        qsirp.sort(a3);
        assertArrayEquals(b1,a3);
        qsirp.sort(a4);
        assertArrayEquals(b2,a4);
        qsirp.sort(a5);
        assertArrayEquals(b3,a5);
        qsirp.sort(a6);
        assertArrayEquals(b3,a6);
        qsirp.sort(a7);
        assertArrayEquals(b4,a7);
    }
}