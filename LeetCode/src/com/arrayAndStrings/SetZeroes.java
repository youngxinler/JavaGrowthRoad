package com.arrayAndStrings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author youngxinler  2019/7/21
 **/
public class SetZeroes {
    public void setZeroes(int[][] matrix){
        Set<Integer> row = new HashSet<>();
        int lrow = matrix.length;
        int lcol = matrix[0].length;
        for (int i = 0; i < lcol; i++) {
            for (int j = 0; j < lrow; j++) {
                if (matrix[j][i] == 0){
                    int psj = j;
                    while (psj >= 0){
                        matrix[psj--][i] = 0;
                    }
                    row.add(j);
                    while (j + 1 < lrow){
                        j = j + 1;
                        if (matrix[j][i] == 0){
                            row.add(j);
                        }else {
                            matrix[j][i] = 0;
                        }
                    }
                }
            }
        }
        for (int num :
                new ArrayList<Integer>(row)) {
            matrix[num] = new int[lcol];
        }
    }
}
