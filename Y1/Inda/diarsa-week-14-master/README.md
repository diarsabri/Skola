### Deadline:
This work should be completed before **Friday 26th January**.

### Instructions:
To pass the assignment, you must do all of the tasks. Small errors are acceptable, but the most important thing is that you attempt all the tasks. If you get stuck, then help is available in the labs.

Please note that this is individual work. You may discuss the work with other students, but it is absolutely forbidden to submit copies of other student's work as your own. Please read and consider the [Code of Honour](https://www.kth.se/csc/utbildning/hederskodex) carefully.

### Submission:
* All required work must be committed to your KTH Github Repository
* A week-14 repository will be created for you automatically and it can be found [here](https://gits-15.sys.kth.se/inda-16)
* Please refer to the Kurswiki for help, contact your teaching assistant, or course leader if you get stuck

### Homework
Study the following course literature:

* Chapter 9: More about Inheritance, Objects First with Java

### Task 1 - Inheritance

#### Exercise 1:
Assume that you see the following lines of code:

	Device dev = new Printer();
	dev.getName();

`Printer` is a subclass of `Device`. Which of these classes must have a definition of method `getName` for this code to compile?

(See Exercise 9.11 for context, in Chp9, Objects First with Java)

#### Exercise 2:
In the same situation as in the previous exercise, if both classes have an
implementation of `getName`, which one will be executed?

(See Exercise 9.12 for context, in Chp9, Objects First with Java)

#### Exercise 3:
Assume that you write a class `Student` that does not have a declared su-
perclass. You do not write a `toString` method. Consider the following lines of code:

	Student st = new Student();
	String s = st.toString();

Will these lines compile? If so, what exactly will happen when you try to execute?

(See Exercise 9.13 for context, in Chp9, Objects First with Java)

#### Exercise 4:
In the same situation as before (class `Student`, no `toString` method), will the following lines compile? Why?

	Student st = new Student();
	System.out.println(st);

(See Exercise 9.14 for context, in Chp9, Objects First with Java)

#### Exercise 5:
Assume that your class `Student` overrides `toString` so that it returns the student’s name. You now have a list of students. Will the following code compile? If not, why not? If yes, what will it print? Explain in detail what happens.

	for(Object st : myList) {
    	System.out.println(st);
	}

(See Exercise 9.14 for context, in Chp9, Objects First with Java)

#### Exercise 6:
Write a few lines of code that result in a situation where a variable `x` has the static type `T` and the dynamic type `D`.

(See Exercise 9.16 for context, in Chp9, Objects First with Java)

### Task 2 - Linked Lists
A list, a number of elements ordered in a linear structure, is perhaps the simplest and most elementary data structure. Java provides several variants of lists:

* The standard `array` (`int[]` for example) has hardware support, but is simple and somewhat limited

* `ArrayList` is implemented using an array, but has added functionality

* `LinkedList` which has largely the same functionality as ArrayList but different performance

A linked list is a sequence of list elements connected by pointers. A linked list with three integers `[2,2,1]` looks as follows:

	     ----------        ----------        ----------
	    |     |    |      |     |    |      |     |    |
	--->|  2  |  -------->|  2  |  -------->|  1  |null|
	    |     |    |      |     |    |      |     |    |
	     ----------        ----------        ----------

The elements can be implemented as objects with two instance variables containing the value of the node, and a variable containing a pointer to the next element in the list:

	class ListElement {
	    int data;
	    ListElement next;
	}

You are to complete the following tasks:

1. Implement a singly linked list. A code skeleton can be found in the file code/LinkedList.java. You are not allowed to change the API of the class, that is, you are not allowed to modify the signatures of the public methods in the class `LinkedList`, or add any new public methods.

2. Calculate the asymptotic worst-case-time for all public methods in your implementation. Express the time as a function of the number of elements `n` in the list.

3. Write extensive test-code. All public methods shall be tested. Don't forget that your code works even for the empty list. It is recommended that you write the test-code first.

It can be hard to test data structures; a method might return a correct answer but still be broken. A good way to uncover these types of errors is to use a test-method that ensures the list is in a correct state, such that:

* `size` is equal to the number of `ListElements`

* If the list is empty, `first` and `last` both point to `null`. Otherwise they point to a `ListElement`.

* If an element is in the last position of the list, `next` should point to `null`

You shall implement a method `isHealthy()` that checks the above requirements. In appropriate places (probably quite many) in your test-code you will call `isHealthy()` to determine that the list hasn't been broken during implementation.
