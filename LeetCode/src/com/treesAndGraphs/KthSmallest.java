package com.treesAndGraphs;

public class KthSmallest {

    //dfs 中序遍历, 返回第k个元素
    private int res = 0;
    private int target = 1;
    private int n = 0;

    private int kthSmallest(TreeNode root, int k) {
        target = k;
        visit(root);
        return res;
    }

    private void visit(TreeNode root) {
        if (root == null) return;
        visit(root.left);
        n++;
        if (n == target) {
            res = root.val;
            return;
        }
        visit(root.right);
    }
}
