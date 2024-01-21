package leetcode.contest.weekly_380;

import java.util.*;

public class Solution {
    // https://leetcode.com/contest/weekly-contest-380/problems/count-elements-with-maximum-frequency/
    // 3005. Count Elements With Maximum Frequency
    public static int maxFrequencyElements(int[] nums) {
        int max_fre = 0;
        int total_fre = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int fre = map.getOrDefault(num, 0) + 1;
            map.put(num, fre);
            if (fre > max_fre) {
                max_fre = fre;
                total_fre = max_fre;
            } else if (fre == max_fre) {
                total_fre += fre;
            }
        }

        return total_fre;
    }

    public static void invoke_maxFrequencyElements() {
        int[] nums = new int[]{1, 2, 2, 3, 1, 4};

        System.out.println(maxFrequencyElements(nums) == 4);
    }

    // https://leetcode.com/contest/weekly-contest-380/problems/maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k/
    // 3007. Maximum Number That Sum of the Prices Is Less Than or Equal to K
    public static long findMaximumNumber(long k, int x) {
        long left = 0;
        long right = (k + 1) << x;
        while (left + 1 < right) {
            long mid = (left + right) >> 1;
            if (countDigitOne(mid, x) <= k) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static long countDigitOne(long num, int x) {
        long res = 0;
        int shift = x - 1;
        long n = num >> shift;
        while (n > 0) {
            res += (n / 2) << shift;
            if ((n % 2) == 1) {
                int mask = (1 << shift) - 1;
                res += (num & mask) + 1;
            }
            shift += x;
            n >>= x;
        }
        return res;
    }

    public static void invoke_findMaximumNumber() {
        long k = 7;
        int x = 2;
        System.out.println(findMaximumNumber(k, x) == 9);
    }

    // https://leetcode.com/contest/weekly-contest-380/problems/find-beautiful-indices-in-the-given-array-i/
    // https://leetcode.com/contest/weekly-contest-380/problems/find-beautiful-indices-in-the-given-array-ii/
    // 3008. Find Beautiful Indices in the Given Array II
    public static List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> a_indices = kmp(s, a);
        List<Integer> b_indices = kmp(s, b);
        int b_len = b.length();

        List<Integer> res = new ArrayList<>();
        int idx = 0;
        for (int a_idx : a_indices) {
            while (idx < b_len && b_indices.get(idx) < a_idx - k) {
                idx++;
            }
            if (idx < b_len && Math.abs(b_indices.get(idx) - a_idx) <= k) {
                res.add(a_idx);
            }
        }

        return res;
    }

    public static List<Integer> kmp(String text, String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        int c = 0;
        for (int i = 1; i < m; i++) {
            while (c > 0 && pattern.charAt(c) != pattern.charAt(i)) {
                c = pi[c - 1];
            }
            if (pattern.charAt(c) == pattern.charAt(i)) {
                c++;
            }
            pi[i] = c;
        }

        List<Integer> res = new ArrayList<>();
        int n = text.length();
        c = 0;
        for (int i = 0; i < n; i++) {
            while (c > 0 && pattern.charAt(c) != text.charAt(i)) {
                c = pi[c - 1];
            }
            if (pattern.charAt(c) == text.charAt(i)) {
                c++;
            }
            if (c == m) {
                res.add(i - m + 1);
                c = pi[c - 1];
            }
        }
        return res;
    }

    public static void invoke_beautifulIndices() {
        String s = "isawsquirrelnearmysquirrelhouseohmy";
        String a = "my";
        String b = "squirrel";
        int k = 15;
        System.out.println(beautifulIndices(s, a, b, k));
    }

    public static void main(String[] args) {
//        invoke_maxFrequencyElements();
//        invoke_findMaximumNumber();
        invoke_beautifulIndices();
    }
}
