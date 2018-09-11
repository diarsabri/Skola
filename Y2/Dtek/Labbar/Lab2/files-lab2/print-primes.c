/*
 print-prime.c
 By David Broman.
 Last modified: 2015-09-15
 This file is in the public domain.
*/


#include <stdio.h>
#include <stdlib.h>

#define COLUMNS 6
int ccounter;
int numberOfNonPrimes;
int numberOfPrimes;

void print_number(int n){
  if(ccounter == 6) {
    printf("\n");
    printf("%10d ", n);
    ccounter = 0;
  } else {
    printf("%10d ", n);
  }
}

int is_prime(int n){
  if(n<=1) {
    return 0;
  } else if(n<=3) {
    return 1;
  } else if((n % 2 == 0)||(n % 3 == 0)) {
    return 0;
  }
  int i = 5;
  int j = 2;

  while (i*i <= n) {
    if(n % i == 0) {
      return 0;
    }
    i += j;
    j = 6-j;
  }
  return 1;
}

void print_primes(int n) {
  ccounter = 0;
  numberOfNonPrimes = 0;
  numberOfPrimes = 0;
  int i = 2;
  while(i<=n) {
    if(is_prime(i) == 1) {
      print_number(i);
      ccounter++;
      i++;
      numberOfPrimes++;
    } else {
      i++;
      numberOfNonPrimes++;
    }
  }
  float prctg,primes2f;
  primes2f = numberOfNonPrimes / 1.0;
  prctg = 100.0*(numberOfPrimes / primes2f);
  printf("\n");
  printf("%s" "%d" "%s" "%.2f" "%c", "Percentage of primes from 2-",n,": ",prctg,37 );
  printf("\n");
}

// 'argc' contains the number of program arguments, and
// 'argv' is an array of char pointers, where each
// char pointer points to a null-terminated string.
int main(int argc, char *argv[]){
  if(argc == 2)
    print_primes(atoi(argv[1]));
  else
    printf("Please state an integer number.\n");
  return 0;
}
