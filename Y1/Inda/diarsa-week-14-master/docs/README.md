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

Answer: The superclass Device must have a "getName" method, otherwise it will not compile.


#### Exercise 2:
In the same situation as in the previous exercise, if both classes have an
implementation of `getName`, which one will be executed?

Answer: The one furthest down the hierarchy will be called, so therefore the Printer class will execute its getName method.

#### Exercise 3:
Assume that you write a class `Student` that does not have a declared su-
perclass. You do not write a `toString` method. Consider the following lines of code:

	Student st = new Student();
	String s = st.toString();

Will these lines compile? If so, what exactly will happen when you try to execute?

Answer: Yes they'll compile, the to-string method will return the memory address.

#### Exercise 4:
In the same situation as before (class `Student`, no `toString` method), will the following lines compile? Why?

	Student st = new Student();
	System.out.println(st);

Answer: Yes they will also compile, System.out.println() automatically calls for the .toString (of the Object superclass) method if it is not written.


#### Exercise 5:
Assume that your class `Student` overrides `toString` so that it returns the student’s name. You now have a list of students. Will the following code compile? If not, why not? If yes, what will it print? Explain in detail what happens.

	for(Object st : myList) {
    	System.out.println(st);
	}

Answer: Yes, it will still compile. It will print the names of the students by iterating over all the students in the list and calling the toString method on each one.

#### Exercise 6:
Write a few lines of code that result in a situation where a variable `x` has the static type `T` and the dynamic type `D`.

Answer: T x = new D();
This is pretty much exactly the same example as the one on the bottom of page 305.

### Task 2 - Linked Lists

1. Implement a singly linked list. A code skeleton can be found in the file code/LinkedList.java. You are not allowed to change the API of the class, that is, you are not allowed to modify the signatures of the public methods in the class `LinkedList`, or add any new public methods.

2. Calculate the asymptotic worst-case-time for all public methods in your implementation. Express the time as a function of the number of elements `n` in the list.

3. Write extensive test-code. All public methods shall be tested. Don't forget that your code works even for the empty list. It
is recommended that you write the test-code first.

Answer (2): All worst-case-time scenarios for the methods are constant, so O(1). Except for the isHealthy method which has a
counter for each element and also the get method. Both the get and the isHealthy methods have a worst-case-time of O(n).
