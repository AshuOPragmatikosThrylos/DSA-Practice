import java.util.*;

public class Main {

    // idea 1- element creates its own group OR joins existing groups (T -> B) (DP)
    static int tabu_1(int n, int k) {
        if (n == 0 || k == 0 || k > n)
            return 0;
        if (k == 1 || k == n)
            return 1;

        return k * tabu_1(n - 1, k) + tabu_1(n - 1, k - 1);
    }

    // idea 2 - element on level, non-empty OR first empty partition (B -> T) (backtracking)
    // can't be converted correctly to DP using (recur --> memo --> tabu) template cuz no two parts of the tree are same in branching (no of branches, and from and to states)
    static int btRecur(int n, int ele, int k, List < List < Integer >> ans) {

        if (ele > n) {
            for (List < Integer > part: ans) {
                if (part.size() == 0)
                    return 0;
            }

            // System.out.println(ans); 
            return 1;
        }

        int count = 0;

        for (int i = 0; i < k; i++) {

            ans.get(i).add(ele);
            count += btRecur(n, ele + 1, k, ans);
            ans.get(i).remove(ans.get(i).size() - 1);

            if (ans.get(i).size() == 0)
                break;
        }

        return count;
    }

    // idea 2 - element on level, non-empty OR first empty partition (T -> B)
    static int recur(int ele, int used, int n, int k) {

        if (ele == n) {
            return (used == k) ? 1 : 0;
        }

        if (used > k)
            return 0;

        int ways = 0;

        // put element into any non-empty partition
        if (used > 0) {
            ways += used * recur(ele + 1, used, n, k);
        }

        // put element into first empty partition
        if (used < k) {
            ways += recur(ele + 1, used + 1, n, k);
        }

        return ways;
    }

    static int[][] memo;

    static int memo(int n, int k) {

        memo = new int[n + 1][k + 1];
        for (int[] row: memo)
            Arrays.fill(row, -1);

        return memoDfs(0, 0, n, k);
    }

    // idea 2 - T-> B
    static int memoDfs(int ele, int used, int n, int k) {

        if (ele == n)
            return (used == k) ? 1 : 0;

        if (used > k)
            return 0;

        if (memo[ele][used] != -1)
            return memo[ele][used];

        int ways = 0;

        if (used > 0)
            ways += used * memoDfs(ele + 1, used, n, k);

        if (used < k)
            ways += memoDfs(ele + 1, used + 1, n, k);

        return memo[ele][used] = ways;
    }

    // idea 2 - T-> B
    static int tabu_2(int n, int k) {

        int[][] dp = new int[n + 1][k + 1];

        // base
        dp[0][0] = 1;

        for (int ele = 0; ele < n; ele++) {
            for (int used = 0; used <= k; used++) {

                if (dp[ele][used] == 0)
                    continue;

                // non-empty partitions
                if (used > 0)
                    dp[ele + 1][used] += used * dp[ele][used];

                // first empty partition
                if (used < k)
                    dp[ele + 1][used + 1] += dp[ele][used];
            }
        }

        return dp[n][k];
    }


    public static void main(String[] args) {

        int n = 5, k = 2;

        System.out.println("DP count: " + tabu_1(n, k));


        List < List < Integer >> ans = new ArrayList < > ();
        for (int i = 0; i < k; i++)
            ans.add(new ArrayList < > ());

        int btCount = btRecur(n, 1, k, ans);
        System.out.println("BT count: " + btCount);

        System.out.println("Recur   : " + recur(0, 0, n, k)); // exponential
        System.out.println("Memo    : " + memo(n, k)); // O(n*k)
        System.out.println("Tabu_2  : " + tabu_2(n, k)); // O(n*k)
    }
}


// DP count: 15
// BT count: 15
// Recur   : 15
// Memo    : 15
// Tabu_2  : 15