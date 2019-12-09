package com.others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Banker {

    private int threadNum;
    private int resourcesNum;
    //资源, 线程
    private ArrayList<ArrayList<Pair>> resources;
    private ArrayList<Integer> remain;


    private void init() {
        System.out.print("请分别输入线程数目和资源类别数目:");
        Scanner sc = new Scanner(System.in);
        this.threadNum = sc.nextInt();
        this.resourcesNum = sc.nextInt();
        resources = new ArrayList<>();
        for (int i = 1; i <= resourcesNum; i++) {
            ArrayList<Pair> item = new ArrayList<>();
            System.out.printf("请输入第%d种资源的状态\n", i);
            for (int j = 1; j <= threadNum; j++) {
                System.out.printf("第%d个线程的最大需要资源数和已经拥有的资源数:", j);
                int max = sc.nextInt();
                int had = sc.nextInt();
                Pair p = new Pair(max, had);
                item.add(p);
            }
            resources.add(item);
        }
        remain = new ArrayList<>();
        for (int i = 0; i < resourcesNum; i++) {
            System.out.printf("请输入第%d种资源还剩余的资源数:", i + 1);
            remain.add(sc.nextInt());
        }
    }

    private void disPlayInfo() {
        System.out.println("---------资源信息展示---------");
        for (int i = 1; i <= threadNum; i++) {
            System.out.printf("P%d:   ", i);
            for (int j = 1; j <= resourcesNum; j++) {
                Pair p = resources.get(j - 1).get(i - 1);
                System.out.printf("%d@max %d %d@had %d", j, p.max, j, p.had);
            }
            System.out.println();
        }
        for (int j = 0; j < resourcesNum; j++) {
            System.out.printf("第%d种资源剩余数量: %d\n", j + 1, remain.get(j));
        }
    }

    private void judge() {
        for (int i = 0; i < resourcesNum; i++) {
            Collections.sort(resources.get(i), new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return (o1.max - o1.had) - (o2.max - o2.had);
                }
            });
            int re = remain.get(i);
            System.out.printf("第%d种资源线程最优分配策略顺序: \n", i + 1);
            for (int j = 0; j < threadNum; j++) {
                Pair p = resources.get(i).get(j);
                System.out.printf("%d -- %d\n", p.max, p.had);
                if (p.max - p.had > re) {
                    System.out.println("发生死锁, 此种资源请求不能被接受!");
                    return;
                } else {
                    re += p.max;
                }
            }
        }
        System.out.println("不会发生死锁");
    }

    public static void main(String[] args) {
        Banker banker = new Banker();
        banker.init();
        banker.disPlayInfo();
        banker.judge();
    }
}

class Pair {
    int max;
    int had;

    public Pair(int max, int had) {
        this.max = max;
        this.had = had;
    }
}
