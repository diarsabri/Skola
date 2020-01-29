#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

int *tomat(int *a,int *b) {
    int *gurka = (int*)malloc(sizeof(int));
    *gurka = *a+*b;
    return gurka;

}



int main() {

    int a = 2;
    int b = 3;


    int *ret;

    *ret =*tomat(&a,&b);

    printf("%d",*ret);
    

    return 0 ;
}

