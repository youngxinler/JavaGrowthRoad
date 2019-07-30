package com.treesAndGraphs;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {


    int preIndex = 0;
    int[] preOrder;
    int[] inOrder;

    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preOrder, int[] inOrder){
        this.preOrder = preOrder;
        this.inOrder = inOrder;
        int index = 0;
        for (int n :
                inOrder) {
            map.put(n, index++);
        }
        return fun(0, preOrder.length);
    }

    private TreeNode fun(int left, int right){
        if (right == left) return null;
        int val = preOrder[preIndex];
        TreeNode root = new TreeNode(val);
        int in = map.get(val);

        preIndex++;
        root.left = fun(left, in);
        root.right = fun(in + 1, right);
        return root;
    }
}


