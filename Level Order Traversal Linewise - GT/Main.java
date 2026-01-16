import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> lot = new ArrayList<>();

        if (root == null) return lot;

        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);

        while(queue.size() != 0) {
            List<Integer> oneLevel = new ArrayList<>();
            int levelSize = queue.size();
            for(int i =0; i<levelSize; i++) { 
            // for(int i =0; i<queue.size(); i++) { //wrong: this would modify queue size during iteration
                Node temp = queue.removeFirst();
                oneLevel.add(temp.val);
                for(Node child: temp.children)
                    queue.addLast(child);
            }
            lot.add(oneLevel); // works
            // lot.add(new ArrayList<>(oneLevel)); //works too, but unnecessarily creates a new list each time
        }
        return lot;
    }
}

public class Main {
    public static void main(String[] args) {
        // Example tree:
        //         1
        //       / | \
        //      3  2  4
        //     / \
        //    5   6

        Node node5 = new Node(5, new ArrayList<>());
        Node node6 = new Node(6, new ArrayList<>());
        List<Node> childrenOf3 = Arrays.asList(node5, node6);

        Node node3 = new Node(3, childrenOf3);
        Node node2 = new Node(2, new ArrayList<>());
        Node node4 = new Node(4, new ArrayList<>());

        Node root = new Node(1, Arrays.asList(node3, node2, node4));

        Solution sol = new Solution();
        List<List<Integer>> result = sol.levelOrder(root);

        System.out.println(result);
    }
}
