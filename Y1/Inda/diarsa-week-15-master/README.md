### Deadline:
This work should be completed before **Wednesday 3rd Feburary**.

### Instructions:
To pass the assignment, you must do all of the tasks. Small errors are acceptable, but the most important thing is that you attempt all the tasks. If you get stuck, then help is available in the labs.

Please note that this is individual work. You may discuss the work with other students, but it is absolutely forbidden to submit copies of other student's work as your own. Please read and consider the [Code of Honour](https://www.kth.se/csc/utbildning/hederskodex) carefully.

### Submission:
* All required work must be committed to your KTH Github Repository
* A week-15 repository will be created for you automatically and it can be found [here](https://gits-15.sys.kth.se/inda-16)
* Please refer to the Kurswiki for help, contact your teaching assistant, or course leader if you get stuck

### Homework
Study the following course literature:

* Chapter 10: Further Abstraction Techniques, Objects First with Java
* [Tutorial on Regular Expressions](http://www.vogella.com/tutorials/JavaRegularExpressions/article.html), Lars Vogel

### Task 1 - Create a Stack Interface
A stack is an abstract data type (in Java "collection") that supports the following methods:

* `push(o)` Adds the element o to the top of the stack

* `pop()` Removes and returns the top element in the stack, that is the element that was last added to the stack. An error occurs if the stack is empty.

* `top()` Returns the top element in the stack without removing it. An error occurs if the stack is empty.

* `size()` Returns the number of elements in the stack.

* `isEmpty()` indicates whether the stack is empty.

Describe this abstract data type in Java using an interface named Stack. Don't forget the documentation, it's extra important in an interface, where there's no program code.

### Task 2 - Create an implementation of Stack
Write a class that implements this interface. It's beneficial to use the Linked List from the previous assignment. It's up to you how to handle errors. Make sure that all methods are O(1) in the worst case. Test all methods properly. You should also submit test code.

### Task 3 - Evaluation of postfix-expressions
A stack is a very useful data type that amongst other things is used to implement method-calls in programming languages. This weeks assignment is not to implement a whole language however.

You will write a program that can calculate arithmetic expressions written in postfix-notation. It's a simple way to write arithmetic expressions that doesn't require parentheses nor priority-rules. Here are some examples of expressions written in the usual infix-notation and postfix respectively:

|Infix Expression                 |Postfix Expression
|---------------------------------|---------------------
|`0 + 1`                          |`0 1 +`
|`(2 + -3) * 4`                   |`2 -3 + 4 *`
|`(5 - 6) * (7 + 8)`              |`5 6 - 7 8 + *`
|`((9 + 10) * 11 - 12) / 13`      |`9 10 + 11 * 12 - 13 /`
|`14 * (15 - (16 + 17))`          |`14 15 16 17 + - *`

You see that the operator is always placed directly following its operands. Postfix-expressions are easy to compute with a stack. The algorithm can be described as follows:

1. Parse the expression symbol by symbol from left to right.

2. As soon as you see an operand, push it to the stack.

3. As soon as you see an operator, pop both operands from the stack and push the result of the computation to the stack.

Write a method in Java that takes a postfix-expression represented as a String of Integer-operands and arithmetic operators as input, and returns the value of the expression as an integer.

Use the Stack you implemented in the previous assignment. Work from the skeleton **code/Postfix.java**. The two helper-methods `isOperator` and `isInteger` should be implemented using regular expressions. The skeleton contains test-code and your implementation must pass all test-cases.
