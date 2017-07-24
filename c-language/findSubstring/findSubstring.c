int * findSubString(char * s, char ** words, int words_size, int * return_size) {
}

int main(int argc, char **argv) {
  char * s = "barfoothefoobarman";

  char ** words = ["foo", "bar"];
  int words_size = 2;

  int * return_size = [];

  findSubString(s, words, words_size, return_size);
}
