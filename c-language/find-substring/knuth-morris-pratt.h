typedef struct KmpMatcher {
  int * prefixFunction;
  int patternLength;
  char * text;
  char * pattern;
} KmpMatcher;

KmpMatcher * createKmpMatcher(char * text, char * pattern);
