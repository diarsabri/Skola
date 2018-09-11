/*
 prime.c
 By David Broman.
 Last modified: 2015-09-15
 This file is in the public domain.
*/


#include <stdio.h>

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

int main(void){
  printf("%d\n", is_prime(11));  // 11 is a prime.      Should print 1.
  printf("%d\n", is_prime(383)); // 383 is a prime.     Should print 1.
  printf("%d\n", is_prime(987)); // 987 is not a prime. Should print 0.
}