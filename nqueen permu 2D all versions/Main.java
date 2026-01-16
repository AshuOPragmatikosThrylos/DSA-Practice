import java.util.*;

public class Main {

    static int nqueen_perm(boolean[][] board, int row, int tnq, boolean[] usedQueen, int[] cols, int[] queenIds, List<int[]> solutionCols, List<int[]> solutionIds) {

        if (row == tnq) {
            solutionCols.add(cols.clone());
            solutionIds.add(queenIds.clone());
            return 1;
        }

        int n = board.length;
        int count = 0;

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {

                for (int q = 0; q < tnq; q++) {
                    if (!usedQueen[q]) {

                        usedQueen[q] = true;
                        board[row][col] = true;

                        cols[row] = col;      // store column
                        queenIds[row] = q;    // store queen id in this row

                        count += nqueen_perm(board, row + 1, tnq, usedQueen, cols, queenIds, solutionCols, solutionIds);

                        usedQueen[q] = false;
                        board[row][col] = false;
                    }
                }
            }
        }
        return count;
    }

    // static boolean isSafe(boolean[][] board, int row, int col) {

    //     int[][] dir = {
    //         {0,-1},{-1,-1},{-1,0},{-1,1},
    //         {0,1},{1,1},{1,0},{1,-1}
    //     };

    //     int n = board.length;

    //     for (int d = 0; d < 8; d++) {
    //         for (int rad = 1; rad < n; rad++) {
    //             int r = row + rad * dir[d][0];
    //             int c = col + rad * dir[d][1];

    //             if (r >= 0 && c >= 0 && r < n && c < n) {
    //                 if (board[r][c]) return false;
    //             } else break;
    //         }
    //     }
    //     return true;
    // }

    static int nqueen_perm(int n, int row, int tnq, boolean[] usedQueen, boolean[] col, boolean[] diag, boolean[] aDiag, int[] cols, int[] queenIds, List<int[]> solutionCols, List<int[]> solutionIds) {

        if (row == tnq) {
            solutionCols.add(cols.clone());
            solutionIds.add(queenIds.clone());
            return 1;
        }

        int count = 0;

        for (int c = 0; c < n; c++) {

            if (!col[c] && !diag[row + c] && !aDiag[row - c + n - 1]) {

                for (int q = 0; q < tnq; q++) {
                    if (!usedQueen[q]) {

                        usedQueen[q] = true;
                        col[c] = diag[row + c] = aDiag[row - c + n - 1] = true;

                        cols[row] = c;
                        queenIds[row] = q;

                        count += nqueen_perm(n, row + 1, tnq, usedQueen, col, diag, aDiag, cols, queenIds, solutionCols, solutionIds);

                        usedQueen[q] = false;
                        col[c] = diag[row + c] = aDiag[row - c + n - 1] = false;
                    }
                }
            }
        }
        return count;
    }

    static int nqueen_perm(int n, int row, int tnq, int colMask, int diagMask, int aDiagMask, int usedMask, int[] cols, int[] queenIds, List<int[]> solutionCols, List<int[]> solutionIds) {

        if (row == tnq) {
            solutionCols.add(cols.clone());
            solutionIds.add(queenIds.clone());
            return 1;
        }

        int count = 0;

        for (int c = 0; c < n; c++) {

            int colBit   = 1 << c;
            int diagBit  = 1 << (row + c);
            int aDiagBit = 1 << (row - c + n - 1);

            if ((colMask & colBit) == 0 &&
                (diagMask & diagBit) == 0 &&
                (aDiagMask & aDiagBit) == 0) {

                for (int q = 0; q < tnq; q++) {

                    int qBit = 1 << q;
                    if ((usedMask & qBit) == 0) {

                        cols[row] = c;
                        queenIds[row] = q;

                        count += nqueen_perm(n, row + 1, tnq, colMask ^ colBit, diagMask ^ diagBit, aDiagMask ^ aDiagBit, usedMask ^ qBit, cols, queenIds, solutionCols, solutionIds);
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int n = 4;
        int tnq = 4;

        // -------- 1) Board + isSafe --------
        boolean[][] board = new boolean[n][n];
        boolean[] usedQ1 = new boolean[tnq];
        int[] cols1 = new int[tnq];
        int[] ids1  = new int[tnq];
        List<int[]> solCols1 = new ArrayList<>();
        List<int[]> solIds1  = new ArrayList<>();

        // int count1 = nqueen_perm(board, 0, tnq,
        //                                usedQ1, cols1, ids1,
        //                                solCols1, solIds1);

        // System.out.println("Board + isSafe  => " + count1);

        // -------- 2) Boolean attack arrays --------
        boolean[] usedQ2 = new boolean[tnq];
        boolean[] col = new boolean[n];
        boolean[] diag = new boolean[2 * n - 1];
        boolean[] aDiag = new boolean[2 * n - 1];
        int[] cols2 = new int[tnq];
        int[] ids2  = new int[tnq];
        List<int[]> solCols2 = new ArrayList<>();
        List<int[]> solIds2  = new ArrayList<>();

        // int count2 = nqueen_perm(n, 0, tnq,
        //                               usedQ2,
        //                               col, diag, aDiag,
        //                               cols2, ids2,
        //                               solCols2, solIds2);

        // System.out.println("Bool attacks    => " + count2);

        // // -------- 3) Bitmask version --------
        int[] cols3 = new int[tnq];
        int[] ids3  = new int[tnq];
        List<int[]> solCols3 = new ArrayList<>();
        List<int[]> solIds3  = new ArrayList<>();

        int count3 = nqueen_perm(n, 0, tnq,
                                     0, 0, 0, 0,
                                     cols3, ids3,
                                     solCols3, solIds3);

        System.out.println("Bitmasks        => " + count3);
        System.out.println();

        // Print from one version (e.g., board)
        // for (int i = 0; i < solCols1.size(); i++) {
        //     printBoardNumbered(n,
        //                        solCols1.get(i),
        //                        solIds1.get(i));
        // }

        // for (int i = 0; i < solCols2.size(); i++) {
        //     printBoardNumbered(n,
        //                        solCols2.get(i),
        //                        solIds2.get(i));
        // }
        for (int i = 0; i < solCols3.size(); i++) {
            printBoardNumbered(n,
                               solCols3.get(i),
                               solIds3.get(i));
        }
    }

    // =====================================================
    // NUMBERED BOARD PRINTER (1..n)
    // =====================================================
    static void printBoardNumbered(int n, int[] cols, int[] queenIds) {

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.print(
                    cols[r] == c ? (queenIds[r] + 1) + " " : ". "
                );
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }
}
