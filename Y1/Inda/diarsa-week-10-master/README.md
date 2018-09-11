### Deadline
This work should be completed before **Friday 25th November**.

### Instructions
To pass the assignment, you must do all of the tasks. Small errors are acceptable, but the most important thing is that you attempt all the tasks. If you get stuck, then help is available in the labs.

Please note that this is individual work. You may discuss the work with other students, but it is absolutely forbidden to submit copies of other student's work as your own. Please read and consider the [Code of Honour](https://www.kth.se/csc/utbildning/hederskodex) carefully.

### Submission
- All required work must be committed to your KTH Github Repository.
- A week-10 repository will be created for you automatically and it can be found [here](https://gits-15.sys.kth.se/inda-16).
- Please refer to the Kurswiki for help. Contact your teaching assistant or course leader if you get stuck.

### Homework
Study all the following pages from the [course text](http://www.nada.kth.se/~snilsson/algoritmer/) (online):

[Hur snabb är datorn?](http://www.nada.kth.se/~snilsson/algoritmer/tid)

[Algoritmer](http://www.nada.kth.se/~snilsson/algoritmer/algoritmer)

### Github Task: List Processor
In this assignment, you will perform basic list processing tasks using both the primitive array and lists from the Collections hierarchy.  It is important that you can implement algorithms in either form.  Collectively, we shall refer to both `int[]` number and `List<Integer>` as "lists of numbers" in this assignment.

#### Setup
- Create a Java class called `ListProcessor`
- ListProcessing should contain all of the following methods specified below
- You must use the same method names
- Methods should be appropriately documented and tested
- You may also include a main method if desired

#### Exercise 1: Populating Lists
Implement the following:

`public int[] fillArray(int from, int to)`

and

`public List<Integer> fillList(int from, int to)`

These methods should return populated lists with integer values in the range from the lower bound `from` until the upper bound `to` (exclusive).

e.g. `fillArray(0, 5);` should return the following array `[0,1,2,3,4]`

#### Exercise 2: Shuffling Lists
Implement the following:

`public int[] shuffleArray(int[] numbers)`

and

`public List<Integer> shuffleList(List<Integer> numbers)`

These methods should return a shuffled list of numbers, by randomly swapping elements to randomise the ordering.

n.b. you cannot use `Collections.shuffle()` ;)

#### Exercise 3: Summing Lists Iteratively
Implement the following:

`public int sumArrayIterative(int[] numbers)`

and

`public int sumListIterative(List<Integer> numbers)`

These methods should return the sum of a list of numbers using iteration.

#### Exercise 4: Summing Lists Recursively
Implement the following:

`public int sumArrayRecursive(int[] numbers)`

and

`public int sumListRecursive(List<Integer> numbers)`

These methods should achieve the same as Exercise 3, however they should use recursion instead.  There are several strategies that you may use here.  You may also create private helper methods if required.  Finally, there are some useful methods in java.util.Arrays and java.util.ArrayList that may help depending on your strategy.
