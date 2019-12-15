#include <stdio.h>
#include <stdlib.h>
#define N 5

int main()
{
    srand(time(NULL));
    int *tab, i, j;
    tab = (int*)malloc(N*sizeof(int));
    // wypelnienie tablicy
    for (i = 0; i < N; i++)
       {
         tab[i] = rand()%11;
         printf("%d\n", tab[i]);
       }



    int ile_parzystych()
    {
        int k, ilosc = 0;
        for (k = 0; k < N; k++)
        {
            if (tab[k]%2 == 0)
                ilosc++;
        }
        return ilosc;
    }
    printf("Ilosc liczb parzystych to %d", ile_parzystych());

    return 0;
}
