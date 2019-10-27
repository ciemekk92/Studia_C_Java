#include <stdio.h>
#include <stdlib.h>
#define N 5
int main()
{

    int *tab, i;
    float suma, srednia;
    tab = (int*)malloc(N*sizeof(int));
    printf("Podaj 5 liczb\n");
    for (i = 0; i < N; i++)
    {
        scanf("%d", &tab[i]);
        suma += tab[i];
    }
    srednia = suma / i;
    printf("Srednia arytmetyczna wynosi %f", srednia);
    return 0;
}
