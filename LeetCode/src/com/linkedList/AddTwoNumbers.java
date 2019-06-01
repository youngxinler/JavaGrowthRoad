package com.linkedList;

/**
 * @author youngxinler  19-6-1 下午7:11
 * @version 0.1
 **/

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        while (l1 != null || l2 != null) {
            int x = 0, y = 0;
            if (l1 != null) {
                x = l1.val;
            }
            if (l2 != null) {
                y = l2.val;
            }
            res.next = new ListNode(x + y);
            res = res.next;
        }
        return res.next;
    }
}
