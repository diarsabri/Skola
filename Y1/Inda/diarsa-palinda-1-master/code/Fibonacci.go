package main

import "fmt"

// fibonacci is a function that returns
// a function that returns an int.
func fibonacci() func() int {
	x := 0
	y := 1
	return func() int {
		a := x
		x = y
		y = a+y

		return x
	}
}

func main() {
	f := fibonacci()
	for i := 0; i < 5; i++ {
		fmt.Println(f(),f(),f(),f(),f())
	}
}
