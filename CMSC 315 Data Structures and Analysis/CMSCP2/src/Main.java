//James Mutry
//Project 3
//21 JUL 24
//Main class to run the program

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Prompt for tree and selections
        while (true) {
            System.out.print("Enter a binary tree: ");
            String input = scanner.nextLine().trim();

            try {
                BinaryTree tree = new BinaryTree(input);
                System.out.println("Indented representation:");
                tree.printIndented();

                if (tree.isBinarySearchTree()) {
                    if (tree.isBalanced()) {
                        System.out.println("It is a balanced binary search tree");
                    } else {
                        System.out.println("It is a binary search tree but it is not balanced");
                        ArrayList<Integer> values = tree.getValues();
                        BinaryTree balancedTree = new BinaryTree(values);
                        System.out.println("Balanced tree:");
                        balancedTree.printIndented();
                        System.out.println("Original tree has height " + tree.getHeight());
                        System.out.println("Balanced tree has height " + balancedTree.getHeight());
                    }
                } else {
                    System.out.println("It is not a binary search tree");
                }
            } catch (InvalidSyntaxException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }

            System.out.print("More trees? (Y/N): ");
            String choice = scanner.nextLine().trim();
            if (!choice.equalsIgnoreCase("Y")) {
                break;
            }
        }

        scanner.close();
    }
}