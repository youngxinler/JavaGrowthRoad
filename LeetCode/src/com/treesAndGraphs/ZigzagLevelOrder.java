package com.treesAndGraphs;

import java.util.*;
import java.util.stream.Collectors;

public class ZigzagLevelOrder {
    //节点入栈方向变化
    public List<List<Integer>> zigzagLevelOrder_1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        boolean flag = true;
        while (!stack.empty()) {
            List<TreeNode> cur = new ArrayList<>();
            List<Integer> item = new ArrayList<>();
            int size = stack.size();
            //for(int i = 0; i < stack.size(); i++) 这样错了, 在这里卡了半天, stack一直pop, size不是我们想要的值
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = stack.pop();
                cur.add(treeNode);
                item.add(treeNode.val);
            }

            if (flag) {
                for (int i = 0; i < cur.size(); i++) {
                    TreeNode t = cur.get(i);
                    if (t.left != null) stack.push(t.left);
                    if (t.right != null) stack.push(t.right);
                }
            } else {
                for (int i = 0; i < cur.size(); i++) {
                    TreeNode t = cur.get(i);
                    if (t.right != null) stack.push(t.right);
                    if (t.left != null) stack.push(t.left);
                }
            }
            flag = !flag;
            res.add(item);
        }
        return res;
    }


    //数值入数组的方向变化
    public List<List<Integer>> zigzagLevelOrder_2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        boolean flag = true;
        while (!deque.isEmpty()) {
            List<Integer> item = new LinkedList<>();
            List<TreeNode> cur = new LinkedList<>();
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode t = deque.poll();
                cur.add(t);
                if (t.left != null) deque.offer(t.left);
                if (t.right != null) deque.offer(t.right);
            }
            if (flag) {
                for (int i = 0; i < cur.size(); i++) {
                    item.add(cur.get(i).val);
                }
            } else {
                for (int i = cur.size() - 1; i >= 0; i--) {
                    item.add(cur.get(i).val);
                }
            }
            flag = !flag;
            res.add(item);
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        ans.add(new ArrayList<>());
        bfs(ans, root, 0);
        for (int i = 0; i < ans.size(); i++) {
            if (i % 2 == 1){
                Collections.reverse(ans.get(i));
            }
        }
        return ans;
    }

    private void bfs(List<List<Integer>> ans, TreeNode root, int floor){
        if (root == null) return;
        if (floor + 1 <= ans.size()){
            ans.get(floor).add(root.val);
        }else {
            List<Integer> node = new ArrayList<>();
            node.add(root.val);
            ans.add(node);
        }
        bfs(ans, root.left, floor + 1);
        bfs(ans, root.right, floor + 1);
    }
}

