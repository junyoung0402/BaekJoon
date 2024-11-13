#include<stdio.h>

int main() {
	int enve,total_sugar = 0,count = 0,sugar =0;
	scanf("%d", &enve);
	for (int i = enve /3; i >= 0 ; i--) {
		sugar = enve - (5 * i);
		if (sugar % 3 == 0 && sugar >= 0) {
			total_sugar = i + sugar / 3;
			break;
		}
	}
	if (total_sugar == 0) {
		printf("-1");
	}
	else {
		printf("%d", total_sugar);
	}
}