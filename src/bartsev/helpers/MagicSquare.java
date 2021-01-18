package bartsev.helpers;

import java.util.List;

public class MagicSquare {

    public static String encryptMagicSquare(String word) {
        int[][] matrix = UserActions.getMagicSquareFromFile();
        if (word.length() != 25) {
            word = word + getStarString(25 - word.length());
        }
        String encryptedWord = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                encryptedWord = encryptedWord + word.charAt(matrix[i][j] - 1);
            }
        }
        return encryptedWord;
    }

    public static String decryptMagicSquare(String word) {
        int[][] matrix = UserActions.getMagicSquareFromFile();
        String decryptedWord = "";
        for (int m = 1; m <= 25; m++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == m) {
                        decryptedWord = decryptedWord + word.charAt(5 * i + (j + 1) - 1);
                    }
                }
            }
        }
        return decryptedWord.trim().replaceAll("\\*", "");
    }


    public static Boolean isMagicSquare(List<Integer> list) {
        int mat[][] = intListTo2DArray(list);
        int N = 5;
        int sum = 0, sum2 = 0;
        for (int i = 0; i < N; i++)
            sum = sum + mat[i][i];
        for (int i = 0; i < N; i++)
            sum2 = sum2 + mat[i][N - 1 - i];
        if (sum != sum2)
            return false;
        for (int i = 0; i < N; i++) {
            int rowSum = 0;
            for (int j = 0; j < N; j++)
                rowSum += mat[i][j];
            if (rowSum != sum)
                return false;
        }
        for (int i = 0; i < N; i++) {
            int colSum = 0;
            for (int j = 0; j < N; j++)
                colSum += mat[j][i];
            if (sum != colSum)
                return false;
        }
        return true;
    }

    public static int[][] intListTo2DArray(List<Integer> list) {
        int[][] matrix = new int[5][5];
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = list.get(k);
                k++;
            }
        }
        return matrix;
    }

    private static void printStringAsMatrix(String list) {
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(list.charAt(k) + "\t");
                k++;
            }
            System.out.println();
        }
    }

    private static void printMatrix(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static String getStarString(int length) {
        String s = "";
        while (s.length() < length) {
            s = s + "*";
        }
        return s;
    }
}
