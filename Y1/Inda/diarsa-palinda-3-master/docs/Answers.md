### Task 1 - Matching Behavior

Take a look at the program [matching.go](code/matching.go) from Chapter 9 in the [literature](http://www.nada.kth.se/~snilsson/concurrency/#Match). Explain what happens and why it happens if you make the following changes. Try first to reason about it, and then test your hypothesis by changing and running the program.

  * What happens if you remove the `go-command` from the `Seek` call in the `main` function?

    #What happen's in this case is nothing. More commonly though, when assigning a go function,
    the created thread doesn't wait for the function to finish. Program execution with a go call
    doesn't wait for the invoked function to complete.


  * What happens if you switch the declaration `wg := new(sync.WaitGroup`) to `var wg sync.WaitGroup` and the parameter `wg *sync.WaitGroup` to `wg sync.WaitGroup`?

    #The variable declaration doesn't change anything, but the pointer removal causes an error.
    Assigning a variable in a function can be done both ways.

  * What happens if you remove the buffer on the channel match?

    #A deadlock occurs because the match channel is waiting for the seek function to finsh
    while the seek function is waiting for the match channel.


  * What happens if you remove the default-case from the case-statement in the `main` function?

    #If there is no default case in a select statement then the statement blocks until
    one of the communications can proceed.

### Task 2 - Fractal Images

The file [julia.go](code/julia.go) contains a program that creates images and writes them to file. The program is pretty slow. Your assignment is to divide the computations so that they run in parallel on all available CPUs. Use the ideas from the example in [Chapter 10](http://www.nada.kth.se/~snilsson/concurrency/#Parallel) of the course literature.

You can also make changes to the program, such as using different functions and other colorings.

How many CPUs does you program use? How much faster is your parallel version?

    #v1: 24.2456858 s
    #v2: 4.8065206 s

### Task 3 - Weather station

The file [server.go](code/server.go) contains a program that simulates three independent weather stations that show the temperature at KTH. The results are published at the following addresses once the serves are operational:

  * `http://localhost:8080`
  * `http://localhost:8081`
  * `http://localhost:8082`

Start the program and try to visit the three addresses in your browser. You'll soon find that the three services aren't very reliable; they're pretty slow and sometimes you get no response at all. You might also get the error message "Service unavailable".

Your assignment is to write a client that simultaneously asks all servers and terminates the search as soon as one has responded with a correct temperature. The request should also terminate if no-one has answered within a given time. The file [client.go](code/client.go) contains a template from which you should build on.

  * Read through the code and start the client whilst the weather stations are operational
  * Implement the function `MultiGet`

---

Please commit any written answers or diagrams to the "docs" folder as a PDF (or Markdown) document, and commit any Java code developed to the "code" folder of your KTH Github repo. Remember to push to KTH Github.
