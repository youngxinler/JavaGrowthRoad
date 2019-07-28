package com.treesAndGraphs;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal {
    //递归
    public List<Integer> inorderTraversal_1(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        fun(list, root);
        return list;
    }

    private void fun(List<Integer> list, TreeNode t){
        if (t == null) return;
        if (t.left != null) fun(list, t.left);
        list.add(t.val);
        if (t.right != null) fun(list, t.right);
    }

    //迭代
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || stack.empty()){
            while (root != null){
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }
}
