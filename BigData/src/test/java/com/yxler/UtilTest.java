package com.yxler;

import java.util.HashMap;
import java.util.Map;

public class UtilTest {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode res = solution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        assert res != null;
    }

    static class Solution {
        private Map<Integer, Integer> inorderMap;
        private int[] preorder;
        private int[] inorder;
        private int preIndex;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            this.inorder = inorder;
            this.inorderMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inorderMap.put(inorder[i], i);
            }
            this.preIndex = 0;
            return core(0, inorder.length - 1);
        }

        private TreeNode core(int inStart, int inEnd) {
            if (inStart == inEnd) {
                return new TreeNode(inorder[inStart]);
            }

            if (inStart < inEnd) {
                int rootIndex = inorderMap.get(preorder[preIndex]);
                TreeNode root = new TreeNode(inorder[rootIndex]);
                if (rootIndex > inStart) {
                    preIndex++;
                    root.left = core(inStart, rootIndex - 1);
                }
                if (rootIndex < inEnd) {
                    preIndex++;
                    root.right = core(rootIndex + 1, inEnd);
                }
                return root;
            }
            return null;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
