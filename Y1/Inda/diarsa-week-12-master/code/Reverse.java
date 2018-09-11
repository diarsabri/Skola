import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Diar on 2016-12-08.
 */
public class Reverse
{

    public int[] reverseArray(int[] array)
    {
        for(int a = 0;a<array.length /2;a++) {
            int b = array[a];
            array[a]=array[array.length-a-1];
            array[array.length-a-1]=b;
        }
        return array;
    }

    // på samma sätt skriver vi funktionen för en lista istället.
    // båda dessa funktioner har en basic operation som loopar en gång för varje n i listan/arrayen.
    // med andra ord är big O O(n).
    public List<Integer> reverseList(List<Integer> list)
    {
        for(int a = 0;a<list.size() /2;a++) {
            int b =list.get(a);
            list.set(a,list.get(list.size()-a-1));
            list.set(list.size()-a-1,b);
        }
        return list;
    }

}

/*
    Reverse (A):
    Input: an array A storing n elements.
    Output: the same array with the elements in reversed order.
   for i = 1 to n-1             //outer loop exc n times
    x = A[i]                    //constant
        for j = i down to 1     //inner loop exc j times for each i which is exc n times.
    A[j] = A[j-1]               //constant
    A[0] = x                    //constant
*/