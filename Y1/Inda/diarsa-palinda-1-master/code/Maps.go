package main

import (
	//"golang.org/x/tour/wc"	//det här programmet kan endast köras på a tour of go hemsidan då den importerar paket och funktioner.
	"strings"
)

func WordCount(s string) map[string]int {
	splitString := strings.Fields(s)
	mapcontents := make(map[string]int)

	for i := 0;i< len(splitString); i++ {
		mapcontents[splitString[i]]++
	}
	return mapcontents
}

func main() {
	wc.Test(WordCount)
}
