#include <stdio.h>
#include <stdlib.h>
#include "knuth-morris-pratt.c"

int main(int argc, char ** argv) {
  KmpMatcher * kmpMatcher = createKmpMatcher("", "ababaca");

  for (int i = 0; i < 5; i++) {
    printf("%d", kmpMatcher->prefixFunction[i]);
  }
}
