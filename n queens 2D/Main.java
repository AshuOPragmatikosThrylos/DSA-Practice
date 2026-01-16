import java.util.*;

public class Main {

    // PRINT BOARD WITH Q (for combination)
    static void printBoardQ(int n, int[] cols) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.print(cols[r] == c ? "Q " : ". ");
            }
            System.out.println();
        }
        System.out.println("------------");
    }

    // PRINT BOARD WITH NUMBERED QUEENS (for permutation)
    static void printBoardNumbered(int n, int[] cols, int[] queenIds) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.print(cols[r] == c ? (queenIds[r] + 1) + " " : ". ");
            }
            System.out.println();
        }
        System.out.println("------------");
    }

    static void nqueen_combi(int n, int row,
        boolean[] col, boolean[] diag, boolean[] aDiag,
        int[] cols, List < int[] > solutions) {

        if (row == n) {
            solutions.add(cols.clone());
            return;
        }

        for (int c = 0; c < n; c++) {
            if (!col[c] && !diag[row + c] && !aDiag[row - c + n - 1]) {

                col[c] = diag[row + c] = aDiag[row - c + n - 1] = true;
                cols[row] = c; // note: not bactracked

                nqueen_combi(n, row + 1, col, diag, aDiag, cols, solutions);

                col[c] = diag[row + c] = aDiag[row - c + n - 1] = false;
            }
        }
    }

    static void nqueen_permutation(int n, int row,
        int[] cols, int[] queenIds,
        boolean[] colUsed,
        boolean[] diag, boolean[] aDiag,
        boolean[] queenUsed,
        List < int[] > solutionsCols,
        List < int[] > solutionsIds) {

        if (row == n) {
            solutionsCols.add(cols.clone());
            solutionsIds.add(queenIds.clone());
            return;
        }

        for (int c = 0; c < n; c++) {
            if (colUsed[c] || diag[row + c] || aDiag[row - c + n - 1])
                continue;

            for (int q = 0; q < n; q++) { // try all queen IDs
                if (queenUsed[q])
                    continue;

                // place queen q at (row, c)
                colUsed[c] = true;
                diag[row + c] = true;
                aDiag[row - c + n - 1] = true;
                queenUsed[q] = true;

                cols[row] = c; // note: not bactracked
                queenIds[row] = q; // note: not bactracked

                nqueen_permutation(n, row + 1, cols, queenIds, colUsed, diag, aDiag, queenUsed, solutionsCols, solutionsIds);

                colUsed[c] = false;
                diag[row + c] = false;
                aDiag[row - c + n - 1] = false;
                queenUsed[q] = false;
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;

        List < int[] > combiSols = new ArrayList < > ();
        nqueen_combi(
            n, 0,
            new boolean[n],
            new boolean[2 * n - 1],
            new boolean[2 * n - 1],
            new int[n],
            combiSols
        );

        System.out.println("Combination solutions: " + combiSols.size());
        for (int[] sol: combiSols) {
            printBoardQ(n, sol);
        }

        List < int[] > permuSolsCols = new ArrayList < > ();
        List < int[] > permuSolsIds = new ArrayList < > ();
        nqueen_permutation(
            n, 0,
            new int[n], new int[n],
            new boolean[n],
            new boolean[2 * n - 1],
            new boolean[2 * n - 1],
            new boolean[n],
            permuSolsCols,
            permuSolsIds
        );

        System.out.println("Permutation solutions: " + permuSolsCols.size());
        for (int i = 0; i < permuSolsCols.size(); i++) {
            printBoardNumbered(n, permuSolsCols.get(i), permuSolsIds.get(i));
        }
    }
}

// Combination solutions: 2
// . Q . .
// . . . Q
// Q . . .
// . . Q .
// ------------
// . . Q .
// Q . . .
// . . . Q
// . Q . .
// ------------
// Permutation solutions: 48
// . 1 . .
// . . . 2
// 3 . . .
// . . 4 .
// ------------
// . 1 . .
// . . . 2
// 4 . . .
// . . 3 .
// ------------
// . 1 . .
// . . . 3
// 2 . . .
// . . 4 .
// ------------
// . 1 . .
// . . . 3
// 4 . . .
// . . 2 .
// ------------
// . 1 . .
// . . . 4
// 2 . . .
// . . 3 .
// ------------
// . 1 . .
// . . . 4
// 3 . . .
// . . 2 .
// ------------
// . 2 . .
// . . . 1
// 3 . . .
// . . 4 .
// ------------
// . 2 . .
// . . . 1
// 4 . . .
// . . 3 .
// ------------
// . 2 . .
// . . . 3
// 1 . . .
// . . 4 .
// ------------
// . 2 . .
// . . . 3
// 4 . . .
// . . 1 .
// ------------
// . 2 . .
// . . . 4
// 1 . . .
// . . 3 .
// ------------
// . 2 . .
// . . . 4
// 3 . . .
// . . 1 .
// ------------
// . 3 . .
// . . . 1
// 2 . . .
// . . 4 .
// ------------
// . 3 . .
// . . . 1
// 4 . . .
// . . 2 .
// ------------
// . 3 . .
// . . . 2
// 1 . . .
// . . 4 .
// ------------
// . 3 . .
// . . . 2
// 4 . . .
// . . 1 .
// ------------
// . 3 . .
// . . . 4
// 1 . . .
// . . 2 .
// ------------
// . 3 . .
// . . . 4
// 2 . . .
// . . 1 .
// ------------
// . 4 . .
// . . . 1
// 2 . . .
// . . 3 .
// ------------
// . 4 . .
// . . . 1
// 3 . . .
// . . 2 .
// ------------
// . 4 . .
// . . . 2
// 1 . . .
// . . 3 .
// ------------
// . 4 . .
// . . . 2
// 3 . . .
// . . 1 .
// ------------
// . 4 . .
// . . . 3
// 1 . . .
// . . 2 .
// ------------
// . 4 . .
// . . . 3
// 2 . . .
// . . 1 .
// ------------
// . . 1 .
// 2 . . .
// . . . 3
// . 4 . .
// ------------
// . . 1 .
// 2 . . .
// . . . 4
// . 3 . .
// ------------
// . . 1 .
// 3 . . .
// . . . 2
// . 4 . .
// ------------
// . . 1 .
// 3 . . .
// . . . 4
// . 2 . .
// ------------
// . . 1 .
// 4 . . .
// . . . 2
// . 3 . .
// ------------
// . . 1 .
// 4 . . .
// . . . 3
// . 2 . .
// ------------
// . . 2 .
// 1 . . .
// . . . 3
// . 4 . .
// ------------
// . . 2 .
// 1 . . .
// . . . 4
// . 3 . .
// ------------
// . . 2 .
// 3 . . .
// . . . 1
// . 4 . .
// ------------
// . . 2 .
// 3 . . .
// . . . 4
// . 1 . .
// ------------
// . . 2 .
// 4 . . .
// . . . 1
// . 3 . .
// ------------
// . . 2 .
// 4 . . .
// . . . 3
// . 1 . .
// ------------
// . . 3 .
// 1 . . .
// . . . 2
// . 4 . .
// ------------
// . . 3 .
// 1 . . .
// . . . 4
// . 2 . .
// ------------
// . . 3 .
// 2 . . .
// . . . 1
// . 4 . .
// ------------
// . . 3 .
// 2 . . .
// . . . 4
// . 1 . .
// ------------
// . . 3 .
// 4 . . .
// . . . 1
// . 2 . .
// ------------
// . . 3 .
// 4 . . .
// . . . 2
// . 1 . .
// ------------
// . . 4 .
// 1 . . .
// . . . 2
// . 3 . .
// ------------
// . . 4 .
// 1 . . .
// . . . 3
// . 2 . .
// ------------
// . . 4 .
// 2 . . .
// . . . 1
// . 3 . .
// ------------
// . . 4 .
// 2 . . .
// . . . 3
// . 1 . .
// ------------
// . . 4 .
// 3 . . .
// . . . 1
// . 2 . .
// ------------
// . . 4 .
// 3 . . .
// . . . 2
// . 1 . .
// ------------