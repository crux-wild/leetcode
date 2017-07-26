#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include "knuth-morris-pratt.h"

KmpMatcher * createKmpMatcher(char * text, char * pattern) {
  KmpMatcher * kmpMatcher;

  if ((kmpMatcher = malloc(sizeof(KmpMatcher))) == NULL) {
    // @TODO Error handling
    return NULL;
  }

  kmpMatcher->text = text;
  kmpMatcher->pattern = pattern;
  kmpMatcher->patternLength = strlen(pattern);

  kmpMatcher->initPrefixFunction = initPrefixFunction;

  return kmpMatcher;
}

bool initPrefixFunction(KmpMatcher * kmpMatcher) {
  int * prefixFunction = kmpMatcher->prefixFunction;
  char * pattern = kmpMatcher->pattern;
  size_t length = kmpMatcher->patternLength;
  size_t size = length * sizeof(int);
  int k = 0;

  if ((prefixFunction = malloc(size)) == NULL) {
    // @TODO Error handling
    return false;
  }

  // Fill in the default pefixFunction
  prefixFunction[1] = 0;

  // Traverse the pattern text
  for (int i = 1; i < length; i ++) {
    while (k > 0 && isPatternMatchOfOffsets(kmpMatcher, k+1, q)) {
      k = prefixFunction[k];
    }

    if (isPatternMatchOfOffsets(kmpMatcher, k+1, q)) {
      k = k + 1;
    }

    prefixFunction[q] = k;
  }

  return true;
}

bool isPatternMatchOfOffsets(KmpMatcher * kmpMatcher, int offset1, int offset2) {
  int length = kmpMatcher->patternLength;

  for (int point = 0; point < length ;i --) {
    if (isEqualOfTwoChar(pattern, offset1, offset2, point)) {
      return false;
    }
  }

  return true;
}

bool isEqualOfTwoChar(KmpMatcher * kmpMatcher, int offset1, int offset2,
    int point) {

  char char1 = getPointToCharOfOffset(kmpMathcer, offset1, point);
  char char2 = getPointToCharOfOffset(kmpMatcher, offset2, point);

  if (char1 == char2) {
    return true;
  } else {
    return false;
  }
}

char getPointToCharOfOffset(KmpMatcher kmpMacher, int offset, int point) {
  char pointToChar;

  return pointToChar;
}
