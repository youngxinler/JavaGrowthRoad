import com.treesAndGraphs.TreeNode;

import java.util.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Main {

    public class Solution {
        public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
            return selectionSort(input, k);
        }

        private ArrayList<Integer> selectionSort(int[] array, int k){
            if(array == null || array.length < k)return null;
            Integer[] ans = new Integer[k];
            for(int i = 0; i < k; i++){
                int min = i;
                for(int j = i + 1; j < array.length; j++){
                    if(array[j] < array[min]) min = j;
                }
                if(min != i){
                    int tmp = array[min];
                    array[min] = array[i];
                    array[i] = tmp;
                }
            }
            for(int i = 0; i < k; i++){
                ans[i] = array[i];
            }
//            ArrayList<Integer> res = new ArrayList<>(ans.length);
//            Collections.addAll(res, ans);
            AbstractQueuedSynchronizer
            return new ArrayList<Integer>(Arrays.asList(ans));

        }
    }


}

