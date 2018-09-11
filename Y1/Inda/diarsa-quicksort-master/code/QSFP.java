/**
 * Created by Diar on 2017-03-02.
 * Quicksort with a fixed pivot.
 */
public class QSFP implements IntSorter
{

    /**
     * @param v               the array to be sorted
     * @param start           the element at the beginning of the array
     * @param end             the element at the end of the array
     */
    public void qsort(int[] v,int start,int end)
    {
        int a = partition(v,start,end);        //assigns an integer to the partition method
        if (start<a-1)
            qsort(v,start,a-1);           //recursive calls for if the start is less than the returned integer a
        if (a<end)
            qsort(v,a,end);                    //recursive calls for if the end is bigger than the returned integer a
    }

    /**
     * @param v               the array to be sorted
     * @param start           the element at the beginning of the array
     * @param end             the element at the end of the array
     * @return                returns an integer that gets gets checked in qsort whether the algorithm should continue or not.
     */
    public int partition(int[] v,int start,int end)
    {
        int a = start,b = end;
        int pivot = v[start+(end-start)/2];         //setting the pivot entry to a fixed position

        while (a<=b) {
            while (v[a]<pivot)                      //while the starting pos is smaller than the pivot, we increment
                a++;
            while (v[b] >pivot)                     //while the end pos is bigger than the pivot, we decrease
                b--;
            if (a<=b) {                             //the breaking point of the increment/decrease. if it gets to here, we swap the elements of start and end and increment/decrease.
                int x = v[a];
                v[a] = v[b];
                v[b] = x;
                a++;
                b--;
            }
        }
        return a;                                   //this is the important return that allows the qsort method to figure out with the if statements if it should go on or not.
    }

    @Override
    public void sort(int[] v) {
        qsort(v,0,v.length-1);
    }
    public static void main(String[] args) {
        IntSorter qs = new QSFP();
    }

}