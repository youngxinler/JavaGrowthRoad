package com.treesAndGraphs;

public class KthSmallest {
    private int ans;
    private int target;
    private int n;

    public int kthSmallest(TreeNode root, int k){
        this.target = k;
        n = 0;
        visit(root);
        return ans;
    }

    private void visit(TreeNode root){
        if (root == null)return;
        visit(root.left);
        n++;
        if (n == target){
            ans = root.val;
            return;
        }
        visit(root.right);
    }

}
