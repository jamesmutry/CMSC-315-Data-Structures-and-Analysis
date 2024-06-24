CMSC 315 Data Structures and Algorithms
Programming Project 1
The first programming project involves writing a program to determine whether another Java
source code file has matching delimiters. The delimiters that must match are parentheses, braces
and square brackets. The program should consist of two classes. The first class that encapsulates
the input file should contain the following public methods:
 A constructor that accepts the file name of the Java source file to be tested and throws a
FileNotFoundException if the file does not exist
 A method that returns the next character in the file excluding characters that are inside
either type of comment and characters in either character or string literals
 A method that returns a string containing the current line number and character number
of the current character
You may add any additional private methods that are required.
The second class should contain the main method. It should read in the file name from the
keyboard until a valid file name is entered and then create an object of the first class. It should
then repeatedly call the method that returns the next character until it returns a null character
indicating the end of the file or until a mismatch of delimiters is encountered. If the character is a
left delimiter it should be pushed onto a delimiter stack. If it is a right delimiter, the stack should
be popped and a check should be made to ensure that the delimiters are of a matching type. If the
delimiters do not match, a message should be displayed indicating what delimiter was
encountered and at what position. You may use the defined Java Stack class.
You are to submit two files.
1. The first is a .zip file that contains all the source code for the project. The .zip file
should contain only source code and nothing else, which means only the .java files. If
you elect to use a package the .java files should be in a folder whose name is the
package name. Every outer class should be in a separate .java file with the same name
as the class name. Each file should include a comment block at the top containing your
name, the project name, the date, and a short description of the class contained in that
file.
2. The second is a Word document (PDF or RTF is also acceptable) that contains the
documentation for the project, which should include the following:
a. A UML class diagram that includes all classes you wrote. Do not include
predefined classes.
b. A test plan that includes test cases that you have created indicating what aspects
of the program each one is testing
c. A short paragraph on lessons learned from the project
