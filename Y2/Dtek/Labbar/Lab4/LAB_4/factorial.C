#include <stdio.h>

int main()
{
    int a0 = 5;
    int a1 = a0-1;
    int a2 = 0;
    int a3 = 0;
    int v0 = 0;
    
    while(a1!=0){
        a2=a1;
        while(a2!=0){
            a3 = a3 + a0;
            a2--;
        }
        a0 = a3;
        a3 = 0;
        a1--;
    }
    v0=a0;

    printf("%d\n", a0);
}





5+5+5+5 = 20




a0 = 4
a1 = 3              

a2 = 3              a2 = 2          a2 = 1
while a2 != 0       
a3 = 4              a3 = 12         a3 = 24
a2 = 2              a2 = 1          a2 = 0
                                    _______
a3 = 8              a3 = 24         a0 = 24
a2 = 1              a2 = 0          a3 = 0
                    _______         a1 = 0
a3 = 12             a0 = 24
a2 = 0              a3 = 0
______              a1 = 1
a0 = 12
a3 = 0
a1 = 2