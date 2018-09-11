### Deadline
This work should be completed before **Friday 2nd December**.

### Instructions
To pass the assignment, you must do all of the tasks. Small errors are acceptable, but the most important thing is that you attempt all the tasks. If you get stuck, then help is available in the labs.

Please note that this is individual work. You may discuss the work with other students, but it is absolutely forbidden to submit copies of other student's work as your own. Please read and consider the [Code of Honour](https://www.kth.se/csc/utbildning/hederskodex) carefully.

### Submission
- All required work must be committed to your KTH Github Repository.
- A week-11 repository will be created for you automatically and it can be found [here](https://gits-15.sys.kth.se/inda-16).
- Please refer to the Kurswiki for help. Contact your teaching assistant or course leader if you get stuck.

### Homework
Study all the following pages from the [course text](http://www.nada.kth.se/~snilsson/algoritmer/) (online):

- [Hur snabb är datorn?](http://www.nada.kth.se/~snilsson/algoritmer/tid)
- [Algoritmer](http://www.nada.kth.se/~snilsson/algoritmer/algoritmer)

### Github Task: Search & Sort
In this assignment, you will investigate how to compare objects, implement a typical sorting algorithm, and implement a binary search algorithm.  As usual, we will practice using both arrays and collections in Java, and you should reflect on the benefits and costs of each approach.  It may assist you to create helper methods that create lists of `Box` objects when testing, e.g. `Box[] generateArrayOfBoxes(int n)` and `List<Box> generateListOfBoxes(int n)`.

#### Requirements
- You must use the same method names as specified below
- Methods should be appropriately documented and tested
- You may also include a main method if desired

You should be thorough in the testing of the methods in this week. For each method, you should _at least_ test that:
- The method works for empty lists
- The method works for lists with a single element
- The method works as expected when the list contains duplicates
  - For example, if the list contains two Box objects with the same volume

#### Exercise 1: Comparable Box
A `Box` class has been provided for you in the `code` folder. Make yourself familiar with the source code, then expand upon this class so that it implements the [`Comparable<Box>`](http://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html) interface. You must override the `compareTo` method so that instances of `Box` can be compared by their `volume`. Information on Object Ordering and Comparable can be found [here](https://docs.oracle.com/javase/tutorial/collections/interfaces/order.html).

#### Exercise 2: Sorting
Create a Java class called `BoxProcessor`. This class will contain your methods for sorting / searching collections of `Box` objects.

* Choose either the Selection sort or Insertion sort algorithm, as shown below
* In `BoxProcessor` class, implement two versions of the algorithm, using arrays and collections, e.g.
	* `void insertionSort(Box[] array)`
	* `void insertionSort(List<Box> list)`
* Expected behaviour: list of Box objects will be sorted by increasing volume

<pre>
Insertion Sort (A)
------------------
1	for i = 1 to A.length - 1
2		j = i
3		while j > 0 and A[j-1] > A[j]
4			temp = A[j]
5			A[j] = A[j-1]
6			A[j-1] = temp
7			j = j - 1
</pre>

<pre>
Selection Sort (A)
------------------
1	for i = 0 to A.length - 1
2       min = i
3		for j = i + 1 to A.length - 1
4			if A[j] < A[min]
5				// minimum element found
6				min = j
7       temp = A[i]
8		A[i] = A[min]
9		A[min] = temp
</pre>

#### Exercise 3: Sequential Search
Now you will create an iterative sequential search algorithm that **searches for a particular Box volume**. This will be a 'brute force' approach that simply iterates through all elements.

* In `BoxProcessor` class, implement two versions, using both collections and arrays, e.g.
	* `int sequentialSearch(Box[] array, int volume)`
	* `int sequentialSearch(List<Box> list, int volume)`
* Expected behaviour: return index of Box if found, or -1 if Box is not found

#### Exercise 4: Binary Search
If you assume that the input array/list is sorted, you can use the binary search algorithm to improve search performance. Create a recursive (or iterative) version of the binary search algorithm that **searches for a particular Box volume**.

* In your `BoxProcessor` class, implement two versions, using both collections and arrays, e.g.
	* `int binarySearch(Box[] array, int volume)`
	* `int binarySearch(List<Box> list, int volume)`
* When testing, be sure to always use sorted lists/arrays as input.
* Expected behaviour: return index of Box if found, or -1 if Box is not found
