typedef struct KmpMatcher {
  int * prefixFunction;
  char * text;
  char * pattern;

  int (*release)(void * kmpMatcher);
} KmpMatcher;

KmpMatcher * createKmpMatcher();
int releaseKmpMatcher(KmpMatcher * kmpMatcher);
