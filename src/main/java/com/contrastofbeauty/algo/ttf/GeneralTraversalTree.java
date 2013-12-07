package com.contrastofbeauty.algo.ttf;

import java.util.ArrayList;
import java.util.List;

/**
 * User: elentini
 * Date: 05.12.13
 */
public class GeneralTraversalTree<Key extends Comparable<Key>, Value> {

    private Node root;

    /**
     * Representation of a node for a general traversal tree.
     */
    protected class Node {
        private Node parent;
        private Node leftMostChild;
        private List<Node> rightSiblings;
        private Key key;
        private Value value;

        public Node(Key key, Key parent, Value value) {
            this.key = key;
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public Node getLeftMostChild() {
            return leftMostChild;
        }

        public List<Node> getRightSiblings() {
            return rightSiblings;
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

    public void put(Key key, Key parent, Value value) {
        root = put(root, key, parent, value);
    }

    /**
     * Add a node to the tree structure. As first attempt it checks if the node is null,
     * so creates the root.
     * <p/>
     * Then, if the root already exists, it checks if the parent of the new node is the root,
     * if not it looks for the parent node reference to attach the new node.
     * <p/>
     * When the parent node
     *
     * @param node  parent node
     * @param key   key of the new node
     * @param value value of the new node
     * @return parent node modified
     */
    //TODO: to compare the node, the == is used, better to use compareTo method.
    private Node put(Node node, Key key, Key parent, Value value) {
        //TODO: add case for a tree forest
        // add the root node to the tree
        if (node == null) {
            return new Node(key, parent, value);
            // current node is the parent of the new node that must be added
        } else if (node.parent == null && parent == null) {
            // add a new root node as sibling of the existing root
            if (node.rightSiblings == null) {
                node.rightSiblings = new ArrayList<Node>();
            }
            node.rightSiblings.add(new Node(key, parent, value));
        } else if (node.key == parent) {
            // first node to be added is the left most child
            if (node.leftMostChild == null) {
                node.leftMostChild = put(node.leftMostChild, key, parent, value);
            } else {
                // if the left most child is already added, new nodes are added as siblings
                if (node.rightSiblings == null) {
                    node.rightSiblings = new ArrayList<Node>();
                }
                node.rightSiblings.add(new Node(key, parent, value));
            }
        } else {
            // find the parent node to attach the new node
            put(getNode(node, parent), key, parent, value);
        }

        return node;
    }

    /**
     * Start to search a given parent node from the node passed as an argument.
     *
     * @param node   starting point for the search
     * @param parent
     * @return
     */
    protected Node getParentNode(Node node, Key parent) {

        //TODO: add while/for to look in different root node

        // base case to exit from the recursion
        if (node.key == parent) {
            return node;
        }

        // search in the left child
        if (node.leftMostChild != null) {
            return getParentNode(node.leftMostChild, parent);
        }

        // search in the siblings
        if (node.getRightSiblings() != null) {
            // search in the siblings
            Node found = null;
            for (Node sibling : node.rightSiblings) {
                found = getParentNode(sibling, parent);
                if (found != null) {
                    return found;
                }
            }
            return found;
        }
        return node;
    }

    protected void getNode(Node node, Key key, List<Node> list) {
        if (node == null) {
            return;
        }


        getNode(node.leftMostChild, key, list);

        if (node.getKey() == key) {
            list.add(node);
            return;
        }

        if(node.getRightSiblings() != null) {
            for (Node sibling : node.getRightSiblings()) {
                getNode(sibling, key, list);
            }
        }
        return;
    }

    protected Node getNode(Node node, Key key) {
        if (node == null) {
            return node;
        }


        getNode(node.leftMostChild, key);

        if (node.getKey() == key) {
            return node;
        }

        if(node.getRightSiblings() != null) {
            Node found = null;
            for (Node sibling : node.getRightSiblings()) {
                found = getNode(sibling, key);
                if(found != null && node.getKey() == key) {
                    return found;
                }
            }
        }
        return node;
    }
}

