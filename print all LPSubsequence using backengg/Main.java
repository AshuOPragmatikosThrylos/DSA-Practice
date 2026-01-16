import java.util.*;

public class Main {

    // ---------------- LPS DP (length) ----------------
    static int[][] lps_dp(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {

                if (i == j)
                    dp[i][j] = 1;

                else if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = 2 + dp[i + 1][j - 1];

                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp;
    }

    // ---------------- BACK-ENGINEERING (ALL LPS) ----------------
    static Set<String> lps_backEng_all(String s, int si, int ei, int[][] dp) {

    Set<String> res = new HashSet<>();

    if (si > ei) {
        res.add("");
        return res;
    }

    if (si == ei) {
        res.add(s.charAt(si) + "");
        return res;
    }

    if (s.charAt(si) == s.charAt(ei)) {

        Set<String> mids = lps_backEng_all(s, si + 1, ei - 1, dp);
        for (String mid : mids) {
            res.add(s.charAt(si) + mid + s.charAt(ei));
        }

    } else {

        if (dp[si + 1][ei] == dp[si][ei]) {
            res.addAll(lps_backEng_all(s, si + 1, ei, dp));
        }

        if (dp[si][ei - 1] == dp[si][ei]) {
            res.addAll(lps_backEng_all(s, si, ei - 1, dp));
        }
    }

    return res;
}


    // ---------------- CLIENT ----------------
    public static void main(String[] args) {

        // String str = "bbabcbcab";   // change test case here
        String str = "abcbbad";   // change test case here
        int n = str.length();

        int[][] dp = lps_dp(str);

        Set<String> allLPS = lps_backEng_all(str, 0, n - 1, dp);

        System.out.println("String      : " + str);
        System.out.println("LPS length  : " + dp[0][n - 1]);
        System.out.println("All LPS:");

        for (String s : allLPS) {
            System.out.println(s);
        }
    }
}
