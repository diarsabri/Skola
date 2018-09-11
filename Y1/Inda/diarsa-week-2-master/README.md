### Deadline
This work should be completed before **Friday 16th September** (n.b if you have a Thursday time slot, then this is your deadline).

### Instructions
To pass the assignment, you must do all of the tasks. Small errors are acceptable, but the most important thing is that you attempt all the tasks. If you get stuck, then help is available in the labs.

Please note that this is individual work. You may discuss the work with other students, but it is absolutely forbidden to submit copies of other student's work as your own. Please read and consider the [Code of Honour](https://www.kth.se/csc/utbildning/hederskodex) carefully.

### Submission
- All required work must be committed to your KTH Github Repository
- A week-2 repository will be created for you automatically and it can be found [here](https://gits-15.sys.kth.se/inda-16).
- Please refer to the Kurswiki for help. Contact your teaching assistant or course leader if you get stuck.

### Homework
Study sections 2.1 -- 2.12 of the course textbook. Please complete exercises 2.1 -- 2.42 from 5th or 6th edition (or 2.1 -- 2.38 from 4th edition) of the course textbook. You do not need to submit this homework, but you must be prepared to discuss it.

You can find the code for TicketMachine in the [bluej-projects repo](https://gits-15.sys.kth.se/inda-16/bluej-projects/tree/master/chapter02/naive-ticket-machine).

### Github Task:
Submit code for tasks 2.44 -- 2.45 from 5th edition (or 2.40 -- 2.42 from 4th edition) of course textbook in the form of a complete Java source code file called TicketMachine.java.

Please commit any written answers to the "docs" folder, and commit any Java code developed to the "code" folder of your KTH Github repo. Remember to push to KTH Github.

#### Exercise 2.44
Give the class two constructors. One should take a single parameter that specifies the `price`, and the other should take no parameter and set the `price` to be a default value of your choosing. Test your implementation by creating machines via the two different constructors.

#### Exercise 2.45
Implement a method `empty`, that simulates the effect of removing all money from the machine. This method should have a `void` return type, and its body should simply set the `total` field to zero. Does this method need to take any parameters? Test your method by creating a machine, inserting some money, printing some tickets, checking the total, and then emptying the machine. Is the `empty` method a mutator or an accessor?
