### Deadline
This work should be completed before **Friday 4th November**.

### Instructions
To pass the assignment, you must do all of the tasks. Small errors are acceptable, but the most important thing is that you attempt all the tasks. If you get stuck, then help is available in the labs.

Please note that this is individual work. You may discuss the work with other students, but it is absolutely forbidden to submit copies of other student's work as your own. Please read and consider the [Code of Honour](https://www.kth.se/csc/utbildning/hederskodex) carefully.

### Submission
- All required work must be committed to your KTH Github Repository.
- A week-7 repository will be created for you automatically and it can be found [here](https://gits-15.sys.kth.se/inda-16).
- Please refer to the Kurswiki for help. Contact your teaching assistant or course leader if you get stuck.

### Homework
Study all sections from chapter 5 from the course textbook and be prepared to answer any of the exercises.

### Github Task:
You must complete the following exercises (n.b 5th Edition only here):

- 5.14 & 5.16 -- 5.20
- Using JavaDoc, write the class documentation for `RandomTester`
- 5.57 -- 5.60
- 5.62 & 5.64 -- 5.66
- 5.71

Please commit any written answers to the "docs" folder, and commit any Java code developed to the "code" folder of your KTH Github repo. Remember to push to KTH Github.

### RandomTester

#### Exercise 5.14
Write some code (in BlueJ) to test the generation of random numbers. To do this, create a new class called RandomTester. In class RandomTester, implement two methods: `printOneRandom` (which prints out one random number) and `printMultiRandom(int howMany)` (which has a parameter to specify how many numbers you want, and then prints out the appropriate number of random numbers).

#### Exercise 5.16
Write a method in your RandomTester class called `throwDice` that returns a random number between 1 and 6 (inclusive).

#### Exercise 5.17
Write a method called `getResponse` that randomly returns one of the strings "yes", "no", or "maybe".

#### Exercise 5.18
Extend your `getResponse` method so that it uses an ArrayList to store an arbitrary number of responses and randomly returns one of them.

#### Exercise 5.19
Add a method `randInRange` to your RandomTester class that takes a parameter max and generates a random number in the range 1 to max (inclusive).

#### Exercise 5.20
Add a method `randInRangeMinMax` to your RandomTester class that takes two parameters, min and max, and generates a random number in the range min to max (inclusive). Rewrite the body of the method you wrote for the previous exercise so that it now calls this new method to generate its result. Note that it should not be necessary to use a loop in this method.

#### Exercise 5.XX
Using JavaDoc, write the class documentation for RandomTester class. First, briefly review the **Format of a Doc Comment** and **Example of Doc Comments** sections from the [official documentation](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html) on JavaDoc from Oracle. Then within the RandomTester class, you must attempt to include the minimum JavaDoc requirements listed here:

The documentation of a class should at least include:
* the class name
* a comment describing the overall purpose and characteristics of the class
* a version number
* the author’s name (or authors’ names)
* documentation for each constructor and each method

The documentation for each constructor and method should include:
* the name of the method
* the return type
* the parameter names and types
* a description of the purpose and function of the method
* a description of each parameter
* a description of the value returned

Good Javadoc will become a **minimum requirement** in documentation of future assignments where you have created your own class, so absolute care must be taken to understand correct style of documentation.  Otherwise, you may be asked to **resubmit work if the documentation is of a poor standard**.

### Scribble Demo

#### Exercise 5.57
In class DrawDemo, create a new method named `drawTriangle`. This method should create a pen (as in the drawSquare method) and then draw a green triangle.

#### Exercise 5.58
Write a method `drawPentagon` that draws a pentagon.

#### Exercise 5.59
Write a method `drawPolygon(int n)` that draws a regular polygon with n sides (thus, n=3 draws a triangle, n=4 draws a square, etc.).

#### Exercise 5.60
Write a method called `spiral` that draws a spiral (see Figure 5.6).

### Bouncing Balls

#### Exercise 5.62
Change the method `bounce` in class BallDemo to let the user choose how many balls should be bouncing.

#### Exercise 5.64
Change the `bounce` method to place the balls randomly anywhere in the top half of the screen.

#### Exercise 5.65
Write a new method named `boxBounce`. This method draws a rectangle (the “box”) on screen and one or more balls inside the box. For the balls, do not use BouncingBall, but create a new class BoxBall that moves around inside the box, bouncing off the walls of the box so that the ball always stays inside. The initial position and speed of the ball should be random. The boxBounce method should have a parameter that specifies how many balls are in the box.

#### Exercise 5.66
Give the balls in boxBounce random colors.

### Star Wars

#### Exercise 5.71
There is a rumor circulating on the Internet that George Lucas (the creator of the Star Wars movies) uses a formula to create the names for the characters in his stories (Jar Jar Binks, ObiWan Kenobi, etc.). The formula—allegedly—is this:

```
Your Star Wars first name:
1 Take the first three letters of your last name.
2 Add to that the first two letters of your first name.

Your Star Wars last name:
1 Take the first two letters of your mother’s maiden name.
2 Add to this the first three letters of the name of the town or city where you were born.
```

And now your task: Create a new BlueJ project named star-wars. In it create a class named NameGenerator. This class should have a method named generateStarWarsName that generates a Star Wars name, following the method described above. You will need to find out about a method of the String class that generates a substring.

Feel free to extend this exercise as you please, and remember to document using proper JavaDoc from now onwards!
