package com.shstu.stringmatch;

/**
 * @Author:ssl
 * @Description: datastructures
 * @Date: create in 2023/4/22 21:29
 * @Modified By:
 * @VERSON:1.8
 */
public class KMP {
    public static void main(String[] args) {
        String ts = "bbc abcdab abcdabcdabde";
        String ps = "abcdabd";
        int res = kmp(ts, ps);
        System.out.println(res);
    }

    public static int kmp(String ts, String ps) {
        if (ts.length() == 0 || ps.length() == 0) {
            return -1;
        }
        byte[] t = ts.getBytes();
        byte[] p = ps.getBytes();
        int[] next = getNext(p);
        for (int i = 0, j = 0; i < t.length; i++) {
            while (j > 0 && t[i] != p[j]) {
                j = next[j - 1];
            }
            if (t[i] == p[j]) {
                j++;
            }
            if (j == p.length) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public static int[] getNext(byte[] p) {
        int len = p.length;
        int[] next = new int[len];
        next[0] = 0;
        for (int i = 1, j = 0; i < len; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = next[j - 1];
            }
            if (p[i] == p[j]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
