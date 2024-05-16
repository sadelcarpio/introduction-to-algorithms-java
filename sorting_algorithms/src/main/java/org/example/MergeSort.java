package org.example;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int n = 8;
        int[] A = new int[]{12, 3, 7, 9, 14, 6, 11, 2};
        sort(A, 0, n - 1);
        System.out.println(Arrays.toString(A));
    }

    private static void sort(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = (p + r) / 2;
        sort(a, p, q);
        sort(a, q + 1, r);
        merge(a, p, q, r);
    }

    private static void merge(int[] a, int p, int q, int r) {
        int n_l = q - p + 1;
        int n_r = r - q;
        int[] L = new int[n_l];
        int[] R = new int[n_r];
        System.arraycopy(a, p, L, 0, n_l);
        System.arraycopy(a, q + 1, R, 0, n_l);
        int i = 0; int j = 0; int k = p;
        while (i < n_l && j < n_r) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            }
            else {
                a[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n_l) {
            a[k] = L[i];
            i++;
            k++;
        }
        while (j < n_r) {
            a[k] = R[j];
            j++;
            k++;
        }
    }
}
