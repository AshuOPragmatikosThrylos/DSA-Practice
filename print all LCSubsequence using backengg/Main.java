import java.util.*;

public class Main {

    // ---------------- LCS DP (length) ----------------
    static int[][] lcs_dp(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp;
    }

    // ---------------- BACK-ENGINEERING (ALL LCS) ----------------
    static Set<String> lcs_backEng_all(String s1, String s2, int i, int j, int[][] dp) {

        Set<String> res = new HashSet<>();

        if (i == 0 || j == 0) {
            res.add("");
            return res;
        }

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

            Set<String> prev = lcs_backEng_all(s1, s2, i - 1, j - 1, dp);
            for (String p : prev) {
                res.add(p + s1.charAt(i - 1));
            }

        } else {

            if (dp[i - 1][j] == dp[i][j]) {
                res.addAll(lcs_backEng_all(s1, s2, i - 1, j, dp));
            }

            if (dp[i][j - 1] == dp[i][j]) {
                res.addAll(lcs_backEng_all(s1, s2, i, j - 1, dp));
            }
        }

        return res;
    }

    // ---------------- CLIENT ----------------
    public static void main(String[] args) {

        String s1 = "abcbbad";
        String s2 = "bdcab";

        int[][] dp = lcs_dp(s1, s2);

        Set<String> allLCS = lcs_backEng_all(s1, s2, s1.length(), s2.length(), dp);

        System.out.println("String 1     : " + s1);
        System.out.println("String 2     : " + s2);
        System.out.println("LCS length  : " + dp[s1.length()][s2.length()]);
        System.out.println("All LCS:");

        for (String s : allLCS) {
            System.out.println(s);
        }
    }
}
