#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int main(void)
{
	int n, title = 666, count = 0;
	char* ptr;
	char stitle[1000] = "\0";
	scanf("%d", &n);
	while (1) {
		sprintf(stitle, "%d", title);//str로 형식 변환
		ptr = strstr(stitle, "666");//666이 있으면 address return, 없으면 null
		if (ptr != NULL) { // 1 => 666존재
			count++;
		}
		//원하는 만큼 count찾음
		if (count == n) {
			break;
		}
		else {
			title++;
		}
	}
	printf("%d", title);
	return 0;
}
