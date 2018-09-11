package main

import "fmt"

/* I want this program to print "Hello world!", but it doesn't work.
This program didn't even create a channel for the goroutine to pass through
 */
func main() {
	ch := make(chan string)
	go func() {
		ch <- "Hello world!"
		close(ch)
	}()
	fmt.Println(<-ch)
}
