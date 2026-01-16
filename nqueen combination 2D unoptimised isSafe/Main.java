public class Main {

    public static int nQueen(boolean[][] board, int row, int tnq, String ans) {

        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int n = board.length;
        int count = 0;

        for (int col = 0; col < n; col++) {

            if (isSafeToPlaceQueen(board, row, col)) {

                board[row][col] = true;

                count += nQueen(board, row + 1, tnq - 1, ans + "(" + row + "," + col + ") ");

                board[row][col] = false;
            }
        }

        return count;
    }

    public static boolean isSafeToPlaceQueen(boolean[][] board, int row, int col) {

        int[][] dir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};

        int n = board.length;

        for (int d = 0; d < 8; d++) {
            for (int rad = 1; rad < n; rad++) {

                int r = row + rad * dir[d][0];
                int c = col + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < n) {
                    if (board[r][c]) return false;
                } else break;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int n = 4;
        boolean[][] board = new boolean[n][n];

        int ans = nQueen(board, 0, n, "");
        System.out.println("Total Solutions = " + ans);
    }
}
