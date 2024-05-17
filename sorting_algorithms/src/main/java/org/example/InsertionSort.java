package org.example;

import java.util.Arrays;
import java.util.Scanner;

//Dummy comment
public class InsertionSort {
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.nextLine());
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(sc.nextLine());
        }
        sortWithBinarySearch(A, n);
        System.out.println(Arrays.toString(A));
    }

    public static void sort(int[] A, int n) {
        for (int i = 1; i < n; i++) {
            int key = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }
    }

    public static void sortWithBinarySearch(int[] A, int n) {
        for (int i = 1; i < n; i++) {
            int key = A[i];
            int index = binaryInsert(A, key, 0, i);
            int j = i - 1;
            while (j >= index) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }
    }

    public static void recursiveSort(int[] A, int n) {
        if (n == 1) {
            return;
        }
        recursiveSort(A, n - 1);
        int key = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (key < A[i]) {
                A[i + 1] = A[i];
                A[i] = key;
            }
        }
    }

    private static int binaryInsert(int[] A, int key, int p, int r) {
        int q = (p + r) / 2;
        if (p >= r) return p;
        if (key == A[q]) return q;
        if (key < A[q]) {
            return binaryInsert(A, key, p, q);
        }
        return binaryInsert(A, key, q + 1, r);
    }
}
