package com.singleObject;

import java.util.*;

/**
 * @author youngxinler
 * @description TODO
 * @date 2020-09-08  19:56
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String line = sc.nextLine();
            if (line.length() == 0){
                System.out.println("");
            }
            String[] strList = line.split(" ");
            List<String> ans = new ArrayList<>();
            char[] cs = new char[strList.length];
            dfs(strList, 0, cs, ans);
            Collections.sort(ans);
            for (String str : ans){
                System.out.println(str);
            }
        }
    }

    public static void dfs(String[] strList, int index, char[] cs, List<String> ans){
        if (index == strList.length){
            ans.add(judge(cs));
        }else {
            for (char c : strList[index].toCharArray()){
                cs[index] = c;
                dfs(strList, index + 1, cs, ans);
            }
        }
    }

    private static String judge(char[] cs){
        boolean[] visited = new boolean[50];
        for (char c : cs){
            int i = c - 'a';
            if (visited[i]){
                return new String(cs) + "--circular dependency";
            }else {
                visited[i] = true;
            }
        }
        return new String(cs);
    }
}
