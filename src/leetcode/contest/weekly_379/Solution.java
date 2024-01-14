package leetcode.contest.weekly_379;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    // https://leetcode.com/contest/weekly-contest-379/problems/maximum-area-of-longest-diagonal-rectangle/
    // 3000. Maximum Area of Longest Diagonal Rectangle
    public static int areaOfMaxDiagonal(int[][] dimensions) {
        int max_diagonal_square = 0;
        int area = 0;

        for (int[] dimension : dimensions) {
            int diagonal_square = dimension[0] * dimension[0] + dimension[1] * dimension[1];
            if (diagonal_square > max_diagonal_square || (diagonal_square == max_diagonal_square && dimension[0] * dimension[1] > area)) {
                max_diagonal_square = diagonal_square;
                area = dimension[0] * dimension[1];
            }
        }

        return area;
    }

    public static void invoke_areaOfMaxDiagonal() {
        int[][] dimensions = new int[][]{{9, 3}, {8, 6}};
        System.out.println(areaOfMaxDiagonal(dimensions));
    }

    // https://leetcode.com/contest/weekly-contest-379/problems/minimum-moves-to-capture-the-queen/
    // 3001. Minimum Moves to Capture The Queen
    public static int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        final int[] DR_ROOK = {-1, 0, 1, 0};
        final int[] DC_ROOK = {0, 1, 0, -1};

        final int[] DR_BISHOP = {-1, 1, 1, -1};
        final int[] DC_BISHOP = {1, 1, -1, -1};

        for (int dd = 0; dd < 4; ++dd) {
            for (int i = 0; ; ++i) {
                int rr = a + i * DR_ROOK[dd];
                int cc = b + i * DC_ROOK[dd];
                if (rr == e && cc == f) {
                    return 1;
                }
                if (rr < 1 || rr > 8 || cc < 1 || cc > 8 || (rr == c && cc == d)) {
                    break;
                }
            }
        }

        for (int dd = 0; dd < 4; ++dd) {
            for (int i = 0; ; ++i) {
                int rr = c + i * DR_BISHOP[dd];
                int cc = d + i * DC_BISHOP[dd];
                if (rr == e && cc == f) {
                    return 1;
                }
                if (rr < 1 || rr > 8 || cc < 1 || cc > 8 || (rr == a && cc == b)) {
                    break;
                }
            }
        }

        return 2;
    }

    public static void invoke_minMovesToCaptureTheQueen() {
        System.out.println(minMovesToCaptureTheQueen(5, 3, 3, 4, 5, 2));
    }

    // https://leetcode.com/contest/weekly-contest-379/problems/maximum-size-of-a-set-after-removals/
    // 3002. Maximum Size of a Set After Removals
    public static int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            set2.add(num);
        }

        int common = 0;
        for (int num : set1) {
            if (set2.contains(num)) {
                common++;
            }
        }

        int n1 = set1.size();
        int n2 = set2.size();
        int ans = n1 + n2 - common;

        int m = nums1.length / 2;
        if (n1 > m) {
            int mn = Math.min(n1 - m, common);
            n1 -= mn;
            common -= mn;
            ans -= n1 - m;
        }

        if (n2 > m) {
            int mn = Math.min(n2 - m, common);
            n2 -= mn;
            ans -= n2 - m;
        }

        return ans;
    }

    public static void invoke_maximumSetSize() {
        int[] nums1 = new int[]{1, 2, 3, 4, 5, 6};
        int[] nums2 = new int[]{2, 3, 2, 3, 2, 3};
        System.out.println(maximumSetSize(nums1, nums2));
    }

    // https://leetcode.com/contest/weekly-contest-379/problems/maximize-the-number-of-partitions-after-operations/
    // 3003. Maximize the Number of Partitions After Operations
    private static final Map<Long, Integer> memo = new HashMap<>();

    public static int maxPartitionsAfterOperations(String s, int k) {
        return dfs(0, 0, 0, s.toCharArray(), k);
    }

    private static int dfs(int i, int mask, int changed, char[] s, int k) {
        if (i == s.length) {
            return 1;
        }

        long argsMask = (long) i << 32 | mask << 1 | changed;
        if (memo.containsKey(argsMask)) {
            return memo.get(argsMask);
        }

        int res;

        int bit = 1 << (s[i] - 'a');
        int newMask = mask | bit;
        if (Integer.bitCount(newMask) > k) {
            res = dfs(i + 1, bit, changed, s, k) + 1;
        } else {
            res = dfs(i + 1, newMask, changed, s, k);
        }

        if (changed == 0) {
            for (int j = 0; j < 26; j++) {
                newMask = mask | (1 << j);
                if (Integer.bitCount(newMask) > k) {
                    res = Math.max(res, dfs(i + 1, 1 << j, 1, s, k) + 1);
                } else { // 不分割
                    res = Math.max(res, dfs(i + 1, newMask, 1, s, k));
                }
            }
        }

        memo.put(argsMask, res);
        return res;
    }

    public static void invoke_maxPartitionsAfterOperations() {
        String s = "accca";
        int k = 2;
        int result = maxPartitionsAfterOperations(s, k);
        System.out.println(result);
        assert result == 3;
    }

    public static void main(String[] args) {
//        invoke_areaOfMaxDiagonal();
//        invoke_minMovesToCaptureTheQueen();
//        invoke_maximumSetSize();
        invoke_maxPartitionsAfterOperations();
    }
}
