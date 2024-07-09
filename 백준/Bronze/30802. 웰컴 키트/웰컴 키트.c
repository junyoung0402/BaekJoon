#include<stdio.h>

int main()
{
	int n,size[7],p,t,total_t=0,total_p=0,one_p=0;
	scanf("%d", &n);
	for (int i = 0; i < 6; i++) {
		scanf("%d", &size[i]);
	}
	scanf("%d %d", &t, &p);
	for (int i = 0; i < 6; i++) {
		if (size[i]%t != 0) {
			if (size[i] / t == 0)
				total_t++;
			else if (size[i] / t != 0) {
				total_t = total_t+(size[i] / t + 1);
			}
		}
		if (size[i] % t == 0) {
			total_t = total_t + size[i] / t;
		}
	}
	total_p = n/p;
	one_p = n%p;
	printf("%d\n%d %d", total_t, total_p, one_p);
}