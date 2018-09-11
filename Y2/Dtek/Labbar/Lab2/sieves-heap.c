#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define COLUMNS 6

int printCounter = 0;

void print_numbers(int n){
  printf("%10d", n);
  //increment variable checking how many numbers has been printed
  printCounter++;

  //if 6 digits has been printed, create a new line
  if(printCounter % 6 == 0){
    printf("\n");
  }
}

  void print_sieves(int n){
  //compensate for 0 indexing
  int l = n+1;

  //initialize variable-sized array on the HEAP and fill it with 1's (true)
  int *pp = (int*)malloc(l*sizeof(int));
  for(int i = 0; i<l; i++){
    pp[i]=1;
  }

  //upper bound for outer-loop to minimize number of calculations
  int b = (int) sqrt(n);
  //Eratosthenes prime number algoritm
  for(int i = 2; i<=b; i++){

    if(pp[i] == 1){
      //set all multiples of a prime to false
      for(int j = (i*i); j<l; j+=i){
        pp[j] = 0;
      }
    }
  }

  //print out the indices with 1's (prime numbers)
  for(int i = 2; i<l; i++){
    if(pp[i]){
      print_numbers(i);
    }
  }
  printf("\n");

  //free memory
  free(pp);
}


int main(int argc, char *argv[]){


  if(argc == 2)
    print_sieves(atoi(argv[1]));
  else
    printf("Please state an interger number.\n");

  return 0;
}
