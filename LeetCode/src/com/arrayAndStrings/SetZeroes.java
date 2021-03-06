package com.arrayAndStrings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author youngxinler  2019/7/21
 **/
public class SetZeroes {
    public void setZeroes_1(int[][] matrix){
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

    public void setZeroes(int[][] matrix){
        boolean  firstRow = false;
        int rlen = matrix.length;
        int clen = matrix[0].length;

        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                if (matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        if (matrix[0][0] == 0) firstRow = true;
        for (int i = 1; i < rlen; i++) {
            if (matrix[i][0] == 0) {
                matrix[i] =  new int[clen];
            }
        }
        for (int i = 0; i < clen; i++) {
            if (matrix[0][i] == 0){
                for (int j = 0; j < rlen; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        if (firstRow) matrix[0] = new int[clen];
    }
}
