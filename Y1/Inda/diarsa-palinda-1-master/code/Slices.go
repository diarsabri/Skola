package main

import (
	//"golang.org/x/tour/pic"	//det här programmet kan endast köras på a tour of go hemsidan då den importerar paket och funktioner.
	"math"
)

func Pic(dx, dy int) [][]uint8 {
	arrays := make([][]uint8,dy)
	for i := 0; i<dy;i++ {
		arrays[i] = make([]uint8, dx)
		for j := 0; j<dx; j++ {
			x := math.Pi
			f := int(x)
			arrays[i][j] = uint8((i*j)+f)
		}
	}
	return arrays
}


func main() {
	pic.Show(Pic)
}