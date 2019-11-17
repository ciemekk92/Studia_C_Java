#include <stdio.h>
#include <stdlib.h>

int main()
{
    srand(time(NULL));
    int i, j, w, m, n, o, p, suma = 0;
    printf("Podaj wymiar tablicy");
    scanf("%d", &w);

    int **tab2;
    tab2=(int**)malloc(w*sizeof(int*));
    for(i=0; i<w; i++)
    {
        tab2[i]=(double*)malloc(w*sizeof(double));
    }
    // wypelnienie tablicy
    for (o = 0; o < w; o++)
    {
        for (p = 0; p < w; p++)
        {
            tab2[o][p] = rand()%11;
        }
    }


    // obliczenie sumy
    for (m = 0; m < w; m++)
    {
        for (n = 0; n < w; n++)
        {
            suma += tab2[m][n];
        }
    }
    printf("Suma wynosi: %d", suma);

    for(j=0; j<w; j++)
        free(tab2[j]);

    free(tab2);

return 0;
}


