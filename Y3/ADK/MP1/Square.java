import java.util.Arrays;
import java.util.Scanner;

public class Square {
    static int curr = 1;

    static int[][] matrix;
    static int m;

    static int[][] m1, m2, m3, m4;

    private static void fillMiddle(int[][] matrix, int m) {

        matrix[m / 2][m / 2] = curr;
        matrix[m / 2 - 1][m / 2 - 1] = curr;
        matrix[m / 2][m / 2 - 1] = curr;

        curr++;

    }

    private static int[][] partition(int start, int end, int q) {
        int[][] ret = new int[m / 2][m / 2];

        switch (q) {
        case 1:
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (start != 0) {
                        ret[i][j] = matrix[i][start + j];
                    } else {
                        ret[i][j] = matrix[start + i][start + j];
                    }
                }
            }

            break;
        case 2:
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (start != 0) {
                        ret[i][j] = matrix[start + i][start + j];
                    } else {
                        ret[i][j] = matrix[end + i][j];
                    }
                }
            }

            break;
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        matrix = new int[m][m]; // matrix[i][j] - i rader ,j kolumner
        matrix[0][m - 1] = -1;
        fillMiddle(matrix, m);

        m1 = partition(0, m / 2, 1);
        m2 = partition(m / 2, m, 1);

        m3 = partition(0, m / 2, 2);
        m4 = partition(m / 2, m, 2);

        

    
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\n");


        System.out.println(Arrays.toString(m1[0])+Arrays.toString(m2[0]));

        System.out.println(Arrays.toString(m1[1])+Arrays.toString(m2[1]));

        System.out.println(Arrays.toString(m3[0])+Arrays.toString(m4[0]));

        System.out.println(Arrays.toString(m3[1])+Arrays.toString(m4[1]));



        scanner.close();
    }
}