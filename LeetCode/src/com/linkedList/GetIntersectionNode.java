package com.linkedList;

public class GetIntersectionNode {
//    public static void main(String[] args) {
//        ListNode headA = new ListNode(0);
//        ListNode headB = new ListNode(0);
//        int[] anums = new int[]{4, 1};
//        int[] bnums = new int[]{5, 0, 1};
//        ListNode acur = new ListNode(4);
//        headA.next = acur;
//        for (int i = 1; i < anums.length; i++) {
//            acur.next = new ListNode(anums[i]);
//            acur = acur.next;
//        }
//        ListNode bcur = new ListNode(5);
//        headB.next = bcur;
//        for (int i = 1; i < bnums.length; i++) {
//            bcur.next = new ListNode(bnums[i]);
//            bcur = bcur.next;
//        }
//        ListNode n8 = new ListNode(8);
//        ListNode n2 = new ListNode(2);
//        acur.next = n8;
//        bcur.next = n8;
//        GetIntersectionNode getIntersectionNode = new GetIntersectionNode();
////        ListNode res = getIntersectionNode.getIntersectionNode(headA.next, headB.next);\
//        ListNode res = getIntersectionNode.reverseListNode(headA.next);
//        System.out.println(res);
//    }
//
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB){
//        ListNode a = reverseListNode(headA);
//        ListNode b = reverseListNode(headB);
//        ListNode res = null;
//        while (a == b && a != null){
//            a = a.next;
//            b = b.next;
//            res = a;
//        }
//        return  res;
//    }
//
//    private ListNode reverseListNode(ListNode head){
//        ListNode cur = head.next;
//        ListNode last = head;
//        ListNode next;
//        while (cur.next != null){
//            next = cur.next;
//            cur.next = last;
//            last = cur;
//            cur = next;
//        }
//        cur.next = last;
//        return cur;
//    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
