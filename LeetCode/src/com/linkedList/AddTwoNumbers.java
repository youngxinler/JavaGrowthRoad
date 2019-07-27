package com.linkedList;

import javax.swing.*;

/**
 * @author youngxinler  19-6-1 下午7:11
 * @version 0.1
 **/

public class AddTwoNumbers {
    public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {

        //链表大部分问题都需要记录head
        ListNode res = new ListNode(0);
        ListNode head = new ListNode(0);
        head.next = res;
        //last 记录进位
        int last = 0;
        while (l1 != null || l2 != null) {
            int x = 0, y = 0;
            if (l1 != null) {
                x = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                y = l2.val;
                l2 = l2.next;
            }
            int sum = x + y + last;
            //判断是否大于10, 有进位为1, 没进位为0,
            if (sum > 9) {
                last = 1;
                sum = sum - 10;
            } else {
                last = 0;
            }
            res.next = new ListNode(sum);
            res = res.next;
        }
        //[9] [9] 这种情况如果最后一位有进位, 最后多添加一个node, 记录进位
        if (last != 0) {
            res.next = new ListNode(last);
        }
        return head.next.next;
    }



















    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode head = new ListNode(-1);
        ListNode res = new ListNode(-1);
        res.next = head;
        int last = 0;
        while (l1 != null || l2 != null){
            int a = 0, b = 0;
            if (l1 != null){
                a = l1.val;
                l1 = l1.next;
            }
            if (l2 != null){
                b = l2.val;
                l2 = l2.next;
            }
            if (a + b + last > 9){
                head.next = new ListNode(a + b - last);
                last = 1;
            }else {
                head.next = new ListNode(a + b + last);
                last = 0;
            }
            head = head.next;
        }
        if (last != 0) head.next = new ListNode(last);
        return res.next.next;
    }
}
