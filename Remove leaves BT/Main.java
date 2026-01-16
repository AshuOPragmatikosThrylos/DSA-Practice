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

class Solution {

    // ✅ Function to remove leaf nodes
    public Node removeLeaves(Node root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) {
            return null; // leaf found → remove
        }
        root.left = removeLeaves(root.left);
        root.right = removeLeaves(root.right);

        //wrong
        //  if (root.left == null && root.right == null) {
        //     return null; // leaf found → remove
        // }

        return root;
    }

    // Helper: inorder traversal to check result
    public void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }
}

public class Main {
    public static void main(String[] args) {
        // Example binary tree:
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5

        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node2 = new Node(2, node4, node5);
        Node node3 = new Node(3);
        Node root = new Node(1, node2, node3);

        Solution sol = new Solution();

        System.out.print("Original Tree (Inorder): ");
        sol.inorder(root);
        System.out.println();

        root = sol.removeLeaves(root);

        System.out.print("After Removing Leaves (Inorder): ");
        sol.inorder(root);
        System.out.println();
    }
}
