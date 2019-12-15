#include <stdio.h>
#include <stdlib.h>

int main()
{
    srand(time(NULL));
    int w, k, i, j, m, n;
    printf("Podaj liczbe wierszy\n");
    scanf("%d", &w);
    printf("Podaj liczbe kolumn\n");
    scanf("%d", &k);
    int tab[w][k];
    for (i = 0; i < w; i++)
    {
        for (j = 0; j < k; j++)
        {
            tab[i][j] = rand()%11;
        }
    }
    for (m = 0; m < w; m++)
    {
        for (n = 0; n < k; n++)
            printf("Wartosc komorki %d - %d wynosi %d\n", m+1, n+1, tab[m][n]);
    }
    return 0;
}
