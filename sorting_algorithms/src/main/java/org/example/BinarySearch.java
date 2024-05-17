package org.example;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 5, 7, 3, 2, 1};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int index = binarySearch(arr, 4, 0, arr.length);
        System.out.println(index);
    }

    public static int binarySearch(int[] a, int val, int p, int r) {
        if (p >= r) return -1;
        int q = (p + r) / 2;
        if (val == a[q]) return q;
        if (val < a[q]) {
            return binarySearch(a, val, p, q);
        }
        return binarySearch(a, val, q + 1, r);
    }
}
