#include<stdio.h>

int main()
{
	int hour, min;
	scanf("%d %d", &hour, &min);
	if (min < 45) {
		hour--;
		if (hour == -1) {
			hour = 23;
		}
		min = 45 - min;
		min = 60 - min;
		printf("%d %d", hour, min);
		
	}
	else if (min >= 45) {
		min = min - 45;
		printf("%d %d", hour, min);
	}
}