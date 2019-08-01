package com.others;

/**
 * @author youngxinler  2019/8/1
 **/
public class FindRedundantConnection {
    int[] p;
    public int[] findRedundantConnection(int[][] edges){
        p =  new int[edges.length + 1];
        int[] res = new int[2];
        for (int i = 1; i <= edges.length; i++) {
            p[i] = i;
        }
        for (int[] edge :
                edges) {
            int a = find(edge[0]);
            int b = find(edge[1]);
            if (a == b) res = edge;
            else p[a] = b;
        }
        return res;
    }

    private int find(int x){
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
