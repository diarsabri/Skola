# A Study of Quicksort

Diar Sabri
INDA Group 1
2016

## Characteristics and Complexity

Quicksort is a divide and conquer algorithm, meaning it splits the initial array up into smaller arrays and then 
performs the sorting on the "subarrays". The worst-case time complexity of quicksort is Θ(n​^2​​). If the pivot is 
always either the smallest or the biggest in the array that means that it will be partitioned all the way down to 
one element. The the worst-case running time will be Θ(n​^2​​). The average and best running time will be O(nlogn) since 
the array will (hopefully) be split in half each partitioning.

## Variations of Quicksort

The class QSFP is the main quicksort algorithm upon which all the other classes I've made are built on, so I'll explain 
how that class works and only explain the differences to the other classes. QSFP is a fixed pivot quicksort, meaning 
the selected pivot is always at the same position in the array. The method qsort assigns an integer that will be 
determined by the partitioning method. This integer (a) will then be evaluated with two if-statements, namely if a is 
bigger than the starting index and if a is smaller than the end index. If these are true, then we will make a recursive 
call to the same method (qsort) on them. 

The partition method first begins by assigning a pivot integer that will be 
the value of comparison. The first while loop checks so that if the element at the start is smaller than or equal to 
the pivot value, the start index gets incremented until it is equal to or smaller than the pivot. The second while 
loop decrements the end index as long as it is bigger than the pivot. Finally we get to the if-statement. This 
statement checks that if the start and end indexes either have met, or the end is bigger than the start, then we 
swap the elements at the end and starting indexes. Finally, we return the starting index (that is now at the start 
of the second part of the split array) and do the same if-checks in qsort. 

So basically, we assign a pivot value -> we
increment/decrement the elements that are smaller/bigger and then do this until the array is sorted. The only 
difference to QSFP and QSRP is the assigned pivot element, in QSRP it is randomized. On to the other two classes,
QSIFP and QSIRP. These classes implement quicksort with insertionsort cut-off point. So the difference is that we 
have an if-check that looks if the array is bigger than or equal to 9 elements, we use insertionsort to just go 
ahead and sort it instead of partitioning and doing more recursive calls. The insertionsort is taken from the one 
we did in this course some weeks ago, with some slight variation to allow the sort to take starting and end-points.

## Methodology

I've done two different tests, the first class is QSTest which just tests all the different implementations of quicksort
with different arrays. I test for sorted,equal,descending,and for zero's. If the quicksort works, we test the running 
time in the TimeTester class. We do this by using the Data class to create and store arrays for us and testing the time
it takes for all the different algorithms to sort these arrays. We use the StopWatch class to measure time.

## Results

| Test 1: Random Data |               |                          |                        |                                     |                                      |               |
| --------------------| ------------- | ------------------------ | -----------------------|-------------------------------------|--------------------------------------|---------------|
| Problem Size        | InsertionSort | QuickSort Fixed Pivot    | QuickSort Random Pivot | QuickSort InsertionSort Fixed Pivot | QuickSort InsertionSort Random Pivot | Arrays.sort † |
| 100                 | 72265         |           5700           |           8351         |                6601                 |                  11103               |     3491      |
| 1000                | 6499419       |           31509          |           65921        |                37013                |                  76273               |     17917     |
| 10000               | 27028980      |           312962         |           659994       |                351356               |                  737955              |     141556    |
| 100000              | 1488454060    |           3318202        |           6770888      |                3477116              |                  7555302             |     1421762   |
| 1000000             | 140993490634  |           36171507       |           70434298     |                37409195             |                  78565348            |     13721392  |


| Test 2: Sorted Data |               |                          |                        |                                     |                                      |               |
| --------------------| ------------- | ------------------------ | -----------------------|-------------------------------------|--------------------------------------|---------------|
| Problem Size        | InsertionSort | QuickSort Fixed Pivot    | QuickSort Random Pivot | QuickSort InsertionSort Fixed Pivot | QuickSort InsertionSort Random Pivot | Arrays.sort † |
| 100                 | 5851          |            4917          |            8660        |                 4983                |                   9865               |       1861    |
| 1000                | 4681          |            17595         |            55871       |                 21887               |                   61985              |       4739    |
| 10000               | 26624         |            154588        |            529008      |                 190108              |                   588949             |       28323   |
| 100000              | 274429        |            1770680       |            5554708     |                 2143475             |                   6240759            |       273533  |
| 1000000             | 2894677       |            20115420      |            58697505    |                 22825660            |                   64754715           |       2911402 |


| Test 3: Reversed Data |               |                          |                        |                                     |                                      |               |
| ----------------------| ------------- | ------------------------ | -----------------------|-------------------------------------|--------------------------------------|---------------|
| Problem Size          | InsertionSort | QuickSort Fixed Pivot    | QuickSort Random Pivot | QuickSort InsertionSort Fixed Pivot | QuickSort InsertionSort Random Pivot | Arrays.sort † |
| 100                   | 8484          |          4794            |           8488         |                5024                 |                 9677                 |      2267     |
| 1000                  | 283207        |          17000           |           55426        |                21806                |                 61863                |      4825     |
| 10000                 | 28119677      |          156827          |           530602       |                191554               |                 587788               |      32326    |
| 100000                | 2811047871    |          1792715         |           5589095      |                2155157              |                 6250689              |      310638   |
| 1000000               | 285727887495  |          20282929        |           58877514     |                22983818             |                 64917393             |      3259915  |


| Test 4: Equal Data  |               |                          |                        |                                     |                                      |               |
| --------------------| ------------- | ------------------------ | -----------------------|-------------------------------------|--------------------------------------|---------------|
| Problem Size        | InsertionSort | QuickSort Fixed Pivot    | QuickSort Random Pivot | QuickSort InsertionSort Fixed Pivot | QuickSort InsertionSort Random Pivot | Arrays.sort † |
| 100                 |     640       |          5002            |          13095         |           3756                      |              17647                   |     947       |
| 1000                |     2211      |          12861           |          53133         |           17641                     |              56369                   |     1854      |
| 10000               |     8607      |          129020          |          544048        |           180465                    |              562108                  |     10593     |
| 100000              |     74181     |          1446195         |          5153923       |           1762729                   |              5292973                 |     63432     |
| 1000000             |     646035    |          15692310        |          10512343      |           18877304                  |              10970434                |     745581    |


## Discussion

What we can conclude is that the insertionsort and Arrays own in-built sorting algorithms are at the ends of the spectrum. 
Insertionsort is incredibly slow compared to Arrays Quicksort with dual-pivots. Except for when the array only contains 
equal elements, then these two sorts are actually BOTH at the top speed-wise. But since that's never going to happen, 
insertionsort is the slowest and Arrays is the fastest one of them all. For some reason, the QSIRP class is one of the 
slowest quicksorts I made, I feel like it should be the fastest one so I'm not really sure what to conclude from that. 
The fault may lie in my InsertionSort algorithm, but since it passes the tests on Kattis and also both the insertionsort 
implementations (QSIFP,QSIRP) also are quicker on kattis than the other two, it may just be on my end (hardware-wise, 
or something else). Overall though, QuickSort is much faster than insertionsort and I believe that's the main thing to 
take away. Side-note: this project was really fun and engaging since we got to do a lot of "figuring-out" on our own, 
how the provided classes worked etc.