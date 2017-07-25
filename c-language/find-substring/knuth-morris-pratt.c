#include <string.h>
#include <stdlib.h>
#include "knuth-morris-pratt.h"

KmpMatcher * createKmpMatcher(char * text, char pattern) {
  KmpMatcher * kmpMatcher;

  if ((kmpMatcher = malloc(sizeof(KmpMatcher))) == NULL) {
    // @TODO Error handling
    return NULL;
  }

  kmpMatcher->initPrefixFunction = initPrefixFunction;

  return kmpMatcher;
}

int initPrefixFunction(KmpMatcher * kmpMatcher) {
  int * prefixFunction = kmpMatcher->prefixFunction;
  char * pattern = kmpMatcher->pattern;
  size_t length = strlen(pattern);
  size_t size = length * sizeof(int);
  int defaultPrefix[2] = { 0, 0 };

  if ((prefixFunction = malloc(size)) == NULL) {
    // @TODO Error handling
  }

  // Fill in the default pefixFunction
  memcpy(prefixFunction, defaultPrefix, 2 * sizeof(int));

  // Traverse the pattern text
  for (int i = 2; i < length; i ++) {
    // Find the maximum match
    for (int j = 1; j < i; j++) {
      if ((isMatchOfTwoOffsets(pattern, j, i) == -1)) {
      } else {
      }
    }
  }
}

void isMatchOfTwoOffsets(char * string, int offset1, int offset2) {
  return -1;
}
