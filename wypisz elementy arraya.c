#include <stdio.h>
#include <stdlib.h>
#define N 5
int main()
{

    int *tab, i;
    tab = (int*)malloc(N*sizeof(int));
    for (i = 0; i < N; i++)
    {
        printf("Podaj liczbe\n");
        scanf("%d", &tab[i]);
    }
    printf("%d", tab[3]);

    free(tab);
    return 0;
}
