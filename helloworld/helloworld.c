/*
 * this is a simple demo program written in C
 * to compile user 'make' from a terminal
 */
#include <stdio.h>
int main(int argc, char **argv) {
	// output hello world to terminal
	printf("hello world!\n");
	// output additional arguments passed to hello world program
	for (int i = 0; i < argc; i++) {
		printf("%d %s\n", i, argv[i]);
	}
}
