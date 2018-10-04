import java.util.Arrays;
import java.util.Scanner;

public class Square {
    static int curr = 1;
    static int[][] matrix;
    static int m;

    private static void fillMiddle(int size, int q, int xx, int yy) {
        switch (q) {
        case 1:
            matrix[size / 2 - 1 + xx][size / 2 - 1 + yy] = curr;
            matrix[size / 2 - 1 + xx][size / 2 + yy] = curr;
            matrix[size / 2 + xx][size / 2 - 1 + yy] = curr;

            break;
        case 2:
            matrix[size / 2 - 1 + xx][size / 2 + yy] = curr;
            matrix[size / 2 - 1 + xx][size / 2 - 1 + yy] = curr;
            matrix[size / 2 + xx][size / 2 + yy] = curr;

            break;
        case 3:
            matrix[size / 2 + xx][size / 2 + yy] = curr;
            matrix[size / 2 - 1 + xx][size / 2 - 1 + yy] = curr;
            matrix[size / 2 + xx][size / 2 - 1 + yy] = curr;

            break;
        case 4:
            matrix[size / 2 + xx][size / 2 + yy] = curr;
            matrix[size / 2 + xx][size / 2 - 1 + yy] = curr;
            matrix[size / 2 - 1 + xx][size / 2 + yy] = curr;

            break;
        }

        curr++;

    }

    // Not used, changed approach
    private static int[][] partition(int start, int end, int q) {
        int[][] ret = new int[m / 2][m / 2];

        switch (q) {
        case 1:
            for (int i = 0; i < m / 2; i++) {
                for (int j = 0; j < m / 2; j++) {
                    if (start != 0) {
                        ret[i][j] = matrix[i][start + j];
                    } else {
                        ret[i][j] = matrix[start + i][start + j];
                    }
                }
            }

            break;
        case 2:
            for (int i = 0; i < m / 2; i++) {
                for (int j = 0; j < m / 2; j++) {
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

    /*
     * 1. Dela upp i fyra 2. Fyll i mitten baserat på vilken q 3. Dela upp i fyra 4.
     * Fyll i mitten baserat på vilken q 5. .... 6. Om storlek = 2 => fyll i baserat
     * på var pos för hålet är 7. Klar
     */

    private static void fill2x2(int xx, int yy) {
        if (matrix[xx][yy] != 0) {
            matrix[xx][yy + 1] = curr;
            matrix[xx + 1][yy] = curr;
            matrix[xx + 1][yy + 1] = curr;

        } else if (matrix[xx][yy + 1] != 0) {
            matrix[xx][yy] = curr;
            matrix[xx + 1][yy] = curr;
            matrix[xx + 1][yy + 1] = curr;

        } else if (matrix[xx + 1][yy] != 0) {
            matrix[xx][yy] = curr;
            matrix[xx][yy + 1] = curr;
            matrix[xx + 1][yy + 1] = curr;

        } else if (matrix[xx + 1][yy + 1] != 0) {
            matrix[xx][yy] = curr;
            matrix[xx][yy + 1] = curr;
            matrix[xx + 1][yy] = curr;

        }

        curr++;
    }

    private static void tile(int size, int xx, int yy, int q) {
        if (size == 2) {
            fill2x2(xx, yy);
            fill2x2(xx, yy + size);
            fill2x2(xx + size, yy);
            fill2x2(xx + size, yy + size);
        } else {

            if (q == 1) {
                fillMiddle(size, 1, xx, yy);
                fillMiddle(size, 2, xx, yy + size);
                fillMiddle(size, 3, xx + size, yy);
                fillMiddle(size, 1, xx + size, yy + size);
            } else if (q == 2 || q == 3) {
                fillMiddle(size, 1, xx, yy);
                fillMiddle(size, 3, xx, yy + size);
                fillMiddle(size, 3, xx + size, yy);
                fillMiddle(size, 4, xx + size, yy + size);
            } else if (q == 4) {
                fillMiddle(size, 4, xx, yy);
                fillMiddle(size, 2, xx, yy + size);
                fillMiddle(size, 3, xx + size, yy);
                fillMiddle(size, 4, xx + size, yy + size);
            }

            tile(size / 2, xx, yy, 1); // q11
            tile(size / 2, xx, yy + size, 3);
            tile(size / 2, xx + size, yy, 3);
            tile(size / 2, xx + size, yy + size, 4);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        matrix = new int[m][m]; // matrix[i][j] - i rader ,j kolumner
        matrix[0][m - 1] = -1;
        fillMiddle(m, 3, 0, 0);
        tile(m / 2, 0, 0, 3);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

        scanner.close();
    }
}