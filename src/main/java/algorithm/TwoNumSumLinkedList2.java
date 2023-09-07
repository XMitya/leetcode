package algorithm;

import java.math.BigInteger;

public class TwoNumSumLinkedList2 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        TwoNumSumLinkedList2 t = new TwoNumSumLinkedList2();

        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        test(l1, l2);

        test(new ListNode(9), new ListNode(9));
        test(new ListNode(9, new ListNode(8)), new ListNode(9));
    }

    private static void test(ListNode l1, ListNode l2) {
        TwoNumSumLinkedList2 t = new TwoNumSumLinkedList2();
        for (ListNode res = t.addTwoNumbers(l1, l2); res != null; res = res.next) {
            System.out.print(res.val);
        }
        System.out.println();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;

        ListNode resNode = null;
        boolean carry = false;
        while (l1 != null || l2 != null) {
            int v1 = getValue(l1);
            int v2 = getValue(l2);

            int sum = v1 + v2;
            if (carry) {
                sum += 1;
                carry = false;
            }
            if (sum > 9) {
                carry = true;
                sum -= 10;
            }

            resNode = append(resNode, sum);

            l1 = next(l1);
            l2 = next(l2);
        }

        if (carry) {
            append(resNode, 1);
        }

        return resNode;
    }

    private ListNode append(ListNode l, int val) {
        if (l == null) 
            return new ListNode(val);
        
        for (ListNode l1 = l; l1 != null; l1 = l1.next) {
            if (l1.next == null) {
                l1.next = new ListNode(val);
                break;
            }
        }

        return l;
    }
    
    private int getValue(ListNode l) {
        return l == null ? 0 : l.val;
    }

    private ListNode next(ListNode l) {
        return l == null ? null : l.next;
    }
}
