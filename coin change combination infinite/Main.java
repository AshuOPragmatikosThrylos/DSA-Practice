public class Main {

    // ==================================================
    // 1) PURE RECURSION (PRINTS PATHS)
    // ==================================================
    static int recur(int[] arr, int idx, int tar, String path) {
        if (tar == 0) {
            System.out.println(path);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                count += recur(arr, i, tar - arr[i], path + arr[i] + " ");
            }
        }
        return count;
    }

    // ==================================================
    // 2) 2D DP (MEMOIZATION)
    // dp[idx][tar] = number of combinations
    // ==================================================
    static int fillDP(int[] arr, int idx, int tar, Integer[][] dp) {

        if (tar == 0) {
            dp[idx][tar] = 1;          // IMPORTANT: memoize base case
            return 1;
        }

        if (dp[idx][tar] != null)
            return dp[idx][tar];

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                count += fillDP(arr, i, tar - arr[i], dp);
            }
        }

        dp[idx][tar] = count;
        return count;
    }

    // ==================================================
    // 3) BACKTRACK USING 2D DP (PRINT PATHS)
    // ==================================================
    static void printFromDP(int[] arr, int idx, int tar,
                            Integer[][] dp, String path) {

        if (tar == 0) {
            System.out.println(path);
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            int ele = arr[i];

            if (tar - ele >= 0 &&
                dp[i][tar - ele] != null &&
                dp[i][tar - ele] > 0) {

                printFromDP(arr, i, tar - ele, dp, path + ele + " ");
            }
        }
    }

    // ==================================================
    // 4) 1D DP (COUNT ONLY – COMBINATIONS)
    // ==================================================
    static int coinChange1D(int[] coins, int target) {

        int[] dp = new int[target + 1];
        dp[0] = 1;   // base case

        for (int coin : coins) {           // coin outer → combinations
            for (int t = coin; t <= target; t++) {
                dp[t] += dp[t - coin];
            }
        }

        return dp[target];
    }

    // ==================================================
    // MAIN
    // ==================================================
    public static void main(String[] args) {

        int[] coins = {2, 3, 5, 6, 8};
        int target = 10;

        // ---------------- RECURSION ----------------
        System.out.println("=== RECURSION (PATHS) ===");
        int recAns = recur(coins, 0, target, "");
        System.out.println("Total (Recursion) = " + recAns);

        // ---------------- 2D DP ----------------
        System.out.println("\n=== 2D DP BACKTRACK (PATHS) ===");
        Integer[][] dp2D = new Integer[coins.length + 1][target + 1];
        int dp2DAns = fillDP(coins, 0, target, dp2D);
        printFromDP(coins, 0, target, dp2D, "");
        System.out.println("Total (2D DP) = " + dp2DAns);

        // ---------------- 1D DP ----------------
        System.out.println("\n=== 1D DP (COUNT ONLY) ===");
        int dp1DAns = coinChange1D(coins, target);
        System.out.println("Total (1D DP) = " + dp1DAns);
    }
}
