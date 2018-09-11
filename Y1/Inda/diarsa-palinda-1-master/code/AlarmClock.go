package main

import (
	"time"
	"fmt"
)

func Remind(text string, delay time.Duration) {

	for {
		x := time.Now().Format("15:04")		//http://stackoverflow.com/questions/20234104/how-to-format-current-time-using-a-yyyymmddhhmmss-format

		time.Sleep(delay)
		if delay == 3*time.Hour {
			fmt.Println(text+x + " : Dags att äta")
		} else if delay == 8*time.Hour {
			fmt.Println(text+x + " : Dags att arbeta")
		} else if delay == 24*time.Hour {
			fmt.Println(text+x + " : Dags att sova")
		}
	}
}

func main() {
	go Remind("Klockan är ", 3*time.Hour)
	go Remind("Klockan är ", 8*time.Hour)
	go Remind("Klockan är ", 24*time.Hour)

	select {}

}