import java.util.Arrays;

/**
 * Created by Diar on 2017-03-01.
 */
public class InsertionSort implements IntSorter {

    public void isort(int[] v,int left,int right)
    {
        for (int i = left; i < right+1; i++) {
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

    public void insSort(int[] v)
    {
        for (int i = 1; i < v.length; i++) {
            int j = i - 1;
            while (j >= 0 && v[j] > v[i]) {
                int x = v[i];
                v[i] = v[j];
                v[j] = x;
                i = j;
                j--;
            }
        }
    }

    public void sort(int[] v)
    {
        isort(v,0,v.length-1);
    }

    public static void main(String[] args) {
        InsertionSort insort = new InsertionSort();
        int[] ints = {1, 3, 7, 233, 7, 99, 210,100,2,7,8,2,432,1,66,50};
        insort.sort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
