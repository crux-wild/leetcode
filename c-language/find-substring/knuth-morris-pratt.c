#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include "knuth-morris-pratt.h"

char getPointCharOfOffset(KmpMatcher * kmpMatcher, int offset, int point) {
  char * pattern = kmpMatcher->pattern;
  int position = offset - point;
  char pointChar = pattern[position];

  return pointChar;
}

bool isEqualOfTwoChar(KmpMatcher * kmpMatcher, int offset1, int offset2,
  int point) {

  char char1 = getPointCharOfOffset(kmpMatcher, offset1, point);
  char char2 = getPointCharOfOffset(kmpMatcher, offset2, point);

  if (char1 == char2) {
    return true;
  } else {
    return false;
  }
}

bool isPatternMatchOfOffsets(KmpMatcher * kmpMatcher, int offset1, int offset2) {
  for (int point = 0; point < offset1; point++) {
    if (isEqualOfTwoChar(kmpMatcher, offset1, offset2, point) == false) {
      return false;
    }
  }

  return true;
}

bool initPrefixFunction(KmpMatcher * kmpMatcher) {
  size_t length = kmpMatcher->patternLength;
  size_t size = length * sizeof(int);
  int * prefixFunction = NULL;
  int k = 0;

  if ((kmpMatcher->prefixFunction = malloc(size)) == NULL) {
    // @TODO Error handling
    return false;
  }

  // Fill in the default pefixFunction
  prefixFunction = kmpMatcher->prefixFunction;
  prefixFunction[0] = 0;

  // Traverse the pattern text
  for (int q = 1; q < length; q++) {
    while (k > 0 && (isPatternMatchOfOffsets(kmpMatcher, k + 1, q) == false)) {
      k = prefixFunction[k];
    }

    if (isPatternMatchOfOffsets(kmpMatcher, k + 1, q) == true) {
      k = k + 1;
    }

    prefixFunction[q] = k;
  }

  return true;
}

KmpMatcher * createKmpMatcher(char * text, char * pattern) {
  KmpMatcher * kmpMatcher;

  if ((kmpMatcher = malloc(sizeof(KmpMatcher))) == NULL) {
    // @TODO Error handling
    return NULL;
  }

  kmpMatcher->text = text;
  kmpMatcher->pattern = pattern;
  kmpMatcher->patternLength = strlen(pattern);

  if (initPrefixFunction(kmpMatcher) == false) {
    // @TODO Error handling
  }

  return kmpMatcher;
}
