//step 1. Calculate Fatorial
//step 2. 인수 5의 갯수가 count의 최종 값이다.
//step 3. 1~num까지 모두 5로 나누어 떨어질떄까지 나누기
//step 3-1. 나누어 떨어질때마다 count+1
#include<stdio.h>
int main()
{
	int num,count = 0,temp;
	scanf("%d", &num);
	for (int i = 1; i <= num; i++) {
		temp = i;
		while (temp) {
			if (temp % 5 == 0) {
				count++;
				temp = temp / 5;
			}
			else {
				break;
			}
		}
	}
	printf("%d", count);
}	