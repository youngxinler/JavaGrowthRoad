import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        String res = main.minNumber(new int[]{121, 12});
        System.out.println(res);
    }

    public String minNumber(int[] nums) {
        List<String> numStrs = new ArrayList<>();
        for (int num : nums) {
            numStrs.add(num + "");
        }
        Collections.sort(numStrs, (a, b) -> (a + b).compareTo(b + a));
        StringBuilder sb = new StringBuilder();
        for (String str : numStrs) {
            sb.append(str);
        }
        return sb.toString();
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        String left = serialize(root.left);
        int rootVal = root.val;
        String right = serialize(root.right);
        return "(" + left + ")" + root + "(" + right + ")";
    }
    private int index;
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        this.index = 0;
        return deserCore(data);
    }

    private TreeNode deserCore(String data) {
        if (index >= data.length() || data.charAt(index) == ')') {
            return null;
        }
        index++;
        TreeNode left = deserCore(data);
        index++;
        int rootVal = parseInt(data);
        index++;
        TreeNode right = deserCore(data);
        index++;
        TreeNode curRoot = new TreeNode(rootVal);
        curRoot.left = left;
        curRoot.right = right;
        return curRoot;
    }



    private int parseInt(String data) {
        int res = 0;
        while (index < data.length() && Character.isDigit(data.charAt(index))) {
            int cur = data.charAt(index) - '0';
            res = res * 10 + cur;
            index++;
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));