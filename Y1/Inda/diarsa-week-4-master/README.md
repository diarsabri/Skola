### Deadline
This work should be completed before Friday **30th September**.

### Instructions
To pass the assignment, you must do all of the tasks. Small errors are acceptable, but the most important thing is that you attempt all the tasks. If you get stuck, then help is available in the labs.

Please note that this is individual work. You may discuss the work with other students, but it is absolutely forbidden to submit copies of other student's work as your own. Please read and consider the [Code of Honour](https://www.kth.se/csc/utbildning/hederskodex) carefully.

### Submission
- All required work must be committed to your KTH Github Repository.
- A week-4 repository will be created for you automatically and it can be found [here](https://gits-15.sys.kth.se/inda-16).
- Please refer to the Kurswiki for help. Contact your teaching assistant or course leader if you get stuck.

### Homework
Study all of chapter 3 of the textbook and be prepared to discuss any of the exercises.

### Github Task:
n.b. Exercise numbers are the same for both the 4th and 5th edition of textbook.

- 3.1--3.3
- 3.9--3.12
- 3.21
- 3.26--3.27
- 3.30
- 3.31--3.32 (you must submit two versions of the 12-hour clock in separate .java files)
- 3.34

Please commit any written answers to the "docs" folder, and commit any Java code developed to the "code" folder of your KTH Github repo. Remember to push to KTH Github.

#### Exercise 3.1
Think again about the lab-classes project that we discussed in Chapter 1 and Chapter 2. Imagine that we create a `LabClass` object and three `Student` objects. We then enroll all three students in that lab. Try to draw a class diagram and an object diagram for that situation. Identify and explain the differences between them.

#### Exercise 3.2
At what time(s) can a class diagram change? How is it changed?

#### Exercise 3.3
At what time(s) can an object diagram change? How is it changed?

#### Exercise 3.9
Which of the following expressions return true?

```
! (4 < 5)
! false
(2 > 2) ││ ((4 == 4) && (1 < 0))
(2 > 2) ││ (4 == 4) && (1 < 0)
(34 != 33) && ! false
```

After writing your answers on paper, open the Code Pad in BlueJ and try it out. Check your answers.

#### Exercise 3.10
Write an expression using boolean variables `a` and `b` that evaluates to true when `a` and `b` are either both true or both false.

#### Exercise 3.11
Write an expression using boolean variables `a` and `b` that evaluates to true when only one of `a` and `b` is true, and that is false if `a` and `b` are both false or both true. (This is also called an exclusive or.)

#### Exercise 3.12
Consider the expression `(a && b)`. Write an equivalent expression (one that evaluates to true at exactly the same values for `a` and `b`) without using the `&&` operator.

#### Exercise 3.21
Rewrite the `increment` method without the modulo operator, using an if
statement. Which solution is better?

#### Exercise 3.26
Write the signature of a constructor that matches the following object creation instruction:

```java
new Editor("readme.txt", –1)
```

#### Exercise 3.27
Write Java statements that define a variable named `window` of type `Rectangle`, and then create a rectangle object and assign it to that variable. The rectangle constructor has two `int` parameters.

#### Exercise 3.30
Given a variable `Printer p1`; which currently holds a reference to a printer object, and two methods inside the Printer class with the headers

```java
public void print(String filename, boolean doubleSided)
public int getStatus(int delay)
```

write two possible calls to each of these methods.

#### Exercise 3.31 - Challenge exercise
Change the clock from a 24-hour clock to a 12-hour clock. Be careful: This is not as easy as it might at first seem. In a 12-hour clock, the hours after midnight and after noon are not shown as 00:30, but as 12:30. Thus, the minute display shows values from 0 to 59, while the hour display shows values from 1 to 12!

#### Exercise 3.32
There are (at least) two ways in which you can make a 12-hour clock. One possibility is to just store hour values from 1 to 12. On the other hand, you can simply leave the clock to work internally as a 24-hour clock but change the display string of the clock display to show 4:23 or 4.23pm when the internal value is 16:23. Implement both versions. Which option is easier? Which is better? Why?

#### Exercise 3.34
Draw an object diagram of the situation you have after creating a mail server and three mail clients. Object diagrams were discussed in Section 3.6.
