import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
    private BufferedReader reader;
    private int lineNumber;
    private int charNumber;
    private String currentLine;
    private int currentIndex;

    public FileProcessor(String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
        lineNumber = 0;
        charNumber = 0;
        currentLine = null;
        currentIndex = 0;
    }

    public char getNextCharacter() throws IOException {
        while (true) {
            if (currentLine == null || currentIndex >= currentLine.length()) {
                currentLine = reader.readLine();
                if (currentLine == null) {
                    return '\0'; // End of file
                }
                lineNumber++;
                currentIndex = 0;
            }

            char ch = currentLine.charAt(currentIndex++);
            charNumber++;

            // Skip comments and literals
            if (ch == '/' && currentIndex < currentLine.length()) {
                char nextChar = currentLine.charAt(currentIndex);
                if (nextChar == '/') {
                    currentLine = null; // Skip single-line comment
                    continue;
                } else if (nextChar == '*') {
                    skipMultiLineComment();
                    continue;
                }
            } else if (ch == '"' || ch == '\'') {
                skipLiteral(ch);
                continue;
            }

            return ch;
        }
    }

    private void skipMultiLineComment() throws IOException {
        while (true) {
            if (currentLine == null || currentIndex >= currentLine.length()) {
                currentLine = reader.readLine();
                if (currentLine == null) {
                    return; // End of file
                }
                lineNumber++;
                currentIndex = 0;
            }

            char ch = currentLine.charAt(currentIndex++);
            if (ch == '*' && currentIndex < currentLine.length() && currentLine.charAt(currentIndex) == '/') {
                currentIndex++;
                return;
            }
        }
    }

    private void skipLiteral(char delimiter) throws IOException {
        while (true) {
            if (currentLine == null || currentIndex >= currentLine.length()) {
                currentLine = reader.readLine();
                if (currentLine == null) {
                    return; // End of file
                }
                lineNumber++;
                currentIndex = 0;
            }

            char ch = currentLine.charAt(currentIndex++);
            if (ch == delimiter) {
                return;
            }
        }
    }

    public String getCurrentPosition() {
        return "Line: " + lineNumber + ", Char: " + charNumber;
    }
}
