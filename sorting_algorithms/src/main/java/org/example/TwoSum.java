package org.example;

public class TwoSum {
    public static void main(String[] args) {
        int[] A = new int[]{6, 5, 9, 7, 7, 1, 3};
        int x = 16;
        System.out.println(optimizedTwoSum(A, A.length, x));

    }

    static boolean naiveTwoSum(int[] A, int n, int x) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (A[i] + A[j] == x) return true;
            }
        }
        return false;
    }

    static boolean optimizedTwoSum(int[] A, int n, int x) {
        MergeSort.sort(A, 0, n - 1);
        for (int i = 0; i < n - 1; i++) {
            int index = BinarySearch.search(A, x - A[i], i + 1, n);
            if (index != -1) return true;
        }
        return false;
    }
}
