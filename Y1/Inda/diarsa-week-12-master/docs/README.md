# Assignment 12

#### Exercise 1

|Size / complexity |log n | n     |n log n |n<sup>2</sup>|n<sup>3</sup>      |2<sup>n</sup> |n!            |
|------------------|------|-------|--------|-------------|-------------------|--------------|--------------|
|1                 |0     |1      |0       |1            |1                  |2             |1             |
|10                |3.32  |10     |33      |100          |1000               |1024          |3628800       |
|100               |6.64  |100    |664     |10000        |1000000            |1.3*10^30     |9.3*10^157    |
|1000              |9.97  |1000   |9965    |1000000      |1000000000         |1.1*10^301    |4.0*10^2567   |
|10000             |13.29 |1000   |132877  |100000000    |1000000000000      |2.0*10^3010   |2.8*10^35659  |
|100000            |16.61 |100000 |1660964 |10000000000  |1000000000000000   |1.0*10^30101  |2.8*10^456573 |
|1000000           |19.93 |1000000|19931568|1000000000000|1000000000000000000|1.0*10^301030 |8.3*10^5565708|

#### Exercise 2

|T(n)          |1 second        |1 minute         |1 hour             |1 day               |1 year                 |
|--------------|----------------|-----------------|-------------------|--------------------|-----------------------|
|log n         |2<sup>1000</sup>|2<sup>60000</sup>|2<sup>3600000</sup>|2<sup>86400000</sup>|2<sup>31536000000</sup>|
|n             |1000            |60000            |3600000            |86400000            |31536000000            |
|n log n       |140             |4895             |5193702            |124648852           |45496830809            |
|n<sup>2</sup> |32              |245              |1897               |9295                |177584                 |
|n<sup>3</sup> |10              |39               |153                |442                 |3159                   |
|2<sup>n</sup> |10              |16               |22                 |26                  |35                     |
|n!            |6.2             |8.2              |10                 |11.3                |13.6                   |

#### Exercise 3

Arrange the functions in the following list in ascending order based on their rate of growth.
That is, the function f(n) should come before the function g(n) in the list if f(n) is O(g(n)).

f4(n) = n + 100

f3(n) = n log n

f1(n) = n<sup>1.5</sup>

f5(n) = 2<sup>n</sup>

f2(n) = 10<sup>n</sup>

Which of the following statements are true? Justify your answer.

The following are true:

n (n + 1) / 2 = O(n<sup>2</sup>)  (I)

n (n + 1) / 2 = Ω(n)              (II)

Explanation: The (I) one is true because the least possible runtime is n^2 times.
The (II) one is also true because the best case scenario is still n^2 times.

#### Exercise 4

Give a tight O-estimation, as a function of n, of the worst case time complexity of the following five loops:

* Loop1: This first loop has the tight O-estimation of O(n) because it loops once for each n in the collection.
* Loop2: This second loop has the tight O-estimation of O(n) because we don't care about constants in our growth.
* Loop3: This third loop has the tight O-estimation of O(n^2).
* Loop4: This fourth loop has the tight O-estimation of O(n^3) because it loops first once over n, then a second time for j over n
and then a third time for d. All of these have to be multiplied, so 3n*3n*3n=27n^3 -> O(n^3).
* Loop5: The same principle as before, except now its n^2 three times, so the big O is O(n^6).

Explain why (n+1)<sup>3</sup> is O(n<sup>3</sup>):

(n+1)^3=n^3+3n^2+3n+1 -> The highest order is n^3, and since the constant (3) doesn't matter -> big O is O(n^3).

#### Exercise 5

The following algorithm reverses a collection.  Answer the following:

* What is the basic operation for this algorithm?

There are two operations, the outer for loop and the inner for loop.

* Describe the time complexity of this algorithm?

The first for loop is called n times, the inner for loop is called j times for each i in n.
This means that it is: n(i(j)). And so; O(n^3)

#### Exercise 6

Insertion Sort and Selection Sort have similar worst case runtime complexity O(n<sup>2</sup>).  Explain:

* How they differ in best case (a sorted collection) and mostly sorted case in terms of the runtime complexity of each algorithm, and

* Which should be preferred as a sorting algorithm with justification.

With selection sort, each element has to be checked if it's in the correct position for every swap.
While insertion sort only iterates over the collection once, and continues from thereon after for each swap.
I would therefore say that insertion sort is the preferred algorithm.
