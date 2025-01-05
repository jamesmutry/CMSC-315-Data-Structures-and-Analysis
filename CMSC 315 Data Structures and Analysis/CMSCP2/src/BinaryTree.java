//James Mutry
//Project 3
//21 JUL 24
//TreeNode Class s

import java.util.ArrayList;
public class BinaryTree {
    private TreeNode root;
//constructor that accepts a string containing the preorder representation of a binary tree
//and constructs a binary tree
    public BinaryTree(String preorder) throws InvalidSyntaxException {
        root = constructTree(preorder.split("\\s+"), new int[]{0});
    }
    //constructor that accepts an array list of integers and constructs a balanced binary search
    //tree containing those values
    public BinaryTree(ArrayList<Integer> values) {
        root = constructBalancedBST(values, 0, values.size() - 1);
    }
    // method that outputs the binary tree in indented form
    private TreeNode constructTree(String[] tokens, int[] index) throws InvalidSyntaxException {
        if (index[0] >= tokens.length) {
            throw new InvalidSyntaxException("Incomplete Tree");
        }

        String token = tokens[index[0]++];
        if (token.equals("*")) {
            return null;
        }

        if (!token.matches("\\d+")) {
            throw new InvalidSyntaxException("Data is Not an Integer");
        }

        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.left = constructTree(tokens, index);
        node.right = constructTree(tokens, index);
        return node;
    }

    private TreeNode constructBalancedBST(ArrayList<Integer> values, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(values.get(mid));
        node.left = constructBalancedBST(values, start, mid - 1);
        node.right = constructBalancedBST(values, mid + 1, end);
        return node;
    }

    public void printIndented() {
        printIndented(root, 0);
    }

    private void printIndented(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        printIndented(node.right, depth + 1);
        System.out.println(" ".repeat(depth * 2) + node.data);
        printIndented(node.left, depth + 1);
    }
// method that returns whether the tree is a binary search tree
    public boolean isBinarySearchTree() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.data < min || node.data > max) {
            return false;
        }
        return isBST(node.left, min, node.data - 1) && isBST(node.right, node.data + 1, max);
    }
//method that returns if the tree is balanced
    public boolean isBalanced() {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }
    //method that returns the height of the tree
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public ArrayList<Integer> getValues() {
        ArrayList<Integer> values = new ArrayList<>();
        getValues(root, values);
        return values;
    }
    //A method that returns an array list of the values in the tree
    private void getValues(TreeNode node, ArrayList<Integer> values) {
        if (node == null) {
            return;
        }
        getValues(node.left, values);
        values.add(node.data);
        getValues(node.right, values);
    }

    private static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }
}
