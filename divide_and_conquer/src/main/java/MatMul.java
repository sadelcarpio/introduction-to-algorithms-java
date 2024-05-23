import java.util.Arrays;

public class MatMul {
    public static void main(String[] args) {
        int[][] A = new int[][]{{1, 1},
                {1, 1}};
        int[][] B = new int[][]{{2, 2},
                {2, 2}};
        int[][] C = strassenMultiply(A, B);
        System.out.println(Arrays.deepToString(C));
    }

    public static int[][] naiveMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] = C[i][j] + A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public static int[][] recursiveMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        _recursiveMultiply(A, B, C, 0, 0, 0, 0, n);
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

    private static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;
        return _strassenMultiply(A, B, 0, 0, 0, 0, n);
    }

    private static int[][] _strassenMultiply(int[][] A, int[][] B, int aCol, int aRow, int bCol, int bRow, int n) {
        int[][] C = new int[n][n];
        if (n == 1) return new int[][]{{A[aRow][aCol] * B[bRow][bCol]}};
        int newN = n / 2;
        int[][] S = new int[2 * newN][5 * newN];
        int[][][] P = new int[7][newN][newN];
        matrixSum(B, B, S, bCol + newN, bRow, bCol + newN, bRow + newN, 0, 0, newN, true);  // b12 - b22
        matrixSum(A, A, S, aCol, aRow, aCol + newN, aRow, newN, 0, newN, false);  // a11 + a12
        matrixSum(A, A, S, aCol, aRow + newN, aCol + newN, aRow + newN, 2 * newN, 0, newN, false);  // a21 + a22
        matrixSum(A, A, S, aCol, aRow, aCol + newN, aRow + newN, 4 * newN, 0, newN, false);  // a11 + a22
        matrixSum(B, B, S, bCol, bRow, bCol + newN, bRow + newN, 0, newN, newN, false);  // b11 + b22
        matrixSum(A, A, S, aCol + newN, aRow, aCol + newN, aRow + newN, newN, newN, newN, true);  // a12 - a22
        matrixSum(B, B, S, bCol, bRow + newN, bCol, bRow, 3 * newN, 0, newN, true);  // b21 - b11
        matrixSum(B, B, S, bCol, bRow + newN, bCol + newN, bRow + newN, 2 * newN, newN, newN, false);  // b21 - b22
        matrixSum(A, A, S, aCol, aRow, aCol, aRow + newN, 3 * newN, newN, newN, true);  // a11 - a21
        matrixSum(B, B, S, bCol, bRow, bCol + newN, bRow, 4 * newN, newN, newN, false);  // b11 + b1B
        P[0] = _strassenMultiply(A, S, aCol, aRow, 0, 0, newN);
        P[1] = _strassenMultiply(S, B, newN, 0, bCol + newN, bRow + newN, newN);
        P[2] = _strassenMultiply(S, B, 2 * newN, 0, bCol, bRow, newN);
        P[3] = _strassenMultiply(A, S, aCol + newN, aRow + newN, 3 * newN, 0, newN);
        P[4] = _strassenMultiply(S, S, 4 * newN, 0, 0, newN, newN);
        P[5] = _strassenMultiply(S, S, newN, newN, 2 * newN, newN, newN);
        P[6] = _strassenMultiply(S, S, 3 * newN, newN, 4 * newN, newN, newN);
        c11(P, C, newN);
        c12(P, C, newN);
        c21(P, C, newN);
        c22(P, C, newN);
        return C;
    }

    private static void c11(int[][][] P, int[][] C, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] += P[4][i][j] + P[3][i][j] - P[1][i][j] + P[5][i][j];
            }
        }
    }

    private static void c12(int[][][] P, int[][] C, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j + n] += P[0][i][j] + P[1][i][j];
            }
        }
    }

    private static void c21(int[][][] P, int[][] C, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i + n][j] += P[2][i][j] + P[3][i][j];
            }
        }
    }

    private static void c22(int[][][] P, int[][] C, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i + n][j + n] += P[4][i][j] + P[0][i][j] - P[2][i][j] - P[6][i][j];
            }
        }
    }


    private static void matrixSum(int[][] A, int[][] B, int[][] S, int aCol, int aRow, int bCol, int bRow, int sCol, int sRow, int n, boolean neg) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                S[sRow + i][sCol + j] = A[i + aRow][j + aCol] + (neg ? -B[i + bRow][j + bCol] : B[i + bRow][j + bCol]);
            }
        }
    }
}
