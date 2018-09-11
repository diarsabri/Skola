### Deadline:
This work should be completed before **Friday 20th January**.

### Instructions:
To pass the assignment, you must do all of the tasks. Small errors are acceptable, but the most important thing is that you attempt all the tasks. If you get stuck, then help is available in the labs.

Please note that this is individual work. You may discuss the work with other students, but it is absolutely forbidden to submit copies of other student's work as your own. Please read and consider the [Code of Honour](https://www.kth.se/csc/utbildning/hederskodex) carefully.

### Submission:
* All required work must be committed to your KTH Github Repository
* A week-13 repository will be created for you automatically and it can be found [here](https://gits-15.sys.kth.se/inda-16)
* Please refer to the Kurswiki for help, contact your teaching assistant, or course leader if you get stuck

### Homework
Study all the following course literature:

* Chapter 8: Improving Structure with Inheritance, Objects first with Java
* [Loop Invariants](http://www.nada.kth.se/~snilsson/algoritmer/loopinvariant/)
* [Induction and Recursive Functions](http://www.nada.kth.se/~snilsson/algoritmer/induktion/)
* [Time Complexity for Recursion](http://www.nada.kth.se/~snilsson/algoritmer/rekursion/)

### Github Task: Time Complexity

#### Exercise: 1
Assume that we have four classes: `Person`, `Teacher`, `Student`, and `PhDStudent`. `Teacher` and `Student` are both subclasses of `Person`. `PhDStudent` is a subclass of `Student`.

a. Which of the following assignments are legal, and why or why not?

    Person p1 = new Student();
    Person p2 = new PhDStudent();
    PhDStudent phd1 = new Student();
    Teacher t1 = new Person();
    Student s1 = new PhDStudent();

b. Suppose that we have the following legal declarations and assignments:

    Person p1 = new Person();
    Person p2 = new Person();
    PhDStudent phd1 = new PhDStudent();
    Teacher t1 = new Teacher();
    Student s1 = new Student();

Based on those just mentioned, which of the following assignments are legal, and why or why not?

    s1 = p1;
    s1 = p2;
    p1 = s1;

(See task 8.12 for context, from Chp8, Objects first with Java)

#### Exercise: 2
What has to change in the `NewsFeed` class when another `Post` subclass (for example, a class `EventPost`) is added? Why?

(See task 8.14 for context, in Chp8, Objects first with Java)

#### Exercise: 3
Exercise 8.15 Use the documentation of the Java standard class libraries to find out about the inheritance hierarchy of the collection classes. Draw a diagram showing the hierarchy.

(See task 8.15 for context, in Chp8, Objects first with Java)

#### Exercise: 4
Go back to the lab-classes project from Chapter 1. Add instructors to the project (every lab class can have many students and a single instructor). Use inheritance to avoid code duplication between students and instructors (both have a name, contact details, etc.).

(See task 8.16 for context, in Chp8, Objects first with Java)

#### Exercise: 5
Here are two algorithms that calculate x<sup>n</sup>, where x is a real number and n is a non-negative integer.

    double expIterativ(double x, int n) {
        double res = 1.0;

        for (int i = 0; i < n; i++)
            res *= x;
        return res;
    }

    double expRekursiv(double x, int n) {
        if (n <= 4)
            return expIterativ(x, n);

        return expRekursiv(x, n/2) *
               expRekursiv(x, (n + 1)/2);
    }

Argue the correctness of both algorithms. You could for example use a loop invariant, and a proof by induction, respectively.

Calculate the time-complexity as a function of n for both algorithms. Give the result using Big-O notation.
