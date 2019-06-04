package com.treesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        int lastNum = 1;
        int index = 1;
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            int now = 0;
            for (int i = 0; i < lastNum; i++) {
                TreeNode cur = linkedList.pollFirst();
                if (index % 2 == 0) {
                    if (cur.left != null) {
                        linkedList.add(cur.left);
                        now++;
                    }
                    if (cur.right != null) {
                        linkedList.add(cur.right);
                        now++;
                    }
                } else {
                    if (cur.right != null) {
                        linkedList.add(cur.right);
                        now++;
                    }
                    if (cur.left != null) {
                        linkedList.add(cur.left);
                        now++;
                    }
                }
                item.add(cur.val);
            }
            index++;
            lastNum = now;
            res.add(item);
        }
        return res;
    }
}
