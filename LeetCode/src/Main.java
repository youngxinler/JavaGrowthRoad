public class Main {






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
