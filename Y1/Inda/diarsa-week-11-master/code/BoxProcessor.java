import java.util.*;

/**
 * Created by DiarS on 2016-11-30.
 */
public class BoxProcessor
{

    public static void insertionSort(Box[] array)
    {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && (array[j - 1].compareTo(array[j]) > 0)) {
                Box temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
                j = j - 1;
            }
        }
    }

    public static void insertionSort(List<Box> list)
    {
        for (int i = 1; i < list.size(); i++) {
            int j = i;
            while (j > 0 && (list.get(j - 1).compareTo(list.get(j))) > 0) {
                Box a = list.get(j);
                list.set(j, list.get(j-1));
                list.set(j-1,a);
                j = j - 1;
            }
        }
    }

    public static int sequentialSearch(Box[] array,int volume)
    {
        for(int a = 0;a<array.length;a++) {
            if(array[a].volume()==volume) {
                return a;
            }
        }
        return -1;
    }

    public static int sequentialSearch(List<Box> list,int volume)
    {
        for(int a = 0;a<list.size();a++) {
            if(list.get(a).volume()==volume) {
                return a;
            }
        }
        return -1;
    }

    public static int binarySearch(Box[] array,int volume)
    {
        int a = 0;
        int b = array.length -1;
        while(b>=a) {
            int c = (a+b)/2;
            if(array[c].volume()==volume) {
                return c;
            }
            if(array[c].volume() < volume) {
                a = c+1;
            }
            if(array[c].volume()>volume) {
                b = c-1;
            }
        }
        return -1;
    }

    public static int binarySearch(List<Box> list,int volume)
    {
        int a = 0;
        int b = list.size()-1;
        while (b>=a) {
            int c = (a+b)/2;
            if(list.get(c).volume()==volume) {
                return c;
            }
            if(list.get(c).volume()<volume) {
                a = c+1;
            }
            if(list.get(c).volume()>volume) {
                b = c-1;
            }
        }
        return -1;
    }
}