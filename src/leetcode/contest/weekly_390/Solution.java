package leetcode.contest.weekly_390;

import java.util.*;

public class Solution {

    public int maximumLengthSubstring(String s) {
        int max = 0;

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        for (int right = 0; right < s.length(); ++right) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.get(c) > 2) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    public void invoke_maximumLengthSubstring() {
        String s = "bcbbbcba";
        System.out.println(maximumLengthSubstring(s));

        String s2 = "eebadadbfa";
        System.out.println(maximumLengthSubstring(s2));
    }

    public int minOperations(int k) {
        int min = Integer.MAX_VALUE;
        int half = k % 2 == 0 ? k / 2 : k / 2 + 1;
        while (half > 0) {
            int duplicate = k % half == 0 ? k / half : k / half + 1;
            min = Math.min(min, (half - 1) + (duplicate - 1));
            half--;
        }

        return min;
    }

    public void invoke_minOperations() {
        System.out.println(minOperations(11));
        System.out.println(minOperations(1));
        System.out.println(minOperations(2));
        System.out.println(minOperations(3));
        System.out.println(minOperations(4));
    }

    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        int n = nums.length;
        long[] result = new long[n];

        Map<Integer, Long> map = new HashMap<>();
        TreeMap<Long, Integer> freqMap = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < n; ++i) {
            long prevFreq = map.getOrDefault(nums[i], 0L);
            long currFreq = prevFreq + freq[i];
            map.put(nums[i], currFreq);
            freqMap.put(prevFreq, freqMap.getOrDefault(prevFreq, 0) - 1);
            if (freqMap.get(prevFreq) == 0) {
                freqMap.remove(prevFreq);
            }
            freqMap.put(currFreq, freqMap.getOrDefault(currFreq, 0) + 1);
            result[i] = freqMap.firstKey();
        }

        return result;
    }

    public void invoke_mostFrequentIDs() {
        int[] nums = {2,3,2,1};
        int[] freq = {3,2,-3,1};
        long[] result = mostFrequentIDs(nums, freq);
        for (long num : result) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] nums2 = {5,5,3};
        int[] freq2 = {2,-2,1};
        long[] result2 = mostFrequentIDs(nums2, freq2);
        for (long num : result2) {
            System.out.print(num + " ");
        }
    }

    // TODO: Adjust this function (813 / 816 test cases passed.)
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int n = wordsQuery.length;
        int[] result = new int[n];

        for (int i = 0; i < n; ++i) {
            String query = wordsQuery[i];
            int max = 0;
            int index = 0;
            for (int j = 0; j < wordsContainer.length; ++j) {
                String container = wordsContainer[j];
                int len = 0;
                int k = query.length() - 1;
                int l = container.length() - 1;

                while (k >= 0 && l >= 0 && query.charAt(k) == container.charAt(l)) {
                    len++;
                    k--;
                    l--;
                }

                if (len > max || (len == max && container.length() < wordsContainer[index].length())) {
                    max = len;
                    index = j;
                }
            }

            result[i] = index;
        }

        return result;
    }

    public void invoke_stringIndices() {
        String[] wordsContainer = {"abcd","bcd","xbcd"};
        String[] wordsQuery = {"cd","bcd","xyz"};
        int[] result = stringIndices(wordsContainer, wordsQuery);
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();

        String[] wordsContainer2 = {"abcdefgh","poiuygh","ghghgh"};
        String[] wordsQuery2 = {"gh","acbfgh","acbfegh"};
        int[] result2 = stringIndices(wordsContainer2, wordsQuery2);
        for (int num : result2) {
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.invoke_maximumLengthSubstring();
//        solution.invoke_minOperations();
//        solution.invoke_mostFrequentIDs();
        solution.invoke_stringIndices();
    }
}
