public class Main {

    // ---------- given back-engineering function ----------
    static String lps_backEng(String str, int si, int ei, int[][] dp) {

        if (si > ei)
            return "";

        if (si == ei)
            return str.charAt(si) + "";

        if (str.charAt(si) == str.charAt(ei)) {
            return str.charAt(si)
                    + lps_backEng(str, si + 1, ei - 1, dp)
                    + str.charAt(ei);
        }

        // skip left
        else if (dp[si + 1][ei] > dp[si][ei - 1]) {
            return lps_backEng(str, si + 1, ei, dp);
        }

        // skip right
        else {
            return lps_backEng(str, si, ei - 1, dp);
        }
    }

    // ---------- DP for LPS length ----------
    static int[][] lps_dp(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // gap strategy
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

    // ---------- client ----------
    public static void main(String[] args) {

        // String str = "bbbab";   // change test case here
        String str = "abcbbad";   // change test case here
        int n = str.length();

        int[][] dp = lps_dp(str);

        String lps = lps_backEng(str, 0, n - 1, dp);

        System.out.println("String : " + str);
        System.out.println("LPS length : " + dp[0][n - 1]);
        System.out.println("LPS : " + lps);
    }
}
