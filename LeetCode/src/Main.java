import java.util.*;


public class Main {
    public boolean wordBreak(String s, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (!visited[start]) {
                for (int i = start + 1; i <= s.length(); i++) {
                    if (set.contains(s.substring(start, i))) {
                        if (i == s.length()) {
                            return true;
                        }
                        queue.add(i);
                    }
                }
                visited[start] = true;
            }
        }
        return false;
    }


    private boolean wordBreakCore(String s, int start, Set<String> set, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (set.contains(s.substring(start, end)) && wordBreakCore(s, end, set, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }

    public static void main(String[] args) {
        System.out.println("hello".substring(5).length());
    }
}

class LRUCache extends LinkedHashMap<Integer, Integer> {

    private int maxSize;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.maxSize = capacity;
    }

    public int get(int key) {
        return getOrDefault(key, -1);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > maxSize;
    }
}