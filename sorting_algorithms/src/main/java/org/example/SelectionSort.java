package org.example;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int n = 6;
        int[] A = new int[]{5, 2, 4, 6, 1, 3};
        sort(A, n);
        System.out.println(Arrays.toString(A));
    }

    private static void sort(int[] a, int n) {
        int min;
        int index;
        for (int i = 0; i < n; i++) {
            min = a[i];
            index = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < min) {
                    min = a[j];
                    index = j;
                }
            }
            a[index] = a[i];
            a[i] = min;
        }
    }
}
