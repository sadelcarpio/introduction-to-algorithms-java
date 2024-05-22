package org.example;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 5, 7, 3, 2, 1};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int index = search(arr, 4, 0, arr.length);
        System.out.println(index);
    }

    public static int search(int[] a, int val, int p, int r) {
        int q = (p + r) / 2;
        if (p >= r) return -1;
        if (val == a[q]) return q;
        if (val < a[q]) {
            return search(a, val, p, q);
        }
        return search(a, val, q + 1, r);
    }
}
