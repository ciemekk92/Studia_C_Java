#include <stdio.h>
#include <stdlib.h>

int main()
{

    int x,y;
    for (x = 1; x < 9; x++) {
        for (y = 1; y <=9; y++) {
            printf("%d ", x*y);
        }
        printf("\n");
    }

    return 0;
}
