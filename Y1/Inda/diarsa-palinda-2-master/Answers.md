### Task 2 - Many Senders; Many Receivers

The program [many2many.go](code/many2many.go) contains four producers that together send 32 strings over a channel.  At the other end there are two consumers that receive the strings.  Describe what happens, and explain why it happens, if you make the following changes in the program.  Try first to reason your way through, and then test your hypothesis by changing and running the program.

1.What happens if you switch the order of the statements `wgp.Wait()` and `close(ch)` in the end of the `main` function?

2.What happens if you move the `close(ch)` from the `main` function and instead close the channel in the end of the function `Produce`?

3.What happens if you remove the statement `close(ch)` completely?

4.What happens if you increase the number of consumers from 2 to 4?

5.Can you be sure that all strings are printed before the program stops?

Finally, modify the code by adding a new WaitGroup that waits for all consumers to finish.

1: What happens is that we don't wait for the channels to close so that means we can't send. Trying to send through a closed channel doesn't work.

2: Similarily to the first question, this leads to us trying to send through a closed channel. The for loop in the main method can't run since the channel is closed.

3: The channel never closes so the producers keep running.

4: We simply create two more channels that can read and print the strings

5: A channel only continues to the next value/input if the current value/input has been read. That means that all the strings are printed.