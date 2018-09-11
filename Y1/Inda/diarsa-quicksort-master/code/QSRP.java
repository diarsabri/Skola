import java.util.Random;
/**
 * Created by Diar on 2017-03-02.
 * Quicksort with a randomly assigned pivot.
 * The only difference between this and QSFP is the commented section @ lines 21-24
 */
public class QSRP implements IntSorter
{
    public void qsort(int[] v,int start,int end)
    {
        int a = partition(v,start,end);
        if (start<a-1)
            qsort(v,start,a-1);
        if (a<end)
            qsort(v,a,end);
    }

    public int partition(int[] v,int start,int end)
    {
        int a = start, b = end;
        Random random = new Random();
        int y = start + random.nextInt(Math.abs(end-start)+1);              //the main difference, we create an int that is randomized and is always positive
        if (y < 0) { y = y*-1; }                                                   //this part keeps it positive
        int pivot = v[y];                                                          //then we just assign it to be the pivot element

        while (a<=b) {
            while (v[a]<pivot)
                a++;
            while (v[b] >pivot)
                b--;
            if (a<=b) {
                int x = v[a];
                v[a] = v[b];
                v[b] = x;
                a++;
                b--;
            }
        }
        return a;
    }

    @Override
    public void sort(int[] v) {
        qsort(v,0,v.length-1);
    }
    public static void main(String[] args) {
        IntSorter qs = new QSRP();
    }
}
