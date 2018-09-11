import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by DiarS on 2016-11-30.
 */
public class BoxProcessorTest
{
    private Box[] box1 = new Box[5];
    private Box[] box2 = new Box[5];
    private Box[] boxAE = new Box[0];
    private Box[] boxAS = new Box[1];
    private List<Box> box3 = new ArrayList<>();
    private List<Box> box4 = new ArrayList<>();
    private List<Box> boxLE = new ArrayList<>();
    private List<Box> boxLS = new ArrayList<>();

    @org.junit.Before
    public void setUp()
    {
        box1[0] = new Box(3, 3, 3);
        box1[1] = new Box(4, 4, 4);
        box1[2] = new Box(1, 1, 1);
        box1[3] = new Box(5, 5, 5);
        box1[4] = new Box(5, 5, 5);

        box2[0] = new Box(1, 1, 1);
        box2[1] = new Box(3, 3, 3);
        box2[2] = new Box(4, 4, 4);
        box2[3] = new Box(5, 5, 5);
        box2[4] = new Box(5, 5, 5);

        box3.add(new Box(1,1,1));
        box3.add(new Box(2,2,2));
        box3.add(new Box(3,3,3));
        box3.add(new Box(5,5,5));
        box3.add(new Box(5,5,5));

        box4.add(new Box(3,3,3));
        box4.add(new Box(1,1,1));
        box4.add(new Box(2,2,2));
        box4.add(new Box(5,5,5));
        box4.add(new Box(5,5,5));

        boxAS[0] = new Box(1,1,1);
        boxLS.add(new Box(1,1,1));


    }

    @org.junit.Test
    public void insertionSort() throws Exception
    {
        BoxProcessor.insertionSort(boxAS);
        assertArrayEquals(boxAS,boxAS);
        BoxProcessor.insertionSort(boxAE);
        assertEquals(0,boxAE.length);
        BoxProcessor.insertionSort(box1);
        assertArrayEquals(box1, box2);
    }

    @org.junit.Test
    public void insertionSortList() throws Exception
    {
        BoxProcessor.insertionSort(boxLS);
        assertEquals(boxLS,boxLS);
        assertEquals(0,boxLE.size());
        BoxProcessor.insertionSort(box4);
        assertEquals(box4,box3);
    }

    @org.junit.Test
    public void sequentialSearch() throws Exception
    {
        assertEquals(BoxProcessor.sequentialSearch(boxAS,1),0);
        assertEquals(BoxProcessor.sequentialSearch(boxAE,1),-1);
        assertEquals((BoxProcessor.sequentialSearch(box2,1)),0);
        assertNotEquals((BoxProcessor.sequentialSearch(box2,1)),1);
    }

    @org.junit.Test
    public void sequentialSearchList() throws Exception
    {
        assertEquals(BoxProcessor.sequentialSearch(boxLS,1),0);
        assertEquals(BoxProcessor.sequentialSearch(boxLE,1),-1);
        assertEquals((BoxProcessor.sequentialSearch(box2,1)),0);
        assertNotEquals((BoxProcessor.sequentialSearch(box2,1)),1);
    }

    @org.junit.Test
    public void binarySearch() throws Exception
    {

        assertEquals((BoxProcessor.binarySearch(box1,1)),2);
    }

    @org.junit.Test
    public void binarySearchList() throws Exception
    {
        assertEquals((BoxProcessor.binarySearch(box3,1)),0);
    }
}