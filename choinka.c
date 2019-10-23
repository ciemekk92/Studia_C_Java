#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i, n, szerokosc1, szerokosc2;
    printf("Podaj szerokosc podstawy:\n");
    scanf("%d", &n);

    if (n%2 == 0)
    {
        for (i = 0; i < n/2; i++)
        {
            szerokosc1 = (n-(i*2))/2;
            szerokosc2 = (n/(n/2)+i*2);
            printf("%*c", szerokosc1, ' ');
            printf("%0*d", szerokosc2, 0);
            printf("\n");
        }
    }
    else
    {
        for (i = 0; i < (n/2)+1; i++ )
        {
            szerokosc1 = ((n-(i*2))/2)+1;
            szerokosc2 = 1 + 2*i;
            printf("%*c", szerokosc1, ' ');
            printf("%0*d", szerokosc2, 0);
            printf("\n");
        }
    }
    return 0;
}
