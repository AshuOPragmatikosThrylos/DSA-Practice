import java.util.*;

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
        this.node = node;
        this.state = state;
    }
}

class Solution {



    // public void iterativeTraversal(Node node) {
    //     String pre = "", in = "", post = "";
    //     Stack<Pair> st = new Stack<>();
    //     st.push(new Pair(node, 1));

    //     while(st.size() != 0) {
    //         Pair top = st.peek();
    //         if(top.state == 1) {
    //             pre += top.node.val + " ";
    //             top.state++;
    //             if(top.node.left != null)
    //                 st.push(new Pair(top.node.left, 1));
    //         } else if (top.state == 2) {
    //             in += top.node.val + " ";
    //             top.state ++;
    //             if(top.node.right != null)
    //                 st.push(new Pair(top.node.right, 1));
    //         } else if(top.state == 3) {
    //             post += top.node.val + " ";
    //             st.pop();
    //         }
    //     }

    //     System.out.print("Pre: ");
    //     System.out.println(pre);
    //     System.out.print("In: ");
    //     System.out.println(in);
    //     System.out.print("Post: ");
    //     System.out.println(post);
    // }

    public void iterativeTraversal(Node node) {
        String pre = "", in = "", post = "";
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node, 1));

        while(st.size() != 0) {
            Pair top = st.peek(); 
            if(top.state == 1) {
                pre += top.node.val + " ";
                top.state++;
                // if(top.node.left != null)
                //     st.push(new Pair(top.node.left, 1));
            } else if (top.state == 2) {
                if(top.node.left != null)
                    st.push(new Pair(top.node.left, 1));
                in += top.node.val + " ";
                top.state ++;
                // if(top.node.right != null)
                //     st.push(new Pair(top.node.right, 1));
            } else if(top.state == 3) {
                if(top.node.right != null)
                    st.push(new Pair(top.node.right, 1));
                post += top.node.val + " ";
                st.pop();
            }
        }

        System.out.print("Pre: ");
        System.out.println(pre);
        System.out.print("In: ");
        System.out.println(in);
        System.out.print("Post: ");
        System.out.println(post);
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
        sol.iterativeTraversal(root);
    }
}
