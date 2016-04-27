#include<stdio.h>
#include<stdlib.h>
#define LEN 100

int get_next_day(int, int, int, int *, int *, int *);
int main(int argc, char * argv[])
{
    char line[LEN];
    FILE * in = fopen("in.txt", "r");
    FILE * out = fopen("out.txt", "w");
    int year;
    int month;
    int day;
    int *y = malloc(sizeof(int));
    int *m = malloc(sizeof(int));
    int *d = malloc(sizeof(int));
    while(fgets(line, LEN, in) != NULL){
        sscanf(line, "%d %d %d", &year, &month, &day);
        if(get_next_day(year, month, day, y, m, d)){
            fprintf(out, "%d %d %d, %d %d %d \n", year, month, day, *y, *m, *d);
        }
        else{
            fprintf(out, "%d %d %d, wrong input!\n", year, month, day);
        }
    }

    return 0;
}

int get_next_day(int year, int month, int day, int * y, int * m, int * d)
{
    *y = year;
    *m = month;
    *d = day;
    if(month <= 0 || month > 12 || day <= 0 || day >31)
    {
        return 0;
    }
    if(day == 31)
    {
        if(month==2||month==4||month==6||month==9||month==11)
        {
            return 0;
        }
        else
        {
            *d = 1;
            (*m)++;
            if(*m == 13)
            {
                *m = 1;
                (*y)++;
            }
        }
    }
    else if(day == 30)
    {
        if(month==2)
        {
            return 0;
        }
        else if(month==4||month==6||month==9||month==11)
        {
            *d = 1;
            (*m)++;
        }
        else
        {
            (*d)++;
        }
    }
    else if(day == 29 && month == 2)
    {
        if((year % 400 == 0)||(year % 100 != 0 && year % 4 == 0))
        {
            *m = 3;
            *d = 1;
        }
        else
        {
            return 0;
        }
    }
    else if(day == 28 && month ==2)
    {
        if((year % 400 == 0)||(year % 100 != 0 && year % 4 == 0))
        {
            (*d)++;
        }
        else
        {
            *m = 3;
            *d = 1;
        }
    }
    else
    {
        (*d)++;
    }
    // printf("%d %d %d", *y, *m, *d);
    // printf("\n");
    return 1;
}
