package algorithm;

import java.math.BigInteger;

// https://leetcode.com/problems/add-two-numbers/editorial/
public class TwoNumSumLinkedList {

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
        TwoNumSumLinkedList t = new TwoNumSumLinkedList();

        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        for (ListNode res = t.addTwoNumbers(l1, l2); res != null; res = res.next) {
            System.out.print(res.val);
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        final BigInteger bi1 = toBigInteger(l1);
        final BigInteger bi2 = toBigInteger(l2);

        final BigInteger biRes = bi1.add(bi2);

        return toLinkedList(biRes);
    }

    private ListNode toLinkedList(BigInteger bi) {
        final StringBuilder sb = new StringBuilder(bi.toString());

        ListNode res = null;
        for (int i = 0; i < sb.length(); i++) {
            res = new ListNode(Integer.parseInt("" + sb.charAt(i)), res);
        }

        return res;
    }

    private BigInteger toBigInteger(ListNode l) {
        final StringBuilder sb = new StringBuilder();

        for (; l != null; l = l.next) {
            sb.append(l.val);
        }

        sb.reverse();

        return new BigInteger(sb.toString());
    }
}
