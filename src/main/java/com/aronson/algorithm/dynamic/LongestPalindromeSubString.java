package com.aronson.algorithm.dynamic;

/**
 * @author Sherlock
 * 最长回文子串
 */
public class LongestPalindromeSubString {
    public static void main(String[] args) {
        String s = "babadcccciouuoouuo997";
        String longest = "";
        // 暴力解法，没有利用好已经做过比较的状态
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                String compare = s.substring(i, j);
                StringBuilder stringBuilder = new StringBuilder(compare);
                if (stringBuilder.reverse().toString().equals(compare) && compare.length() > longest.length()) {
                    longest = compare;
                }
            }
        }
        System.out.println(longest);
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int lengthDifference = 0; lengthDifference < n; ++lengthDifference) {
            for (int i = 0; i + lengthDifference < n; ++i) {
                int j = i + lengthDifference;
                if (lengthDifference == 0) {
                    // 长度差值为0，即子串长度为1
                    dp[i][j] = true;
                } else if (lengthDifference == 1) {
                    // 边界条件，即长度差值为1，即子串长度为2
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && lengthDifference + 1 > ans.length()) {
                    ans = s.substring(i, i + lengthDifference + 1);
                }
            }
        }
        return ans;
    }
}
