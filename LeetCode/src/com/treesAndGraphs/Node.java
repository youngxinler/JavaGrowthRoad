package com.treesAndGraphs;

/**
 * @author youngxinler  19-6-7 下午5:08
 * @version 0.1
 **/

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int val, Node left, Node right, Node next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}
