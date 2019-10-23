#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i, liczba, nowaliczba;

    printf("Podaj liczbe:");
    scanf("%d", &liczba);
    if (liczba%2 == 0)
    {
        nowaliczba = liczba+1;
        for (i = 0; i < 50; i++)
        {
            printf("%d ", nowaliczba+(i*2));
        }
    } else
    {
        nowaliczba = liczba+2;
        for (i = 0; i < 50; i++)
        {
            printf("%d ", nowaliczba+(i*2));
        }
    }

    return 0;
}
