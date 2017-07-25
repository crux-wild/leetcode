typedef struct KmpMatcher {
  int * prefixFunction;

  char * text;
  char * pattern;

  int (*initPrefixFunction) (struct KmpMatcher * kmpMatcher);
} KmpMatcher;

KmpMatcher * createKmpMatcher(char * text, char pattern);
int initPrefixFunction (KmpMatcher * kmpMatcher);
