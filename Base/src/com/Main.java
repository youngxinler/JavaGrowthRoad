// 本题为考试多行输入输出规范示例，无需提交，不计分。
package com;
// 本题为考试多行输入输出规范示例，无需提交，不计分。


import java.util.*;


public class Main {
    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("johnsmith@mail.com");
        queue.add("john_newyork@mail.com");
        queue.add("john00@mail.com");
        List<String> res = new ArrayList<>();
        res.addAll(queue);
        for (String s : res) {
            System.out.println(s);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

}