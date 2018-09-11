### Deadline:
This work should be completed before **Friday 17th Feburary**.

### Instructions:
To pass the assignment, you must do all of the tasks. Small errors are acceptable, but the most important thing is that you attempt all the tasks. If you get stuck, then help is available in the labs.

Please note that this is individual work. You may discuss the work with other students, but it is absolutely forbidden to submit copies of other student's work as your own. Please read and consider the [Code of Honour](https://www.kth.se/csc/utbildning/hederskodex) carefully.

### Submission:
* All required work must be committed to your KTH Github Repository
* A week-17 repository will be created for you automatically and it can be found [here](https://gits-15.sys.kth.se/inda-16)
* Please refer to the Kurswiki for help, contact your teaching assistant, or course leader if you get stuck

### Homework
Study the following course literature:

* [Träd](http://www.nada.kth.se/~snilsson/algoritmer/trad/)
* [Treaps](http://www.nada.kth.se/~snilsson/treap/)

### Task 1 - Implement a BST
Implement a Binary Search Tree (BST) in Java. As usual, all public methods should be well-specified and the test-code should be submitted as well.

The keys are Objects that implements the interface `java.lang.Comparable`. A generic class with a type-parameter that matches classes that implements Comparable can be written as follows:

    class Tree<T extends Comparable<T>>

Each node in the tree should be represented by an object that contains two pointers, one the the left and one to the right sub-tree. The following operations have to be implemented.

* Search -- test for presence of a value
* Insert -- add value to tree; duplicates are not allowed
* Size -- the number of elements
* Depth -- the height of the tree
* Leaves -- the number of leaves
* toString -- elements should be ordered

Search and Insert operations should be implemented iteratively.  Size can be implemented in a variety of ways and you are free to choose the most efficient.  Depth, Number of Leaves and toString() should be implmented using recursion.

### Task 2 - Time Complexity
Calculate the worst-case time complexity for all operations of the BST and complete the table below.  As usual, motivate your answers.

| Operation (BST)     | Time Complexity (worst case)    |
| ------------------- | ------------------------------- |
| Search              |         O(n)                    |
| Insert              |         O(n)                    |
| Size                |         O(1)                    |
| Depth               |         O(n)                    |
| Leaves              |         O(n)                    |
| toString            |         O(n)                    |

toString & leaves är alltid O(n) pga. de måste gå genom hela listan.
depth av samma anledning att den kan behöva göra det. search och insert även samma argument.
size är bara en integer i det här fallet som plussas för varje insättning.

If you instead use a randomized BST (Treap) the time complexity gets better.  What's the expected time complexity for Search and Insert in a treap? Motivate your answer.

| Operation (Treap)   | Time Complexity (worst case)    |
| ------------------- | ------------------------------- |
| Search              |             O(logn)             |
| Insert              |             O(logn)             |

Svar enligt Treaps in Java(1997), då trädet oftast är balanserat är det bara halva som behöver sökas.
