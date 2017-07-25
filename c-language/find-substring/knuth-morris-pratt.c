#include <stdlib.h>
#include "knuth-morris-pratt.h"

KmpMatcher * createKmpMatcher(char * text, char * pattern) {
  KmpMatcher * matcher;

  if ((matcher = malloc(sizeof(KmpMatcher))) == NULL) {
    return NULL;
  }

  matcher->release = releaseKmpMatcher;

  return matcher;
}

int releaseKmpMatcher(KmpMatcher * kmpMatcher) {
  free(kmpMatcher);
}
