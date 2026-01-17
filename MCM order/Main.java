public class Main {

    static int[][] cutDP;   // for back-engineering

    static String matrixChainOrder(int[] arr) {
        int n = arr.length;

        int[][] dp = new int[n][n];
        String[][] sdp = new String[n][n];
        cutDP = new int[n][n];

        fillDP(arr, dp, sdp);

        printDPWithBorders(dp);
        printSDPWithBorders(sdp);

        // back-engineered answer
        String backEnggAns = backEngg(0, n - 1);

        System.out.println("\nOptimal Parenthesization (Back-Engineering):");
        System.out.println(backEnggAns);

        return sdp[0][n - 1]; // inline DP result
    }

    static void fillDP(int[] arr, int[][] dp, String[][] sdp) {
        int n = arr.length;

        for (int gap = 1; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {

                if (ei - si == 1) {
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char) ('A' + si) + "";
                    continue;
                }

                int minRes = (int) 1e9;
                String minStr = "";
                int bestCut = -1;

                for (int cut = si + 1; cut < ei; cut++) {
                    int cost = dp[si][cut]
                             + arr[si] * arr[cut] * arr[ei]
                             + dp[cut][ei];

                    if (cost < minRes) {
                        minRes = cost;
                        minStr = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                        bestCut = cut;
                    }
                }

                dp[si][ei] = minRes;
                sdp[si][ei] = minStr;
                cutDP[si][ei] = bestCut; // store decision
            }
        }
    }

    // ---------- BACK-ENGINEERING ----------
    static String backEngg(int si, int ei) {

        if (ei - si == 1) {
            return (char) ('A' + si) + "";
        }

        int cut = cutDP[si][ei];

        String left = backEngg(si, cut);
        String right = backEngg(cut, ei);

        return "(" + left + right + ")";
    }

    // ---------- DP TABLE ----------
    static void printDPWithBorders(int[][] dp) {
        int n = dp.length;
        int cellWidth = 6;

        System.out.println("\nDP Table (Cost):");
        printBorder(n, cellWidth);

        for (int i = 0; i < n; i++) {
            System.out.print("|");
            for (int j = 0; j < n; j++) {
                System.out.printf(" %" + cellWidth + "d |", dp[i][j]);
            }
            System.out.println();
            printBorder(n, cellWidth);
        }
    }

    // ---------- SDP TABLE ----------
    static void printSDPWithBorders(String[][] sdp) {
        int n = sdp.length;
        int cellWidth = 13;

        System.out.println("\nSDP Table (Parenthesization):");
        printBorder(n, cellWidth);

        for (int i = 0; i < n; i++) {
            System.out.print("|");
            for (int j = 0; j < n; j++) {
                String val = (sdp[i][j] == null) ? "-" : sdp[i][j];
                System.out.printf(" %-"+cellWidth+"s |", val);
            }
            System.out.println();
            printBorder(n, cellWidth);
        }
    }

    // ---------- SHARED BORDER ----------
    static void printBorder(int cols, int cellWidth) {
        System.out.print("+");
        for (int c = 0; c < cols; c++) {
            for (int k = 0; k < cellWidth + 2; k++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }

    // Client
    public static void main(String[] args) {
        int[] arr = {40, 20, 30, 10, 30};

        String result = matrixChainOrder(arr);

        System.out.println("\nOptimal Parenthesization (Inline SDP):");
        System.out.println(result);
    }
}
