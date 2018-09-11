/**
 * Created by Diar on 2017-03-02.
 * There is literally no change to this class and QSIFP, except that it extends the QSRP class instead.
 * That means the only difference is the pivot element getting randomized instead of being fixed.
 */
public class QSIRP extends QSRP
{
    public void iSort(int[] v,int start,int end)
    {
        for (int i = start; i < end+1; i++) {
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

    public void qsort(int[] v,int start,int end)
    {
        if (start>=end)
            return;
        if (end-start <= 9)
            iSort(v,start,end);

        int a = partition(v,start,end);
        if (start<a-1)
            qsort(v,start,a-1);
        if (a<end)
            qsort(v,a,end);
    }

    public static void main(String[] args) {
        IntSorter qs = new QSIRP();
    }
}
