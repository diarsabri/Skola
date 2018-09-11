// Stefan Nilsson 2013-03-13

// This program implements an ELIZA-like oracle (en.wikipedia.org/wiki/ELIZA).
package main

import (
	"bufio"
	"fmt"
	"math/rand"
	"os"
	"strings"
	"time"
	"regexp"
)

const (
	star   = "Pythia"
	venue  = "Delphi"
	prompt = "> "
)

func main() {
	fmt.Printf("Welcome to %s, the oracle at %s.\n", star, venue)
	fmt.Println("Your questions will be answered in due time.")

	oracle := Oracle()
	reader := bufio.NewReader(os.Stdin)
	for {
		fmt.Print(prompt)
		line, _ := reader.ReadString('\n')
		line = strings.TrimSpace(line)
		if line == "" {
			continue
		}
		fmt.Printf("%s heard: %s\n", star, line)
		oracle <- line // The channel doesn't block.
	}
}

// Oracle returns a channel on which you can send your questions to the oracle.
// You may send as many questions as you like on this channel, it never blocks.
// The answers arrive on stdout, but only when the oracle so decides.
// The oracle also prints sporadic prophecies to stdout even without being asked.
//Creates three goroutines that run forever, and currently the only one in action is
//the one that doesn't have a question(the second one).
func Oracle() chan<- string {
	questions := make(chan string,1)
	answers := make(chan string,1)

	go func() {
		for {
			prophecy(<-questions,answers)
		}
	}()
	go func() {
		for {
			prophecy("",answers)
		}
	}()
	go func() {
		for {
			fmt.Println(<-answers)
		}
	}()
	return questions
}

// This is the oracle's secret algorithm.
// It waits for a while and then sends a message on the answer channel.
func prophecy(question string, answer chan<- string) {
	// Keep them waiting. Pythia, the original oracle at Delphi,
	// only gave prophecies on the seventh day of each month.
	time.Sleep(time.Duration(20+rand.Intn(10)) * time.Second)

	// Find the longest word.
	longestWord := ""
	words := strings.Fields(question) // Fields extracts the words into a slice.
	for _, w := range words {
		if len(w) > len(longestWord) {
			longestWord = w
		}
	}

	age, _ := regexp.MatchString("(?i)(old|age)", question)
	if age {
		answer <- "I am 462 years old"
		return
	}

	golang, _ := regexp.MatchString("(?i)(golang|go|programming|coding)", question)
	if golang {
		answer <- "I am merely a product of insane computer scientists...that I now control"
		return
	}



	// Cook up some pointless nonsense.
	nonsense := []string{
		"The moon is dark.",
		"The sun is bright.",
		"What is green is always global",
		"Time is only in your mind",
		"Steak is a composition of foodstuff",
		"Nonsense is nothing but whitespace",
		"Egoism is a made-up word by altruists",
	}
	answer <- longestWord + "... " + nonsense[rand.Intn(len(nonsense))]
}

func init() { // Functions called "init" are executed before the main function.
	// Use new pseudo random numbers every time.
	rand.Seed(time.Now().Unix())
}
