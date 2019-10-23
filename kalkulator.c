#include <stdio.h>
#include <stdlib.h>

int main()
{
    int wybor;
    float liczba1, liczba2;
    printf("Wybierz dzialanie (1-4):\n");
    printf("1. Dodawanie\n");
    printf("2. Odejmowanie\n");
    printf("3. Mnozenie\n");
    printf("4. Dzielenie\n");
    scanf("%d", &wybor);
    printf("Podaj pierwsza liczbe:\n");
    scanf("%f", &liczba1);
    printf("Podaj druga liczbe:\n");
    scanf("%f", &liczba2);
    switch (wybor)
    {
    case 1:
        printf("Wynik to %.2f", liczba1 + liczba2);
        break;
    case 2:
        printf("Wynik to %.2f", liczba1 - liczba2);
        break;
    case 3:
        printf("Wynik to %.2f", liczba1 * liczba2);
        break;
    case 4:
        printf("Wynik to %.2f", liczba1 / liczba2);
        break;
    default:
        printf("Nie rozpoznano polecenia.");
    }

    return 0;
}
