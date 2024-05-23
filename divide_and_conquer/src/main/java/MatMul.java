import java.util.Arrays;

public class MatMul {
    public static void main(String[] args) {
        int n = 4;
        int[][] A = new int[][]{{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        int[][] B = new int[][]{{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        int [][] C = recursiveMultiply(A, B, n);
        System.out.println(Arrays.deepToString(C));
    }

    public static int[][] naiveMultiply(int[][] A, int[][] B, int n) {
        int [][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] = C[i][j] + A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public static int[][] recursiveMultiply(int[][] A, int[][] B, int n) {
        int [][] C = new int[n][n];
        _recursiveMultiply(A, B, C,0, 0, 0, 0, n);
        return C;
    }

    private static void _recursiveMultiply(int[][] A, int[][] B, int[][] C, int aCol, int aRow, int bCol, int bRow, int n) {
        if (n == 1) C[aRow][bCol] += A[aRow][aCol] * B[bRow][bCol];
        else {
            int newN = n / 2;
            _recursiveMultiply(A, B, C, aCol, aRow, bCol, bRow, newN);
            _recursiveMultiply(A, B, C, aCol + newN, aRow, bCol, bRow + newN, newN);
            _recursiveMultiply(A, B, C, aCol, aRow, bCol + newN, bRow, newN);
            _recursiveMultiply(A, B, C, aCol + newN, aRow, bCol + newN, bRow + newN, newN);
            _recursiveMultiply(A, B, C, aCol, aRow + newN, bCol, bRow, newN);
            _recursiveMultiply(A, B, C, aCol + newN, aRow + newN, bCol, bRow + newN, newN);
            _recursiveMultiply(A, B, C, aCol, aRow + newN, bCol + newN, bRow, newN);
            _recursiveMultiply(A, B, C, aCol + newN, aRow + newN, bCol + newN, bRow + newN, newN);
        }
    }


}
