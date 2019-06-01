import java.util.*;

public class Main {



    public List<List<String>> groupAnagrams(String[] strs) {
        final Map<String, List<String>> map = new HashMap<>();
        final List<List<String>> res = new ArrayList<>();
        for (String str :
                strs) {
            String sortedString = sort(str);
            if (map.containsKey(sortedString)) {
                map.get(sortedString).add(str);
            } else {
                List<String> rows = new ArrayList<>();
                rows.add(str);
                map.put(sortedString, rows);
            }
        }
        for (Map.Entry<String, List<String>> entry :
                map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    private String sort(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }



    public static void main(String[] args) {
        Main main = new Main();
        String fuck = "bb";
        System.out.println(main.longestPalindrome(fuck));
    }


    private int palindromeLen(String s){
        boolean flag = false;
        int l = 0, r = s.length() - 1;
        while (l < r){
            if (s.charAt(l) != s.charAt(r)) break;
            else {l++; r--;}
        }
        if (l == r) return s.length();
        else return -1;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int lenJ = expandAroundCenter(s, i, i);
            int lenO = expandAroundCenter(s, i, i + 1);
            int len = Math.max(lenJ, lenO);
            if (len > start - end){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)){
            L--;
            R++;
        }
        return R - L - 1;
    }
}
