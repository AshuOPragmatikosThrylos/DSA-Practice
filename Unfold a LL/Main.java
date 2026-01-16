public class Main {

        static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    private static ListNode buildList(int... values) {
        if (values == null || values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new ListNode(values[i]);
            curr = curr.next;
        }
        return head;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static ListNode unfoldLL(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode c1 = head, c2 = head.next, head1 = head, head2 = c2;

        while(c1.next != null && c2.next != null) {
            c1.next = c2.next;
            c2.next = c2.next.next;
            c1 = c1.next;
            c2 = c2.next;
        }
        head2 = reverseList(head2);
        c1.next = head2;

        return head1;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head1 = buildList(1, 5, 2, 4, 3);
        ListNode head2 = buildList(1, 6, 2, 5, 3, 4);

        System.out.println("Original List 1:");
        printList(head1);
        head1 = unfoldLL(head1);
        System.out.println("Unfolded List 1:");
        printList(head1);

        System.out.println("\nOriginal List 2:");
        printList(head2);
        head2 = unfoldLL(head2);
        System.out.println("Unfolded List 2:");
        printList(head2);
    }
}
