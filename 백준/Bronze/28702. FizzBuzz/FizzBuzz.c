/*
* step 1. 3개의 string을 입력받는다.
* step 2. 그 중 숫자는 int형 변수에 저장한다.  ( 숫자인지 아닌지 판단하는 방법?? ) 
*/
#include<stdio.h>
#include<stdlib.h>
int main()
{
	int num[4], count = 0, print_num;
	char fizz_str[4][9];
	for (int i = 0; i < 3; i++) {
		scanf("%s", fizz_str[i]);
		if ((int)fizz_str[i][0] <= 57) {
			num[count] = atoi(fizz_str[i]);
			count++;
		}
		else {
			num[count] = 0;
			count++;
		}
	}
	if (num[2] != 0) {
		print_num = num[2] + 1;
	}
	else if (num[1] != 0) {
		print_num = num[1] + 2;
	}
	else {
		print_num = num[0] + 3;
	}
	if (print_num % 15 == 0) {
		printf("FizzBuzz");
	}
	else if (print_num % 3 == 0) {
		printf("Fizz");
	}
	else if (print_num % 5 == 0) {
		printf("Buzz");
	}
	else
		printf("%d", print_num);
}