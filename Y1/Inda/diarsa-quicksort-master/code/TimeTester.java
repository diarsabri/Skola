import java.util.Arrays;

/**
 * Created by Diar on 2017-03-07.
 * This class is for measuring sorting time for the different algorithms: QSFP,QSRP,QSIFP,QSIRP,InsertionSort & Arrays built-in sort.
 * To test the different algo's, just change either lines #48 or line #80.
 * When testing for insertionsort, the for-loop @ line #42 has to be // (commented out) along with its bracket at line #53
 * The first print shows the time taken for random,ascending and descending arrays in that order, five of each.
 * The second print shows arrays of equal value and the time taken for sorting that.
 */
public final class TimeTester
{
    public static void main(String[] args)
    {
        Data[] test = new Data[15];
        test[0] = new Data(100,20, Data.Order.RANDOM);
        test[1] = new Data(1000,20, Data.Order.RANDOM);
        test[2] = new Data(10000,20, Data.Order.RANDOM);
        test[3] = new Data(100000,20, Data.Order.RANDOM);
        test[4] = new Data(1000000,20, Data.Order.RANDOM);

        test[5] = new Data(100,20, Data.Order.ASCENDING);
        test[6] = new Data(1000,20, Data.Order.ASCENDING);
        test[7] = new Data(10000,20, Data.Order.ASCENDING);
        test[8] = new Data(100000,20, Data.Order.ASCENDING);
        test[9] = new Data(1000000,20, Data.Order.ASCENDING);

        test[10] = new Data(100,20, Data.Order.DESCENDING);
        test[11] = new Data(1000,20, Data.Order.DESCENDING);
        test[12] = new Data(10000,20, Data.Order.DESCENDING);
        test[13] = new Data(100000,20, Data.Order.DESCENDING);
        test[14] = new Data(1000000,20, Data.Order.DESCENDING);

        //below are the different sorting algorithms, change to whichever to test their times
        QSFP qsfp = new QSFP();
        QSRP qsrp = new QSRP();
        QSIFP qsifp = new QSIFP();
        QSIRP qsirp = new QSIRP();
        InsertionSort isort = new InsertionSort();

        final int limit = 15;
        Stopwatch sw = new Stopwatch();
        long[] arr = new long[limit];


        for (int n = 0;n<1000;n++) {            //since insertionsort is so slow, this for-loop has to be "commented out" when testing isort, otherwise it doesn't work(takes more time than i have)
            for (int i = 0; i < limit; i++) {

                sw.reset().start();
                {
                    int[] array = test[i].get();
                    Arrays.sort(array);          //only thing that needs to be changed to test the different sorting algorithms. change qsfp to qsrp for example to get the random pivot instead of fixed.
                }
                long time = sw.stop().nanoseconds();
                arr[i] += time;
            }
        }

        for (int i = 0; i<arr.length;i++) {
            long x = arr[i];
            arr[i] = x/1000;
        }
        System.out.println(Arrays.toString(arr));

        //below is the code for testing arrays of equal value

        int[] equaltest1 = new int[100];
        Arrays.fill(equaltest1,100);
        int[] equaltest2 = new int[1000];
        Arrays.fill(equaltest2,100);
        int[] equaltest3 = new int[10000];
        Arrays.fill(equaltest3,100);
        int[] equaltest4 = new int[100000];
        Arrays.fill(equaltest4,100);
        int[] equaltest5 = new int[1000000];
        Arrays.fill(equaltest5,100);
        int[] arrequal = new int[5];
        int[][] arrays = new int[5][];
        arrays[0] = equaltest1;
        arrays[1] = equaltest2;
        arrays[2] = equaltest3;
        arrays[3] = equaltest4;
        arrays[4] = equaltest5;


        for (int j = 0;j<100;j++) {
            for (int i = 0; i < 5; i++) {
                sw.reset().start();
                isort.sort(arrays[i]);                      //as before, the only thing that needs changing is the sorting algorithm at the start of this line.
                long time = sw.stop().nanoseconds();
                arrequal[i] += time;
            }
        }

        for (int i = 0; i<arrequal.length;i++) {
            long x = arrequal[i];
            arrequal[i] = Math.toIntExact(x / 100);
        }
        System.out.println(Arrays.toString(arrequal));

    }
}