### Task 2 - Time Complexities for Data Structures
Calculate the average-case time complexity for the operations (Find, Insert and Remove) in the following data-structures:
Let n be the number of elements and present the solution in a table with three rows and five columns as shown below. As usual you should motivate your answers.

| Operation | Unsorted Array | Sorted Array | Unsorted SLL | Sorted SLL | Hashtable |
|-----------|----------------|--------------|--------------|------------|-----------|
| Find      |      O(n)      |     O(n)     |     O(n)     |    O(n)    |    O(1)   |
| Insert    |      O(1)      |     O(n)     |     O(1)     |    O(n)    |    O(1)   |
| Remove    |      O(n)      |     O(n)     |     O(n)     |    O(n)    |    O(1)   |

* Unsorted Array:

To find an element you have to traverse the whole list. To insert something, you just do it at the end of the list. To remove something, you have to go through the whole list.


* Sorted Array:

Answers are the same, except for the insertion where, because it's sorted, the element has to be placed at the right spot.


* Unsorted Singly Linked List:

Both find and remove have to go through the whole list to get the element. Insert only places it at the end or front.


* Sorted Singly Linked List:

Same as USS, except that it's sorted. So the insertion has to take that into consideration, therefore it has to go through the whole list.


* Hash Table (You can assume that the number of elements is equal to the size of the table):

All operations are constant because that's how a hashtable works.






### Task 3 - Dynamic Tables
The `ArrayList` in Java is a convenient wrapper to make the primitive arrays more flexible.  However, arrays are fixed in size at the point of creation, i.e. they have a certain `capacity`. This means there is a cost involved if we grow beyond the intial capacity by adding more elements than can be stored. But, the API for `Arraylist` states, "*The add operation runs in amortized constant time*".

Consider an `Arraylist` that grows from it's initial size of zero with a sequence of calls to `add(E e)`, to a size of 20 elements.

	ArrayList list = new ArrayList();

	// Adding elements
	for(int i = 0; i<20; i++) {
		list.add(new Object());
	}

Answer the following:

* What is the initial capacity of an `ArrayList`'s internal array?

A: 10

* At what size does the internal array grow, and by how much?

A: It grows when it reaches the maximum capacity that it is at. For example, 11 from the beginning. It grows by newCapacity =  (oldCapacity * 2)/3 +1

* Explain what really happens by the term "grow" in this context?

A: More elements can be placed.

* What is the capacity of the internal array once 20 elements have been added?

A: Initial: 10 --> (10*2)/3 +1 = 16 --> (16*2)/3 +1 = 25

* If objects were removed, would the size of the internal array change also?

A: When an element gets removed, the whole array is "pushed" to the left by one position.

* What is the worst, average, and best-case time complexity of the `add(E e)` method of `Arraylist`?

A: It's always O(1) because it always just places the element at the end.
