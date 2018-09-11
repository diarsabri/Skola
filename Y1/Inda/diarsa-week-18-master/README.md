### Deadline:
This work should be completed before **Friday 24th Feburary**.

### Instructions:
To pass the assignment, you must do all of the tasks. Small errors are acceptable, but the most important thing is that you attempt all the tasks. If you get stuck, then help is available in the labs.

Please note that this is individual work. You may discuss the work with other students, but it is absolutely forbidden to submit copies of other student's work as your own. Please read and consider the [Code of Honour](https://www.kth.se/csc/utbildning/hederskodex) carefully.

### Submission:
* All required work must be committed to your KTH Github Repository
* A week-18 repository will be created for you automatically and it can be found [here](https://gits-15.sys.kth.se/inda-16)
* Please refer to the Kurswiki for help, contact your teaching assistant, or course leader if you get stuck

### Homework
Study the following course literature:

* [Graphs](http://www.nada.kth.se/~snilsson/algoritmer/grafer/)

### Task 1 - Exploring IDEs
In this week's assignment, it is beneficial to use an IDE to manage your work.  If you have not tried using an IDE yet, then this is a good opportunity to practice.  If you are already comfortable with using an IDE, then proceed to `Task 2`.

It is recommended that you use either [Eclipse](https://eclipse.org/) or [IntelliJ](https://www.jetbrains.com/idea/) to complete the following steps.  Here, I'll use Eclipse to clone this week's repo, but you are free to choose.

* Open Eclipse and select `File` > `Import` > `Git`
* Choose `Projects from Git` > `Clone URI`
* Use the SSH Clone URI from you week-18's repo
* Complete the authentication, accept the default options, and then `Import existing Eclipse Projects` and `finish`
* Browse the available files
* Edit the `README.md` file as a simple test
* In the Package Explorer, right click on the icon for README.md and select `Team` > `Commit`
* Add a description of your change and `Commit` to the local repo, or `Commit and Push` to KTH:Github.
* If you pushed, you can check your repo online to confirm everything works :)

There is a lot to learn with IDEs, but here are some tutorials if you would like more info:

* [Eclipse - "Create your first Java program"](http://www.vogella.com/tutorials/Eclipse/article.html#firstjava)
* [IntelliJ - "Creating and Running Your First Java Application"](https://www.jetbrains.com/idea/help/creating-and-running-your-first-java-application.html)

### Task 2 - Implement HashGraph
Review the code that has been provided in the Graph project.  Start by reading the `Graph` interface code and then move onto the `MatrixGraph` implementation and other related classes. Once you are familiar with the code, create a second implementation of the `Graph` interface using the `HashGraph` class provided.   This class should make use of proximity lists, which can be implemented using hash tables. There is some skeleton code provided to get you started with `HashGraph`, and there is a corresponding `TestHashGraph` to test your implementation.

n.b. You should not modify the code in `Graph`, `VertextIterator` or `VertexAction`

### Task 3 - Components of Random Graphs
Write a program called `RandomGraphGenerator` that:

1) Generates a graph of `n` nodes, and then randomly assign `n` edges to the nodes
2) Calculates the number of components in the graph using DFS
3) Calculates the largest component

As sample trace of output is given below:

	$ java RandomGraphGenerator 1000

	For a graph with 1000 nodes and 1000 randomly assigned edges:
	* Number of components: XX
	* Largest component: YY

To handle command line arguments (e.g. n being 1000 in my trace), read the guides here:

* [Command Line Arguments](https://docs.oracle.com/javase/tutorial/essential/environment/cmdLineArgs.html)
* [Execution Arguments in Eclipse](http://help.eclipse.org/mars/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Ftasks%2Ftasks-executionArgs.htm)

### Task 4 - Time Cost Analysis
Modify the program in `Task 4` to measure the running time of `MatrixGraph` and `HashGraph` for different sizes of `n`. A sample trace of output is given below:

	$ java RandomGraphGenerator 1000 matrix

	For a proximity matrix graph with 1000 nodes and 1000 randomly assigned edges:
	* Number of components: XX
	* Largest component: YY
	* Running time: ZZ ns

Complete the following table of time costs for different values of `n`, for both implementations.

| Size (n)   | MatrixGraph | HashGraph |
| ---------- | ----------- | --------- |
| 100        |1116436 	   |32580294   |
| 200        |2947908 	   |35460912   |
| 400        |5254216 	   |36530245   |
| 800        |7013131 	   |45022304   |
| 1600       |11721709	   |49919002   |
| 3200       |19625414	   |57207732   |
| 6400       |85426141	   |58091578   |

You should report time in nanoseconds using `System.nanoTime()`, for example:

	// setup: build graph
	long t0 = System.nanoTime();
	// experiment: analyse components of graph
	long t1 = System.nanoTime();
	long timecost = t1 - t0;

You may also want to repeat the tests using a loop and determine the average `timecost` for a more accurate result.

Based on the results you have found from empirical analysis, which implementation was faster? Explain why this is the case using time complexity.
