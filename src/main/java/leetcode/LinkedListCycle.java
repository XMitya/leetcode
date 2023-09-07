package leetcode;

public class LinkedListCycle {

    public static void main(String[] args) {
        final ListNode noloop = withoutLoop();
        final ListNode loop = withLoop();

        System.out.println(hasCycle(noloop));
        System.out.println(hasCycle(loop));
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        if (head.next == null) {
            return false;
        }

        ListNode slowPtr = head;
        ListNode fastPtr = head;

        while (true) {
            if (slowPtr.next == null) {
                return false;
            }

            if (fastPtr.next == null || fastPtr.next.next == null) {
                return false;
            }

            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;

            if (slowPtr == fastPtr) {
                return true;
            }
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    private static ListNode withoutLoop() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        return node1;
    }

    private static ListNode withLoop() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;

        return node1;
    }
}
