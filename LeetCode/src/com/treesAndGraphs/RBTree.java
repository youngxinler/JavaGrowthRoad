package com.treesAndGraphs;

import com.sun.org.apache.bcel.internal.generic.RET;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

class RBTreeNode<T extends Comparable<T>>{
    private T value;
    private RBTreeNode<T> left;
    private RBTreeNode<T> right;
    private RBTreeNode<T> parent;
    private boolean red;

    public RBTreeNode() {
    }

    public RBTreeNode(T value) {
        this.value = value;
    }

    public RBTreeNode(T value, boolean isRed) {
        this.value = value;
        this.red = isRed;
    }

    public T getValue(){
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

    RBTreeNode<T> getLeft(){
        return left;
    }

    void setLeft(RBTreeNode<T> left) {
        this.left = left;
    }

    RBTreeNode<T> getRight() {
        return right;
    }

    void setRight(RBTreeNode<T> right) {
        this.right = right;
    }

    RBTreeNode<T> getParent() {
        return parent;
    }

    void setParent(RBTreeNode<T> parent) {
        this.parent = parent;
    }

    boolean isRed() {
        return red;
    }

    boolean isBlack(){
        return !red;
    }

    //判断叶子节点
    boolean isLeaf(){
        return left == null && right == null;
    }

    void setRed(boolean red) {
        this.red = red;
    }

    void makeRed(){
        this.red = true;
    }

    void makeBlack(){
        this.red = false;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

public class RBTree<T extends Comparable<T>>{
    private final RBTreeNode<T> root;
    private AtomicLong size = new AtomicLong(0);

    //in overwrite mode,all node's value can not  has same    value
    //in non-overwrite mode,node can have same value, suggest don't use non-overwrite mode.
    private volatile boolean overrideMode = true;

    public RBTree(){
        this.root = new RBTreeNode<>();
    }

    public RBTree(boolean overrideMode){
        this.root = new RBTreeNode<>();
        this.overrideMode = overrideMode;
    }

    public boolean isOverrideMode() {
        return overrideMode;
    }

    public void setOverrideMode(boolean overrideMode) {
        this.overrideMode = overrideMode;
    }

    /*
     * get number of node
     * @return
     */
    public AtomicLong getSize() {
        return size;
    }

    //这里有疑惑
    private RBTreeNode<T> getRoot(){
//        return root.getLeft();
        return root;
    }

    public T addNode(T value){
        RBTreeNode<T> t = new RBTreeNode<>(value);
        //(RBTreeNode t) 重载. TO DO
        return addNode(t);
    }

    public T find(T value){
        RBTreeNode<T> dataRoot = getRoot();
        while (dataRoot != null){
            //判断节点在右子树,左子树or Root
            int cmp = dataRoot.getValue().compareTo(value);
            if (cmp < 0){
                dataRoot = dataRoot.getRight();
            }else if (cmp > 0){
                dataRoot = dataRoot.getLeft();
            }else {
                return dataRoot.getValue();
            }
        }
        return null;
    }

    public T remove(T value){
        RBTreeNode dataRoot = getRoot();
        RBTreeNode parent = root;
        while (dataRoot != null){
            int cmp = dataRoot.getValue().compareTo(value);
            if (cmp < 0){
                parent = dataRoot;
                dataRoot = dataRoot.getRight();
            }else if (cmp > 0){
                parent = dataRoot;
                dataRoot = dataRoot.getLeft();
            }else {
                if (dataRoot.getRight() != null){
                    RBTreeNode<T> min = removeMin(dataRoot.getRight());
                }
            }
        }
    }

    private RBTreeNode<T> removeMin(RBTreeNode node){
        RBTreeNode<T> parent = node;
        //找到最小节点
        while (node != null && node.getLeft() != null){
            parent = node;
            node = node.getLeft();
        }

        //移除最小节点
        if (parent == node){
            return node;
        }

        parent.setLeft(node.);
    }
}