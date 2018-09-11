package main

import (
	"fmt"
	"math"
)

func Sqrt(x float64) float64 {
	z := 1.0
	for k := 0; k < 10; k++ {
		z = z - (z*z-x)/(2*z)
	}
	return z

}

func main() {
	fmt.Println(Sqrt(2))
	fmt.Println(math.Sqrt(2))

	fmt.Println(Sqrt(3))
	fmt.Println(math.Sqrt(3))
}