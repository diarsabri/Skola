import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Diar on 2016-12-08.
 */
public class ReverseTest
{

    private Reverse reverse = new Reverse();

    @org.junit.Test
    public void reverseArray() throws Exception
    {
        int[] a = new int[5];
        a[0]=1;
        a[1]=2;
        a[2]=3;
        a[3]=4;
        a[4]=5;

        int[] b = new int[5];
        b[0]=5;
        b[1]=4;
        b[2]=3;
        b[3]=2;
        b[4]=1;

        reverse.reverseArray(a);
        assertArrayEquals(a,b);
    }

    @org.junit.Test
    public void reverseList() throws Exception
    {
        List<Integer> listA = new ArrayList<>();
        listA.add(1);
        listA.add(2);
        listA.add(3);
        listA.add(4);
        listA.add(5);

        List<Integer> listB = new ArrayList<>();
        listB.add(5);
        listB.add(4);
        listB.add(3);
        listB.add(2);
        listB.add(1);

        reverse.reverseList(listA);
        assertEquals(listA,listB);
    }
}