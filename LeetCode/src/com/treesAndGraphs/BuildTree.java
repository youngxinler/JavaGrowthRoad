package com.treesAndGraphs;

import java.util.HashMap;

public class BuildTree {
    int preIndex = 0;
    int[] preOrder;
    int[] inOrder;

    HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        this.preOrder = preOrder;
        this.inOrder = inOrder;
        int index = 0;
        for (int n :
                inOrder) {
            hashMap.put(n, index++);
        }
        return helper(0, inOrder.length);
    }

    private TreeNode helper(int left, int right) {
        //root has no left tree or right tree
        if (left == right) return null;
        int rootVal = preOrder[preIndex];
        TreeNode root = new TreeNode(rootVal);
        //find root's  index in inOrder
        int in = hashMap.get(rootVal);

        //root right move
        preIndex++;
        root.left = helper(left, in);
        root.right = helper(in + 1, right);
        return root;
    }
}

