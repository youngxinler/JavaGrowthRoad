package com.linkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youngxinler  19-6-2 下午7:37
 * @version 0.1
 **/

public class OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode o = head;
        ListNode p = head.next;
        ListNode e = p;
        while (o.next != null && e.next != null){
            o.next = e.next;
            o = o.next;
            e.next = o.next;
            e = e.next;
        }
        o.next = p;
        return head;
    }


}
