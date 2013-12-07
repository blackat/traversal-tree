package com.contrastofbeauty.algo.ttf;

import java.util.List;

/**
 * User: elentini
 * Date: 06.12.13
 */
public class TraversalTreeForest<Key extends Comparable<Key>, Value> {

    private Node root;

    /**
     * Representation of a node for a general traversal tree.
     */
    protected class Node {
        private Key parent;
        private Node leftMostChild;
        private Node rightSibling;
        private Key key;
        private Value value;

        public Node(Key parent, Key key, Value value) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public Key getParent() {
            return parent;
        }

        public Node getLeftMostChild() {
            return leftMostChild;
        }

        public Node getRightSibling() {
            return rightSibling;
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }

        public boolean isRoot() {
            return parent == null ? true : false;
        }
    }

    public Node getRoot() {
        return root;
    }

    public void put(Key key, Value value, Key parent) {
        root = put(root, parent, key, value);
    }

    private Node put(Node root, Key parent, Key key, Value value) {
        /**
         * Recursion base case, only this statement is able to create a new node.
         * Only place where a recursion can terminate.
         */
        if (root == null) {
            return new Node(parent, key, value);
        }

        // check one parent node against the other parent node: 1)son, 2)sibling

        // 1) left path: new node is son of the root node
        if (root.key == parent && root.leftMostChild == null) {
            root.leftMostChild = put(root.leftMostChild, parent, key, value);
        } else if (root.key == parent && root.leftMostChild != null) {
            // root node has already a direct son on the left, so new node must be sibling of this son
            put(root.getLeftMostChild(), parent, key, value);
        } else if(root.key != parent && root.parent != parent && root.leftMostChild != null) {
            put(root.getLeftMostChild(), parent, key, value);
        }

        // 2) right path: new node is sibling of the root node
        if (root.parent == parent && root.rightSibling == null) {
            root.rightSibling = put(root.rightSibling, parent, key, value);
        } // current node has already a sibling so recursively look for a sibling without a sibling
        else if (root.parent == parent && root.rightSibling != null) {
            put(root.rightSibling, parent, key, value);
        } else if(root.parent != parent && root.rightSibling != null) {
            put(root.rightSibling, parent, key, value);
        }

        return root;
    }

    public Node getNode(Node root, Key parent) {

        Node found;

        if (root == null) {
            return null;
        }

        if (root.getKey() == parent) {
            return root;
        }

        //left search
        found = getNode(root.getLeftMostChild(), parent);
        if (found != null && found.getKey() == parent) {
            return found;
        }

        //right search
        found = getNode(root.getRightSibling(), parent);
        if (found != null && found.getKey() == parent) {
            return found;
        }

        return root;
    }

    public List<Key> getPath(Node root, List<Key> list) {

        if (root == null) {
            return list;
        }

        // left recursion
        if (root.leftMostChild == null) {
        } else if (root.leftMostChild != null) {
            getPath(root.leftMostChild, list);
        }

        list.add(root.key);

        // right recursion
        if(root.rightSibling != null) {
            getPath(root.rightSibling, list);
        }

        return list;
    }
}
