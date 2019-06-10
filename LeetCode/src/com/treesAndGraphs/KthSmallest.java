package com.treesAndGraphs;

import java.util.ArrayList;
import java.util.List;

public class KthSmallest {
    private List<Integer> nums = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
        traver(root);
        return nums.get(k - 1);
    }

    private void traver(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            traver(root.left);
        }
        nums.add(root.val);
        if (root.right != null) {
            traver(root.right);
        }
    }
}
