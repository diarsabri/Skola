import java.util.Arrays;

/**
 * An example demonstrating the effects of just-in-time compilation
 * on time measurements.
 *
 * @author Stefan Nilsson
 * @version 2011-02-07
 */
public final class TimingExample {
    /**
     * If you're using a JVM with just-in-time compilation,
     * chanses are that the first reported time is much
     * larger than the rest: during most of the first
     * invocation of the sum() method, the code has yet
     * to be compiled and optimized.
     */
    public static void main(String[] args) {
        final int reps = 5;
        final int n = 1000000;
        final Stopwatch clock = new Stopwatch();
        StringBuilder sb = new StringBuilder();
        long average = 0;
        long[] arr = new long[reps];

        for (int i = 0; i < reps; i++) {

            clock.reset().start();
            {
                long dummy = sum(n);
            }
            long time = clock.stop().milliseconds();
            average += time;
            arr[i] = time;      //adds the time at the current position i to the array

            sb.append("Time to run: sum("+n+"): "+time+" ms\n");
        }
        sb.delete(0,32);        //discards the first result
        System.out.println(sb);
        Arrays.sort(arr);       //sorts the array of times
        System.out.println("Highest time: "+arr[3]+" ms");  //prints the last element in the sorted array(except the discarded result)
        System.out.println("Lowest time: "+arr[0]+" ms");   //prints first one
        System.out.println("Average time for "+(reps-1)+" tries was: "+average/reps+" ms");
    }

    /**
     * Returns the sum 1 + 2 + ... + n.
     */
    private static long sum(int n) {
       long sum = 0;
       for (int i = 1; i <= n; i++) {
           sum += i;
       }
       return sum;
   }
}