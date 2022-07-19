//
//
//
//import java.util.*;
//
//
//
//public class Main {
//    static char[][] map;
//    static int ans;
//    static boolean[][] vis;
//    static boolean ok;
//    static int[][] dp;
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()){
//            int T = sc.nextInt();
//            while (T-- != 0){
//                int n = sc.nextInt();
//                int m = sc.nextInt();
//                sc.nextLine();
//                map = new char[n][m];
//                vis = new boolean[n][m];
//                dp = new int[n][m];
//                ans = Integer.MAX_VALUE;
//                ok = false;
//                int si = -1;
//                int sj = -1;
//                for (int i = 0; i < n; i++) {
//                    char[] cs = sc.nextLine().toCharArray();
//                    Arrays.fill(dp[i], Integer.MAX_VALUE);
//                    for (int j = 0; j < m; j++) {
//                        if ((map[i][j] = cs[j]) == '@'){
//                            si = i;
//                            sj = j;
//                        }
//                    }
//                }
//                if (si == 0 || sj == 0 || si == map.length - 1 || sj == map[0].length - 1){
//                    System.out.println(0);
//                }else {
//                    dfs(si, sj, 0);
//                    System.out.println(ok ? ans : -1);
//                }
//            }
//        }
//    }
//
//
//    private static void dfs(int i, int j, int cur){
//        if (i < 0 || j < 0 || i > map.length || j > map[0].length){
//            return;
//        }
//        if(map[i][j] == '#' || vis[i][j]){
//            return;
//        }
//        if (ok && ans <= cur){
//            return;
//        }
//        if (cur )
//        int rl = map.length;
//        int cl = map[0].length;
//        if(i == 0 || j == 0 || i == rl - 1 || j == cl - 1){
//            if (map[i][j] == '.') {
//                ok = true;
//                ans = Math.min(ans, cur);
//            }if (map[i][j] == '*'){
//                ok = true;
//                ans = Math.min(ans, cur + 1);
//            }
//            return;
//        }
//        vis[i][j] = true;
//        if (map[i][j] == '*'){
//            cur = cur + 1;
//        }
//        dp[i][j] = Math.min(cur, dp[i][j]);
//        dfs(i + 1, j, cur);
//        dfs(i, j + 1, cur);
//        dfs(i - 1, j, cur);
//        dfs(i, j - 1, cur);
//        vis[i][j] = false;
//    }
//}
