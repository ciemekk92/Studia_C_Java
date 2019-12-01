#include <stdio.h>
#include <stdlib.h>

int main()
{
    int fibonacciR(int n)
    {
        if (n < 1) return -1;
        if (n < 3) return 1;
        return fibonacciR(n-2)+fibonacciR(n-1);
    }

    int fibonacciI (int o)
    {
        if (o == 1 || o == 2)
            return 1;
        else if (o <= 0)
            return -1;
        else
        {
            int wyraz1 = 1, wyraz2 = 1, wyraz3, i;
            for (i = 0; i < o-2; i++)
            {
                wyraz3 = wyraz1 + wyraz2;
                wyraz1 = wyraz2;
                wyraz2 = wyraz3;
            }
            return wyraz3;
        }
    }

    printf("%d", fibonacciI(0));
}
