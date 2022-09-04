import gulp from 'gulp';
import dartSass from 'sass';
import gulpSass from 'gulp-sass';
const sass = gulpSass(dartSass);
import {deleteAsync} from 'del';
import purgecss from 'gulp-purgecss';

gulp.task('styles', () => {
	return gulp.src('src/main/resources/static/scss/style.scss').
		pipe(sass().on('error', sass.logError))
		.pipe(gulp.dest('src/main/resources/static/css/raw'));
});

gulp.task('clean', () => {
	return deleteAsync([
		'src/main/resources/static/css/style.css',
		'src/main/resources/static/css/raw/style.css'
	]);
});

gulp.task('purgecss', () => {
	return gulp.src('src/main/resources/static/css/raw/style.css')
		.pipe(purgecss({
			content: ['src/main/resources/templates/*.html', 'src/main/resources/templates/**/*.html', 'src/main/resources/static/js/*.js']
		}))
		.pipe(gulp.dest('src/main/resources/static/css'))
});

gulp.task('default', gulp.series(['clean', 'styles', 'purgecss']));