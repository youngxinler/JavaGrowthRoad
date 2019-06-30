package com.design;

import com.treesAndGraphs.TreeNode;


/**
 * @author youngxinler  19-6-29 下午2:55
 **/

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        preOrder(root, builder);
        return builder.substring(0, builder.length() - 1);
    }

    private void preOrder(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append("null,");
            return;
        }
        builder.append(root.val + ",");
        preOrder(root.left, builder);
        preOrder(root.right, builder);
    }


    public TreeNode deserialize(String data) {

        StringBuilder builder = new StringBuilder(data);
        return deserial(builder);

    }

    private TreeNode deserial(StringBuilder data) {
        int index = data.indexOf(",");//index存储第一个逗号出现的索引值
        String current = "";//current存储当前要够构造的节点的val值
        if (index == -1) {//index为-1，说明不存在逗号，针对此对data作切割
            index = data.length();
            current = data.toString();
            data = data.delete(0, index);
        } else {//index为-1，说明存在逗号，针对此对data作切割
            current = data.substring(0, index);
            data = data.delete(0, index + 1);
        }

        if (current.length() > 0 && !current.equals("null")) {//存在值，可以构建节点
            TreeNode root = new TreeNode(Integer.parseInt(current));
            root.left = deserial(data);
            root.right = deserial(data);
            return root;
        } else {//不存在值，返回null
            return null;
        }

    }
}
