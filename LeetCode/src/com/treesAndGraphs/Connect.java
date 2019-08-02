package com.treesAndGraphs;

/**
 * @author youngxinler  19-6-7 下午5:11
 * @version 0.1
 **/

public class Connect {
    //有点像bfs
    public Node connect(Node root){
        if (root == null) return null;
        Node cur = root;
        while (cur != null){
            if(cur.left != null){
                cur.left.next = cur.right;
            }
            if (cur.next != null && cur.right != null){
                cur.right.next = cur.next.left;
            }
            cur = cur.next;
        }
        connect(root.left);
        return root;
    }
}
