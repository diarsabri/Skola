import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class omp1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        if (n == 0) {
            System.out.println("No words");
        }

        int len = scanner.nextInt();

        int[] words = new int[n];
        for (int i = 0; i < n; i++) {
            words[i] = scanner.nextInt();
        }



        System.out.println("\n");

        int linelength = 0;
        int lines = 1;

        for (int i = 0; i < n; i++) {
            linelength += words[i] +1;

            if (linelength -1 > len) {
                linelength = words[i];
                lines++;
            }

            System.out.println(linelength+ " " + lines);
        }
 



        

        scanner.close();

        System.out.println(Arrays.toString(words));
        System.out.println("Lines required: " + lines);
    }
}


/*

n=10, len=25,

w1=17,  w2=4,                22
w3=7 ,  w4=3, w5=13          25
w6=2 ,  w7=9, w8=2,  w9=7    24
word10=6                    6

*/