#include <stdio.h>
#include <stdlib.h>

int main()
{

    int *tab, i, N;
    float suma = 0, srednia;
    printf("Ile liczb?\n");
    scanf("%d",&N);
    tab = (int*)malloc(N*sizeof(int));
    printf("Podaj %d liczb\n",N);
    for (i = 0; i < N; i++)
    {
        scanf("%d", &tab[i]);
        suma += tab[i];
    }
    srednia = suma / i;
    printf("Srednia arytmetyczna wynosi %f", srednia);
    free(tab);
    return 0;
}
