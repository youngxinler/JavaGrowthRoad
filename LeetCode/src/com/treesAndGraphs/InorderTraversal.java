package com.treesAndGraphs;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traver(res, root);
        return res;
    }

    private void traver(final List<Integer> list, TreeNode root) {
        if (root == null) return;
        if (root.left != null) traver(list, root.left);
        list.add(root.val);
        if (root.right != null) traver(list, root.right);
    }
}
