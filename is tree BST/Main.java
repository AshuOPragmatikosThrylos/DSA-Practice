class Node {
    int val;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class BSTPair {
    boolean isBST;
    int min; 
    int max;
}

class Solution {

    // ✅ Function to check if tree is a BST
    // public boolean isBST(Node root) {
    //     return isBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);  
    // }

    // private boolean isBSTUtil(Node node, long min, long max) {
    //     if (node == null) return true;

    //     if (node.val <= min || node.val >= max) return false;

    //     return isBSTUtil(node.left, min, node.val) &&
    //            isBSTUtil(node.right, node.val, max);
    // }



    BSTPair isBST (Node node) {
        if(node == null) {
            BSTPair bp = new BSTPair();
            bp.min = Integer.MAX_VALUE;
            bp.max = Integer.MIN_VALUE;

            bp.isBST = true;
            return bp;
        }

        BSTPair lp = isBST(node.left);
        BSTPair rp = isBST(node.right);

        BSTPair mp = new BSTPair();
        mp.isBST = lp.isBST && rp.isBST && (node.val >= lp.max && node.val <=rp.min);


        if(!mp.isBST)
           return mp; // cuz if not BST then no use of finding min, max

        // mp.min = Math.min(node.val, Math.min(lp.min, rp.min));
        mp.min = Math.min(node.val, lp.min);
        // mp.max = Math.max(node.val, Math.max(lp.max, rp.max));
        mp.max = Math.max(node.val, rp.max);

        return mp;

    }

    // Helper: inorder traversal
    public void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }
}

public class Main {
public static void main(String[] args) {
    Solution sol = new Solution();

    // ✅ Example 1: Perfect BST
    //        4
    //       / \
    //      2   6
    //     / \ / \
    //    1  3 5  7
    Node bst1 = new Node(4,
        new Node(2, new Node(1), new Node(3)),
        new Node(6, new Node(5), new Node(7))
    );
    System.out.print("Tree 1 (Inorder): ");
    sol.inorder(bst1);
    // System.out.println("\nIs BST? " + sol.isBST(bst1));
    System.out.println("\nIs BST? " + sol.isBST(bst1).isBST);
    System.out.println("------------------");

    // ✅ Example 2: Skewed BST (like a linked list increasing)
    //   1
    //    \
    //     2
    //      \
    //       3
    //        \
    //         4
    Node bst2 = new Node(1,
        null,
        new Node(2, null,
            new Node(3, null,
                new Node(4)
            )
        )
    );
    System.out.print("Tree 2 (Inorder): ");
    sol.inorder(bst2);
    // System.out.println("\nIs BST? " + sol.isBST(bst2));
    System.out.println("\nIs BST? " + sol.isBST(bst2).isBST);
    System.out.println("------------------");

    // ✅ Example 3: Larger BST
    //            15
    //          /    \
    //         10     20
    //        / \    /  \
    //       8  12  17  25
    //      /      /      \
    //     6      16      30
    Node bst3 = new Node(15,
        new Node(10,
            new Node(8, new Node(6), null),
            new Node(12)
        ),
        new Node(20,
            new Node(17, new Node(16), null),
            new Node(25, null, new Node(30))
        )
    );
    System.out.print("Tree 3 (Inorder): ");
    sol.inorder(bst3);
    // System.out.println("\nIs BST? " + sol.isBST(bst3));
    System.out.println("\nIs BST? " + sol.isBST(bst3).isBST);
    System.out.println("------------------");

    //  Example 4: Invalid BST
    //        10
    //       /  \
    //      5   15
    //         /  \
    //        6    20   <-- 6 is invalid here
    Node nonBST = new Node(10,
        new Node(5),
        new Node(15, new Node(6), new Node(20))
    );
    System.out.print("Tree 4 (Inorder): ");
    sol.inorder(nonBST);
    // System.out.println("\nIs BST? " + sol.isBST(nonBST));
    System.out.println("\nIs BST? " + sol.isBST(nonBST).isBST);
    System.out.println("------------------");
}
}
