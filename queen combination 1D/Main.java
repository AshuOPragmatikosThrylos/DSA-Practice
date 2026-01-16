public class Main {

    public static void main(String[] args) {

        int tnb = 4;
        int tnq = 3;

        boolean[] vis = new boolean[tnb];

        System.out.println("1) Queen chooses -> Combination:");
        System.out.println("Count = " + queenComb_QueenChooses(0, tnq, tnb, 0, ""));

        System.out.println("\n2) Queen chooses -> Subsequence:");
        System.out.println("Count = " + queenComb_QueenChooses_Subseq(0, tnq, tnb, 0, ""));

        System.out.println("\n3) Box chooses -> Combination:");
        System.out.println("Count = " + queenComb_BoxChooses(0, tnq, tnb, 0, ""));

        System.out.println("\n4) Box chooses -> Subsequence:");
        System.out.println("Count = " + queenComb_BoxChooses_Subseq(0, tnq, tnb, 0, ""));

        System.out.println("\n5) Queen chooses -> Permutation:");
        System.out.println("Count = " + queenPerm_QueenChooses(0, tnq, tnb, vis, ""));

        System.out.println("\n6) Queen chooses -> Permutation (Subsequence):");
        System.out.println("Count = " + queenPerm_QueenChooses_Subseq(0, tnq, tnb, 0, vis, ""));

        System.out.println("\n7) Box chooses -> Permutation:");
        System.out.println("Count = " + queenPerm_BoxChooses(0, tnq, tnb, 0, vis, ""));

        System.out.println("\n8) Box chooses -> Permutation (Subsequence):");
        System.out.println("Count = " + queenPerm_BoxChooses_Subseq(0, tnq, tnb, 0, vis, ""));
    }

    static int queenComb_QueenChooses(int qNo, int tnq, int tnb, int bidx, String ans) {
        if (qNo == tnq) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = bidx; i < tnb; i++) {
            count += queenComb_QueenChooses(qNo + 1, tnq, tnb, i + 1,
                ans + "Q" + qNo + "->B" + i + " ");
        }
        return count;
    }

    // combination or permutation - subseq approach always needs idx
    static int queenComb_QueenChooses_Subseq(int qNo, int tnq, int tnb, int bidx, String ans) {
        if (bidx == tnb) {
            if (qNo == tnq) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        } else if (qNo == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        count += queenComb_QueenChooses_Subseq(qNo + 1, tnq, tnb, bidx + 1,
            ans + "Q" + qNo + "->B" + bidx + " "); // queen (qNo) chose idx box
        count += queenComb_QueenChooses_Subseq(qNo, tnq, tnb, bidx + 1, ans); // queen didn't choose idx box; qNo queen still needs to choose but decision for idx box has already been made
        return count;
    }

    // code same as queenComb_QueenChooses_Subseq()
    static int queenComb_BoxChooses(int qNo, int tnq, int tnb, int bidx, String ans) {
        if (bidx == tnb) { // since no for loop, so somewhere this check has to be there
            if (qNo == tnq) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        } else if (qNo == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        // options here are not implemented through for loop cuz only 2 options
        // idx denotes which box - always move to decision of next box in next recursive call
        // qNo denotes box's option of getting placed with queen or not - increment or not depending on option
        count += queenComb_BoxChooses(qNo + 1, tnq, tnb, bidx + 1,
            ans + "Q" + qNo + "->B" + bidx + " ");
        count += queenComb_BoxChooses(qNo, tnq, tnb, bidx + 1, ans);
        return count;
    }

    // choice of box is include/exclude so code is same for combination, subseq approach
    static int queenComb_BoxChooses_Subseq(int qNo, int tnq, int tnb, int bidx, String ans) {
        if (bidx == tnb) {
            if (qNo == tnq) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        } else if (qNo == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        count += queenComb_BoxChooses(qNo + 1, tnq, tnb, bidx + 1,
            ans + "Q" + qNo + "->B" + bidx + " ");
        count += queenComb_BoxChooses(qNo, tnq, tnb, bidx + 1, ans);
        return count;
    }

    // <--------------------------------------------------------Permutation below---------------------------------------------------------->

    // permutation explores all options at each level except the ones used; so bidx not needed
    static int queenPerm_QueenChooses(int qNo, int tnq, int tnb, boolean[] vis, String ans) {
        if (qNo == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < tnb; i++) {
            if (!vis[i]) {
                vis[i] = true;
                count += queenPerm_QueenChooses(qNo + 1, tnq, tnb, vis,
                    ans + "Q" + qNo + "->B" + i + " ");
                vis[i] = false;
            }
        }
        return count;
    }

    // combination or permutation - subseq approach always needs idx
    static int queenPerm_QueenChooses_Subseq(int qNo, int tnq, int tnb, int bidx, boolean[] vis, String ans) {
        if (bidx == tnb) {
            if (qNo == tnq) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        } else if (qNo == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        if (!vis[bidx]) {
            vis[bidx] = true;
            count += queenPerm_QueenChooses_Subseq(qNo + 1, tnq, tnb, 0, vis,
                ans + "Q" + qNo + "->B" + bidx + " ");
            vis[bidx] = false;
        }

        count += queenPerm_QueenChooses_Subseq(qNo, tnq, tnb, bidx + 1, vis, ans);
        return count;
    }

    // queenPerm_BoxChooses require a loop because each box must try all possible unused queens (if include) else exclude/skip
    // queenComb_BoxChooses() never need a loop because each box can only place the "next" queen (qNo) or skip
    static int queenPerm_BoxChooses(int qNo, int tnq, int tnb, int bidx, boolean[] vis, String ans) {
        if (bidx == tnb) {
            if (qNo == tnq) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;

        for (int queen = 0; queen < tnq; queen++) {
            if (!vis[queen]) {
                vis[queen] = true;
                count += queenPerm_BoxChooses(qNo + 1, tnq, tnb, bidx + 1, vis,
                    ans + "Q" + queen + "->B" + bidx + " ");
                vis[queen] = false;
            }
        }

        count += queenPerm_BoxChooses(qNo, tnq, tnb, bidx + 1, vis, ans);

        return count;
    }


    // choice of box is include/exclude so code is same for combination, subseq approach
    static int queenPerm_BoxChooses_Subseq(int qNo, int tnq, int tnb, int bidx, boolean[] vis, String ans) {
        if (bidx == tnb) {
            if (qNo == tnq) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;

        for (int queen = 0; queen < tnq; queen++) {
            if (!vis[queen]) {
                vis[queen] = true;
                count += queenPerm_BoxChooses(qNo + 1, tnq, tnb, bidx + 1, vis,
                    ans + "Q" + queen + "->B" + bidx + " ");
                vis[queen] = false;
            }
        }

        count += queenPerm_BoxChooses(qNo, tnq, tnb, bidx + 1, vis, ans);

        return count;
    }
}

// 1) Queen chooses -> Combination:
// Q0->B0 Q1->B1 Q2->B2 
// Q0->B0 Q1->B1 Q2->B3
// Q0->B0 Q1->B2 Q2->B3
// Q0->B1 Q1->B2 Q2->B3
// Count = 4

// 2) Queen chooses -> Subsequence:
// Q0->B0 Q1->B1 Q2->B2
// Q0->B0 Q1->B1 Q2->B3
// Q0->B0 Q1->B2 Q2->B3
// Q0->B1 Q1->B2 Q2->B3
// Count = 4

// 3) Box chooses -> Combination:
// Q0->B0 Q1->B1 Q2->B2
// Q0->B0 Q1->B1 Q2->B3
// Q0->B0 Q1->B2 Q2->B3
// Q0->B1 Q1->B2 Q2->B3
// Count = 4

// 4) Box chooses -> Subsequence:
// Q0->B0 Q1->B1 Q2->B2
// Q0->B0 Q1->B1 Q2->B3
// Q0->B0 Q1->B2 Q2->B3
// Q0->B1 Q1->B2 Q2->B3
// Count = 4

// 5) Queen chooses -> Permutation:
// Q0->B0 Q1->B1 Q2->B2
// Q0->B0 Q1->B1 Q2->B3
// Q0->B0 Q1->B2 Q2->B1
// Q0->B0 Q1->B2 Q2->B3
// Q0->B0 Q1->B3 Q2->B1
// Q0->B0 Q1->B3 Q2->B2
// Q0->B1 Q1->B0 Q2->B2
// Q0->B1 Q1->B0 Q2->B3
// Q0->B1 Q1->B2 Q2->B0
// Q0->B1 Q1->B2 Q2->B3
// Q0->B1 Q1->B3 Q2->B0
// Q0->B1 Q1->B3 Q2->B2
// Q0->B2 Q1->B0 Q2->B1
// Q0->B2 Q1->B0 Q2->B3
// Q0->B2 Q1->B1 Q2->B0
// Q0->B2 Q1->B1 Q2->B3
// Q0->B2 Q1->B3 Q2->B0
// Q0->B2 Q1->B3 Q2->B1
// Q0->B3 Q1->B0 Q2->B1
// Q0->B3 Q1->B0 Q2->B2
// Q0->B3 Q1->B1 Q2->B0
// Q0->B3 Q1->B1 Q2->B2
// Q0->B3 Q1->B2 Q2->B0
// Q0->B3 Q1->B2 Q2->B1
// Count = 24

// 6) Queen chooses -> Permutation (Subsequence):
// Q0->B0 Q1->B1 Q2->B2
// Q0->B0 Q1->B1 Q2->B3
// Q0->B0 Q1->B2 Q2->B1
// Q0->B0 Q1->B2 Q2->B3
// Q0->B0 Q1->B3 Q2->B1
// Q0->B0 Q1->B3 Q2->B2
// Q0->B1 Q1->B0 Q2->B2
// Q0->B1 Q1->B0 Q2->B3
// Q0->B1 Q1->B2 Q2->B0
// Q0->B1 Q1->B2 Q2->B3
// Q0->B1 Q1->B3 Q2->B0
// Q0->B1 Q1->B3 Q2->B2
// Q0->B2 Q1->B0 Q2->B1
// Q0->B2 Q1->B0 Q2->B3
// Q0->B2 Q1->B1 Q2->B0
// Q0->B2 Q1->B1 Q2->B3
// Q0->B2 Q1->B3 Q2->B0
// Q0->B2 Q1->B3 Q2->B1
// Q0->B3 Q1->B0 Q2->B1
// Q0->B3 Q1->B0 Q2->B2
// Q0->B3 Q1->B1 Q2->B0
// Q0->B3 Q1->B1 Q2->B2
// Q0->B3 Q1->B2 Q2->B0
// Q0->B3 Q1->B2 Q2->B1
// Count = 24

// 7) Box chooses -> Permutation:
// Q0->B0 Q1->B1 Q2->B2
// Q0->B0 Q1->B1 Q2->B3
// Q0->B0 Q2->B1 Q1->B2
// Q0->B0 Q2->B1 Q1->B3
// Q0->B0 Q1->B2 Q2->B3
// Q0->B0 Q2->B2 Q1->B3
// Q1->B0 Q0->B1 Q2->B2
// Q1->B0 Q0->B1 Q2->B3
// Q1->B0 Q2->B1 Q0->B2
// Q1->B0 Q2->B1 Q0->B3
// Q1->B0 Q0->B2 Q2->B3
// Q1->B0 Q2->B2 Q0->B3
// Q2->B0 Q0->B1 Q1->B2
// Q2->B0 Q0->B1 Q1->B3
// Q2->B0 Q1->B1 Q0->B2
// Q2->B0 Q1->B1 Q0->B3
// Q2->B0 Q0->B2 Q1->B3
// Q2->B0 Q1->B2 Q0->B3
// Q0->B1 Q1->B2 Q2->B3
// Q0->B1 Q2->B2 Q1->B3
// Q1->B1 Q0->B2 Q2->B3
// Q1->B1 Q2->B2 Q0->B3
// Q2->B1 Q0->B2 Q1->B3
// Q2->B1 Q1->B2 Q0->B3
// Count = 24

// 8) Box chooses -> Permutation (Subsequence):
// Q0->B0 Q1->B1 Q2->B2
// Q0->B0 Q1->B1 Q2->B3
// Q0->B0 Q2->B1 Q1->B2
// Q0->B0 Q2->B1 Q1->B3
// Q0->B0 Q1->B2 Q2->B3
// Q0->B0 Q2->B2 Q1->B3
// Q1->B0 Q0->B1 Q2->B2
// Q1->B0 Q0->B1 Q2->B3
// Q1->B0 Q2->B1 Q0->B2
// Q1->B0 Q2->B1 Q0->B3
// Q1->B0 Q0->B2 Q2->B3
// Q1->B0 Q2->B2 Q0->B3
// Q2->B0 Q0->B1 Q1->B2
// Q2->B0 Q0->B1 Q1->B3 
// Q2->B0 Q1->B1 Q0->B2
// Q2->B0 Q1->B1 Q0->B3
// Q2->B0 Q0->B2 Q1->B3
// Q2->B0 Q1->B2 Q0->B3
// Q0->B1 Q1->B2 Q2->B3
// Q0->B1 Q2->B2 Q1->B3
// Q1->B1 Q0->B2 Q2->B3
// Q1->B1 Q2->B2 Q0->B3
// Q2->B1 Q0->B2 Q1->B3
// Q2->B1 Q1->B2 Q0->B3
// Count = 24