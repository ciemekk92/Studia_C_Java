#include <stdio.h>
#include <stdlib.h>
#include <math.h>

float srednia_geom(char *nazwa_pliku, int k)
{
    FILE *file = fopen(nazwa_pliku, "r");

    if (file == NULL)
    {
        printf("Nie znaleziono pliku \n");
        return 0;
    }
    else
    {
        printf("Otwarto plik \n");
    }

    double iloczyn, srednia;
    double licznik;
    int liczba_test;
    licznik = 0;
    iloczyn = 1;

    while ((fscanf(file, "%d", &liczba_test)) != EOF)
    {
        if (liczba_test % k == 0)
        {
            iloczyn = iloczyn * liczba_test;
            licznik++;
            printf("%f %d %d\n", iloczyn, licznik, liczba_test);
        }
    }

    srednia = pow(iloczyn, 1 / licznik);
    return srednia;
    fclose(file);
}

int main()
{
    printf("Srednia geometryczna liczb z pliku to %f", srednia_geom("liczby.txt", 5));
    return 0;
}
