import gulp from 'gulp';

import eslint from 'gulp-eslint';
import ava from 'gulp-ava';

gulp.task('eslint', () => {
  // ESLint ignores files with "node_modules" paths.
  // So, it's best to have gulp ignore the directory as well.
  // Also, Be sure to return the stream from the task;
  // Otherwise, the task may end before the stream has finished.
  const stream = gulp.src(['**/*.js', '!node_modules/**'])
    // eslint() attaches the lint output to the "eslint" property
    // of the file object so it can be used by other modules.
    .pipe(eslint())
    // eslint.format() outputs the lint results to the console.
    // Alternatively use eslint.formatEach() (see Docs).
    .pipe(eslint.format())
    // To have the process exit with an error code (1) on
    // lint error, return the stream and pipe to failAfterError last.
    .pipe(eslint.failAfterError());

  return stream;
});

gulp.task('ava-unit-test', () => {
  const stream = gulp.src(['**/*.spec.js', '!node_modules/**'])
    // `gulp-ava` needs filepaths, so you can't have any
    // plugins before it.
    .pipe(ava({ verbose: true }));

  return stream;
});

gulp.task('default', ['eslint', 'ava-unit-test']);
