package com.practice.zhenqi;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

public class BTree {
    private static class Node {
        private final int value;
        private Node lchild;
        private Node rchild;

        public Node(int val) {
            value = val;
            lchild = null;
            rchild = null;
        }
    }

    private static final Logger LOG = Logger.getLogger(BTree.class.getName());

    private Node root;
    private List<Integer> traversalResult;

    /**
     * Recursively insert a list of elements into the binary tree in preorder
     * @param arr The whole list of elements to be added
     * @param index Current index of the $node
     * @param node Current node
     */
    private void preorderInsertRecursive(List<Integer> arr, int index, Node node) {
        if((index * 2 + 1) < arr.size() && arr.get(index * 2 + 1) != Integer.MIN_VALUE) {
            node.lchild = new Node(arr.get(index * 2 + 1));
            preorderInsertRecursive(arr, index * 2 + 1, node.lchild);
        }
        if((index * 2 + 2) < arr.size() && arr.get(index * 2 + 2) != Integer.MIN_VALUE) {
            node.rchild = new Node(arr.get(index * 2 + 2));
            preorderInsertRecursive(arr, index * 2 + 2, node.rchild);
        }
    }

    /**
     * Recursively traverse the binary tree in preorder
     * @param node The starting node
     */
    private void preTravRecursive(Node node) {
        if(node != null) {
            traversalResult.add(node.value);
            preTravRecursive(node.lchild);
            preTravRecursive(node.rchild);
        }
    }

    /**
     * Iteratively traverse the binary tree in preorder
     * @param node The starting node
     */
    private void preTravIterative(Node node) {
        Stack<Node> nodeStack = new Stack<>();
        List<Node> traversedNodes = new ArrayList<>();

        Node curNode = node;
        do {
            if(!traversedNodes.contains(curNode)) {
                nodeStack.push(curNode);
                traversalResult.add(curNode.value);
                traversedNodes.add(curNode);
            }

            if(curNode.lchild != null && !traversedNodes.contains(curNode.lchild)) {
                curNode = curNode.lchild;
                continue;
            }

            if(curNode.rchild != null && !traversedNodes.contains(curNode.rchild)) {
                curNode = curNode.rchild;
                continue;
            }

            nodeStack.pop();
            if(!nodeStack.isEmpty()) {
                curNode = nodeStack.peek();
            }
        } while(!nodeStack.isEmpty());
    }

    /**
     * Recursively traverse the binary tree in inorder
     * @param node The starting node
     */
    private void inTravRecursive(Node node) {
        if(node != null) {
            inTravRecursive(node.lchild);
            traversalResult.add(node.value);
            inTravRecursive(node.rchild);
        }
    }

    /**
     * Iteratively traverse the binary tree in inorder
     * @param node The starting node
     */
    private void inTravIterative(Node node) {
        Stack<Node> nodeStack = new Stack<>();
        List<Node> traversedNodes = new ArrayList<>();

        Node curNode = node;
        nodeStack.push(curNode);
        do {
            if(curNode.lchild != null && !traversedNodes.contains(curNode.lchild)) {
                nodeStack.push(curNode.lchild);
                curNode = curNode.lchild;
                continue;
            }

            if(!traversedNodes.contains(curNode)) {
                traversalResult.add(curNode.value);
                traversedNodes.add(curNode);
            }

            if(curNode.rchild != null && !traversedNodes.contains(curNode.rchild)) {
                nodeStack.push(curNode.rchild);
                curNode = curNode.rchild;
                continue;
            }

            nodeStack.pop();
            if(!nodeStack.isEmpty()) {
                curNode = nodeStack.peek();
            }
        } while(!nodeStack.isEmpty());
    }

    /**
     * Initialize the $traversalResult
     *
     */
    private void initTravResult() {
        if(traversalResult == null) {
            traversalResult = new ArrayList<>();
        } else {
            traversalResult.clear();
        }
    }

    /**
     * Format the $traversalResult to a string
     */
    private void travResultToString() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < traversalResult.size(); i++) {
            if(i == 0) {
                result.append(traversalResult.get(i).toString());
            } else {
                result.append(", ").append(traversalResult.get(i).toString());
            }
        }
        LOG.info(result + "\n");
    }

    /**
     * Constructor with no argument
     */
    public BTree() {
        root = null;
        traversalResult = null;
    }

    /**
     * Constructor with an argument to specify the root node
     * @param val The value to be assigned to root node
     */
    public BTree(int val) {
        root = new Node(val);
        traversalResult = null;
    }

    /**
     * Constructor with a String argument, build the tree from string
     * @param sequence Specify the tree, format:
     *                 "[1,2,null,4,...,n]"
     */
    public BTree(String sequence) {
        traversalResult = null;
        if(sequence.isEmpty() || sequence.equals("[]")) {
            root = null;
            return;
        }
        String[] nodeStrings = sequence.substring(1, sequence.length() - 1).split(",");
        List<Integer> nodeValues = new ArrayList<>();
        for(String nodeString : nodeStrings) {
            if(nodeValues.size() == 0) {
                try {
                    nodeValues.add(Integer.parseInt(nodeString));
                } catch(NumberFormatException e) {
                    LOG.severe(e.getLocalizedMessage());
                    return;
                }
                continue;
            } else {
                while(nodeValues.get((nodeValues.size() - 1) / 2) == Integer.MIN_VALUE) {
                    nodeValues.add(Integer.MIN_VALUE);
                }
            }
            if(nodeString.startsWith("n")) {
                nodeValues.add(Integer.MIN_VALUE);
            } else {
                try {
                    nodeValues.add(Integer.parseInt(nodeString));
                } catch(NumberFormatException e) {
                    LOG.severe(e.getLocalizedMessage());
                    return;
                }
            }
        }

        root = new Node(nodeValues.get(0));
        preorderInsertRecursive(nodeValues, 0, root);
    }

    /**
     * Judge the tree is empty or not
     * @return true: The tree has at least one element; false: The tree is empty
     */
    public boolean isNotEmpty() {
        return root != null;
    }

    /**
     * Public API for preorder traversal
     * @param option Two implementations: 0 - Recursive, 1 - iterative
     */
    public void preorderTraverse(int option) {
        if(option == 0) {
            preorderTraverse();
        } else {
            LOG.info("Iterative Preorder Traversal:\n");
            initTravResult();
            if(isNotEmpty()) {
                preTravIterative(root);
            }
            travResultToString();
        }
    }

    /**
     * Public API for preorder traversal, make the recursive implementation as default
     */
    public void preorderTraverse() {
        LOG.info("Recursive Preorder Traversal:\n");
        initTravResult();
        if(isNotEmpty()) {
            preTravRecursive(root);
        }
        travResultToString();
    }

    /**
     * Public API for inorder traversal
     * @param option Two implementations: 0 - Recursive, 1 - iterative
     */
    // option: 0 - Recursive, 1 - iterative
    public void inorderTraverse(int option) {
        if(option == 0) {
            inorderTraverse();
        } else {
            LOG.info("Iterative Inorder Traversal:\n");
            initTravResult();
            if(isNotEmpty()) {
                inTravIterative(root);
            }
            travResultToString();
        }
    }

    /**
     * Public API for inorder traversal, make the recursive implementation as default
     */
    public void inorderTraverse() {
        LOG.info("Recursive Inorder Traversal:\n");
        initTravResult();
        if(isNotEmpty()) {
            inTravRecursive(root);
        }
        travResultToString();
    }
}
