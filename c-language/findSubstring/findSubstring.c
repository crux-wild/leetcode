#include <stdio.h>
#include <stdlib.h>

/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
int * findSubString(char * const s, char ** const words, int const words_size,
  int * return_size) {
}

int main(int argc, char **argv) {
  char * s = "barfoothefoobarman";

  char * words[2] = { "foo2", "bar" };
  int words_size = 2;

  size_t int_size = sizeof(int);
  int * return_size = malloc(int_size * 2);

  findSubString(s, words, words_size, return_size);
}
