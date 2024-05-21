package org.example;

public class NumInversions {
    public static void main(String[] args) {
        int[] A = new int[]{5, 6, 4, 0, 3, 9, 12, 8, 16, 1};
        int numInversions = countInversions(A, 0, A.length - 1);
        System.out.println(numInversions);
    }

    public static int countInversions(int[] a, int p, int r) {
        int numInversions = 0;
        if (p >= r) {
            return numInversions;
        }
        int q = (p + r) / 2;
        numInversions += countInversions(a, p, q);
        numInversions += countInversions(a, q + 1, r);
        numInversions += merge(a, p, q, r);
        return numInversions;
    }

    private static int merge(int[] a, int p, int q, int r) {
        int numInversions = 0;
        int n_l = q - p + 1;
        int n_r = r - q;
        int[] L = new int[n_l];
        int[] R = new int[n_r];
        System.arraycopy(a, p, L, 0, n_l);
        System.arraycopy(a, q + 1, R, 0, n_r);
        int i = 0; int j = 0; int k = p;
        while (i < n_l && j < n_r) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            }
            else {
                a[k] = R[j];
                numInversions += (n_l - i);
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
        return numInversions;
    }
}
