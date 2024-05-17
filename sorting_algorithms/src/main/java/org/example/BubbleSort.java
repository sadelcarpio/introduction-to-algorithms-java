package org.example;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int n = 6;
        int[] A = new int[]{5, 2, 4, 6, 1, 3};
        sort(A, n);
        System.out.println(Arrays.toString(A));
    }

    public static void sort(int[] A, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                int temp = A[j];
                if (temp < A[j - 1]) {
                    A[j] = A[j - 1];
                    A[j - 1] = temp;
                }
            }
        }
    }
}
