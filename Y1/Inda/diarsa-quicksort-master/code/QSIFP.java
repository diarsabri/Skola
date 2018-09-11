/**
 * Created by Diar on 2017-03-02.
 * Quicksort with a fixed pivot with an insertion sort breaking point
 * This class extends the class QSFP so it inherits its methods.
 * The insertionsort for both QSIFP and QSIRP are the exact same.
 */
public class QSIFP extends QSFP
{
    public void iSort(int[] v,int start,int end)            //insertionsort based off of the insertionsort we did a couple weeks ago
    {
        for (int i = start; i < end+1; i++) {               //main difference is the start and end values.
            int a = v[i];
            int j = i - 1;
            while (j >= 0 && a < v[j]) {
                int temp = v[j+1];
                v[j+1] = v[j];
                v[j] = temp;
                j--;
            }
            v[j+1] = a;
        }
    }

    public void qsort(int[] v,int start,int end)            //calls for qsort on the QSFP class
    {
        if (start>=end)
            return;
        if (end-start <= 9)                                 //this is the "k" value at which insertionsort gets put in place instead of quicksort.
            iSort(v,start,end);

        int a = partition(v,start,end);
        if (start<a-1)
            qsort(v,start,a-1);
        if (a<end)
            qsort(v,a,end);
    }

    public static void main(String[] args) {
        IntSorter qs = new QSIFP();
    }
}
