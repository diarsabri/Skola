### Deadline:
This work should be completed before **Friday 9th December**.  Woohoo! Last assignment of the year :)

### Instructions:
To pass the assignment, you must do all of the tasks. Small errors are acceptable, but the most important thing is that you attempt all the tasks. If you get stuck, then help is available in the labs.

Please note that this is individual work. You may discuss the work with other students, but it is absolutely forbidden to submit copies of other student's work as your own. Please read and consider the [Code of Honour](https://www.kth.se/csc/utbildning/hederskodex) carefully.

### Submission:
- All required work must be committed to your KTH Github Repository
- A week-12 repository will be created for you automatically and it can be found [here](https://gits-15.sys.kth.se/inda-16)
- Please refer to the Kurswiki for help, contact your teaching assistant, or course leader if you get stuck

### Exercise texts (newish!)
To make things easier for you, the questions for all hand-in exercises have been added to two files in the `docs/` folder of your repo (`solutions.tex` and `solutions.md`). It would be super awesome for us assistants if you just add your written solutions after the corresponding questions in **one** of those files, but if you have a different workflow that you prefer then that's still OK.

### Homework
Study all the following pages from the [course text](http://www.nada.kth.se/~snilsson/algoritmer):

- [Tidskomplexitet](http://www.nada.kth.se/~snilsson/algoritmer/tidskomplexitet/)
- [Asymptotisk notation](http://www.nada.kth.se/~snilsson/algoritmer/ordo-notation/)

### Github Task: Time Complexity

#### Exercise 1
To develop a sense of the relationship between problem size and an algorithm's order of growth, complete the following table of running times (we shall presume that time is the amount of milliseconds).

- Logarithmic = log<sub>2</sub>(n)
- Linear = n
- Linearithmic = n log n
- Quadratic = n<sup>2</sup>
- Cubic = n<sup>3</sup>
- Exponential = 2<sup>n</sup>
- Factorial = n!

| Size / complexity | log n  | n | n log n  | n<sup>2</sup> | n<sup>3</sup> | 2<sup>n</sup> | n! |
|-------------------|--------|---|----------|---------------|---------------|---------------|----|
| 1                 |        |   |          |               |               |               |    |
| 10                |        |   |          |               |               |               |    |
| 100               |        |   |          |               |               |               |    |
| 1000              |        |   |          |               |               |               |    |
| 10000             |        |   |          |               |               |               |    |
| 100000            |        |   |          |               |               |               |    |
| 1000000           |        |   |          |               |               |               |    |

Assume that logarithms are base 2 when you see log n. Give n with 1-2 significant digits.

n.b. Google Search can operate as a good calculator (e.g. try searching for `log2 8`)

#### Exercise 2
Let T(n) be the time in milliseconds (1/1000 second) to solve a given problem of size n with a certain algorithm. For each function T(n) and for each time t in the table, give the largest value of n, for which the algorithm can solve the problem in time t.

| T(n)          | 1 second | 1 minute | 1 hour | 1 day | 1 year |
| --------------|----------|----------|--------|-------|--------|
| log n         |          |          |        |       |        |
| n             |          |          |        |       |        |
| n log n       |          |          |        |       |        |
| n<sup>2</sup> |          |          |        |       |        |
| n<sup>3</sup> |          |          |        |       |        |
| 2<sup>n</sup> |          |          |        |       |        |
| n!            |          |          |        |       |        |

#### Exercise: 3
Arrange the functions in the following list in ascending order based on their rate of growth. That is, the function f(n) should come before the function g(n) in the list if f(n) is O(g(n)).

f1(n) = n<sup>1.5</sup>

f2(n) = 10<sup>n</sup>

f3(n) = n log n

f4(n) = n + 100

f5(n) = 2<sup>n</sup>

Which of the following statements are true? Justify your answer.

n (n + 1) / 2 = O(n<sup>3</sup>)

n (n + 1) / 2 = O(n<sup>2</sup>)

n (n + 1) / 2 = Θ(n<sup>3</sup>)

n (n + 1) / 2 = Ω(n)

#### Exercise: 4
Give a tight O-estimation, as a function of n, of the worst case time complexity of the following five loops:

```
Algorithm Loop1(n):
   a = 0
   for i = 1 to n
      a += i

Algorithm Loop2(n):
   b = 1
   for i = 1 to 4n
      b++

Algorithm Loop3(n):
   c = 1
   for i = 1 to n^2
      c--

Algorithm Loop4(n):
   d = 5
   for i = 1 to 3n
      for j = 1 to i
         d = d + j

Algorithm Loop5(n):
   e = 5
   for i = 1 to n^2
      for j = 1 to i
         e = e + j
```

Explain why (n+1)<sup>3</sup> is O(n<sup>3</sup>). Use the following definition: f(n) is O(g(n)) if there exists positive constants c and n<sub>0</sub> such that f(n) &le; c &times; g(n) for all n &ge; n<sub>0</sub>.

#### Exercise 5
The following algorithm reverses a collection.  Answer the following:

- What is the basic operation for this algorithm?
- Describe the time complexity of this algorithm

```
Reverse (A):
   Input: an array A storing n elements.
   Output: the same array with the elements in reversed order.
   for i = 1 to n-1
       x = A[i]
       for j = i down to 1
           A[j] = A[j-1]
       A[0] = x
```

Design a linear time O(n) algorithm to reverse a collection and implement two versions in Java, the first with arrays and the second with lists.  

Your implementation should count the number of basic operations to ensure that the complexity is O(n) for a given collection of size n. Please add your algorithms to `Reverse.java` and make sure it is tested.

#### Exercise: 6
Insertion Sort and Selection Sort have similar worst case runtime complexity O(n<sup>2</sup>).  Explain:

- How they differ in best case (a sorted collection) and mostly sorted case in terms of the runtime complexity of each algorithm, and
- Which should be preferred as a sorting algorithm with justification.
