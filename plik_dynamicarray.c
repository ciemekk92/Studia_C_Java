#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void tablica_do_pliku (int w, int k, int tab[][k], char* nazwa) {
        FILE* file=fopen(nazwa, "w");
        int i, j;
        printf("BB");
        for(i=0; i<w; i++){
                printf("AA");
            for(j=0; j<k; j++) {
                fprintf(file, "%d ", tab[i][j]);
                //printf("%d ", tab[i][j]);
                //printf("CC");
            }
        }
        fclose(file);
    }


int main()
{
    srand(time(NULL));
    int tablica[3][3];
    /*int** tablica=(int**)malloc(sizeof(int*)*3);
    int i;
    for( i=0;i<3;i++)
        tablica[i]=(int*)malloc(sizeof(int)*3);*/
    int c, d;
    for (c = 0; c < 3; c++){
        for (d = 0; d < 3; d++) {
            tablica[c][d] = rand()%11;
            //printf("%d\n", tablica[c][d]);
        }
    }




    //tablica_do_pliku(tablica, 3,3, "plik.txt");
    tablica_do_pliku(3,3,tablica,  "plik.txt");


    return 0;
}
