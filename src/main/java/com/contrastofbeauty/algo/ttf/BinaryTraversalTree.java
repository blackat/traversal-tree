package com.contrastofbeauty.algo.ttf;

import java.util.List;

/**
 * User: elentini
 * Date: 05.12.13
 */
public class BinaryTraversalTree <Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;

        private Value value;

        private Node left, right;

        private int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public Node getRoot() {
        return root;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if(node == null) {
            return new Node(key, value, 1);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if(cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        node.size = size(node.left) + size(node.right);

        return node;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        } else {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                return get(node.left, key);
            } else if (cmp > 0) {
                return get(node.right, key);
            } else {
                return node.value;
            }
        }
    }

    @Override
    public String toString() {
        return super.toString();    //To change body of overridden methods use File | Settings | File Templates.
    }

    private void print(Node node) {
        if (node == null) return;

        print(node.left);
        System.out.println(node.key);
        print(node.right);
    }

    public List<Key> traverse(Node node, List<Key> list) {
        // base case to exit from recursion
        if (node == null) {
            return list;
        }

        traverse(node.left, list);
        list.add(node.key);
        traverse(node.right, list);
        return list;
    }
}
