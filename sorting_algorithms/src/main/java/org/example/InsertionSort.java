package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class InsertionSort {
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.nextLine());
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(sc.nextLine());
        }
        sort_with_binary_search(A, n);
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

    public static void sort_with_binary_search(int[] A, int n) {
        for (int i = 1; i < n; i++) {
            int key = A[i];
            int index = BinarySearch.binarySearch(A, key, 0, i - 1);
            int j = i - 1;
            while (j > index) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }
    }

    public static void recursive_sort(int[] A, int n) {
        if (n == 1) {
            return;
        }
        recursive_sort(A, n - 1);
        int key = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (key < A[i]) {
                A[i + 1] = A[i];
                A[i] = key;
            }
        }
    }
}
