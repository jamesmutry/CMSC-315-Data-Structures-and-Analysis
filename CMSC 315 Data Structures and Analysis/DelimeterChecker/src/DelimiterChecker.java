import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

//Class 2: DelimiterChecker
//This class will contain the `main` method and handle the logic for checking matching delimiters.


public class DelimiterChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileProcessor fileProcessor = null;

        while (fileProcessor == null) {
            //System.out.print("Enter the file name: ");
            //String fileName = scanner.nextLine();
            try {
                fileProcessor = new FileProcessor("/Users/james/IdeaProjects/CMSCProject1/src/test.txt");
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please try again.");
            }
        }

        Stack delimiterStack = new Stack<>();
        try {
            char ch;
            while ((ch = fileProcessor.getNextCharacter()) != '\0') {
                if (ch == '(' || ch == '{' || ch == '[') {
                    delimiterStack.push(ch);
                } else if (ch == ')' || ch == '}' || ch == ']') {
                    if (delimiterStack.isEmpty()) {
                        System.out.println("Unmatched delimiter :  " + ch + "\n  at " + fileProcessor.getCurrentPosition());
                        return;
                    }
                    char lastDelimiter = (char) delimiterStack.pop();
                    if (!isMatchingPair(lastDelimiter, ch)) {
                        System.out.println("Mismatched delimiter " + ch + " at " + fileProcessor.getCurrentPosition());
                        return;
                    }
                }
            }

            if (!delimiterStack.isEmpty()) {
                System.out.println("Unmatched delimiter: " + delimiterStack.pop() +" \n Located at : " + fileProcessor.getCurrentPosition());
            } else {
                System.out.println("All delimiters match.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')') ||
                (left == '{' && right == '}') ||
                (left == '[' && right == ']');
    }
}