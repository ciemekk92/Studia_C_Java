#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXCHAR 1000

int main()
{
    FILE *fp;
    char str[MAXCHAR];
    char* filename = "plik.txt";

    fp = fopen(filename, "r");
    if (fp == NULL){
        printf("nie mozna otworzyc%s",filename);
        return 1;
    }
    while (fgets(str, MAXCHAR, fp) != NULL)
        printf("%s\n\n", str);
        printf("dlugosc tekstu to: %d\n\n", strlen(str));
    fclose(fp);
    return 0;
}
