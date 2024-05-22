package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class BinaryAdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        int[] c = addBinaryIntegers(a, b, n);
        System.out.println(Arrays.toString(c));
    }

    public static int[] addBinaryIntegers(int[] a, int[] b, int n) {
        int[] c = new int[n + 1];
        int carry = 0;
        int sum;
        for (int i = n - 1; i >= 0; i--) {
            sum = (a[i] + b[i] + carry);
            c[i + 1] = sum % 2;
            c[i] = carry;
            carry = sum / 2;
        }
        return c;
    }
}
