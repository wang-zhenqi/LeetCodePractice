package com.practice.zhenqi;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BTree bTree = new BTree("[1,2,n,4,5,6,7,n,8,n,9]");
        bTree.preorderTraverse();
        bTree.inorderTraverse(1);
        bTree.postorderTraverse(1);
        bTree.levelorderTraverse();
    }
}
