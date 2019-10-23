#include <stdio.h>
#include <stdlib.h>

int main()
{
    int liczba, i, wynik = 1;
    printf("SILNIA\n");
    printf("Podaj liczbe:\n");
    scanf("%d", &liczba);
    for (i = 1; i<=liczba; i++)
    {
        wynik = wynik * i;
    }
    printf("Wynik to %d\n", wynik);
    system("pause");
    return 0;
}
