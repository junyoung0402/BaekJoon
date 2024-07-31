#include<stdio.h>
#include<math.h>

int main() 
{
	int a,b,c,s_b,size=0;
	scanf("%d %d %d", &a, &b, &c);
	s_b = b;
	while (s_b != 0) {
		s_b /= 10;
		size++;
	}
	printf("%d\n", a + b - c);
	printf("%d", a * (int)pow(10, size)+ b - c);
}