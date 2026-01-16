import java.util.Deque;
import java.util.ArrayDeque;

public class Main {

    // General k-day cooldown solution
    public static int maxProfitWithCooldown(int[] prices, int k) {

        int profitWithoutStock = 0;
        int profitWithStock = Integer.MIN_VALUE;

        // history[i] holds profitWithoutStock from i days ago
        Deque<Integer> history = new ArrayDeque<>();

        // Before day 0, profitWithoutStock = 0
        // We need k+1 days of history
        for (int i = 0; i <= k; i++) {
            history.addLast(0);
        }

        for (int price : prices) {

            int prevWithout = profitWithoutStock;
            int prevWith = profitWithStock;

            // SELL or REST (sell is never delayed)
            profitWithoutStock = Math.max(
                prevWithout,
                prevWith + price
            );

            // Shift history BEFORE BUY
            history.pollFirst();
            history.addLast(prevWithout);

            // BUY or REST (must respect cooldown)
            int allowedBuyBase = history.peekFirst();

            profitWithStock = Math.max(
                prevWith,
                allowedBuyBase - price
            );
        }

        return profitWithoutStock;
    }

    // ------------------- TEST DRIVER -------------------
    public static void main(String[] args) {

        test(new int[]{1, 2, 3, 0, 2}, 1, 3);  // LeetCode 309
        test(new int[]{1, 2, 3, 0, 2}, 2, 2);  // cooldown = 2
        test(new int[]{7, 1, 5, 3, 6, 4}, 0, 7);     // LeetCode 122
        test(new int[]{7, 6, 4, 3, 1}, 1, 0);  // decreasing prices
        test(new int[]{1, 5}, 5, 4);           // cooldown > n
        test(new int[]{2, 1, 4}, 1, 3);
    }

    private static void test(int[] prices, int k, int expected) {
        int result = maxProfitWithCooldown(prices, k);
        System.out.println(
            "prices=" + java.util.Arrays.toString(prices) +
            ", cooldown=" + k +
            " -> profit=" + result +
            " (expected " + expected + ")"
        );
    }
}
