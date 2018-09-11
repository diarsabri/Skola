### Deadline
This work should be completed before **Friday 18th November**.

### Instructions
To pass the assignment, you must do all of the tasks. Small errors are acceptable, but the most important thing is that you attempt all the tasks. If you get stuck, then help is available in the labs.

Please note that this is individual work. You may discuss the work with other students, but it is absolutely forbidden to submit copies of other student's work as your own. Please read and consider the [Code of Honour](https://www.kth.se/csc/utbildning/hederskodex) carefully.

### Submission
- All required work must be committed to your KTH Github Repository.
- A week-9 repository will be created for you automatically and it can be found [here](https://gits-15.sys.kth.se/inda-16).
- Please refer to the Kurswiki for help. Contact your teaching assistant or course leader if you get stuck.

### Homework
Study all sections from chapter 7 from the course textbook and be prepared to answer any of the exercises.

### Github Task:
Complete the following exercises from Chapter 7:
- 7.13
- 7.15
- 7.16
- 7.18

You must create test classes for all the Auction project classes (see Chapter 4). The testing should be as comprehensive as you can make it, and as a minimum, for each method, you must consider:

- Positive and negative cases
- Appropriate boundary tests

Please commit any Java code developed to the src folder of your KTH Github repo. Remember to push to KTH Github.

### Testing Online Shop

#### Exercise 7.13
Create a test class for the `Comment` class in the online-shop-junit project.

#### Exercise 7.15
Create a test to check that `addComment` returns false when a comment from the same author already exists.

#### Exercise 7.16
Create a test that performs negative testing on the boundaries of the `rating` range. That is, test the values 0 and 6 as a rating (the values just outside the legal range). We expect these to return false, so assert false in the result dialog. You will notice that one of these actually (incorrectly) returns true. This is the bug we uncovered earlier in manual testing. Make sure that you assert false anyway. The assertion states the expected result, not the actual result.

#### Exercise 7.18
Create a test that checks whether the author and rating details are stored correctly after creation. Record separate tests that check whether the `upvote` and `downvote` methods work as expected.

### Testing Auction
In Chapter 4 the Auction project was introduced.  You should revisit this project, but now attempt to think how you could improve it by including unit testing.  The Auction project code has been included in your repository.

You must create a test class for all the Auction project classes.  The testing should be as comprehensive as you can make it, and as a minimum, for each method, you must consider:

- Positive and negative cases
- Appropriate boundary tests
