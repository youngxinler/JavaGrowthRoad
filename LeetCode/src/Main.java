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


}
