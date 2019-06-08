package com.treesAndGraphs;

/**
 * @author youngxinler  19-6-7 下午5:11
 * @version 0.1
 **/

public class Connect {
    public Node connect(Node root) {
        if (root == null) return null;
        traver(root);
        return root;
    }

    private void traver(Node root) {
        if (root.left != null) {
            root.left.next = root.right;
        } else return;
        traver(root.left);
        traver(root.right);
    }
}
