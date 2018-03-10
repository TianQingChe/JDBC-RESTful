package com.jpa;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa.dao.movie.MovieDao;
import com.jpa.model.movie.Movie;

@SpringBootApplication
public class JdbcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcDemoApplication.class, args);
		MovieDao dao=MovieDao.getInstance();
//		List<Movie> movies=dao.findAllMovies();
//		Movie m1=dao.findMovieByImdb("1");
//		Movie bladeRunner=new Movie("tt0083658","Blade Runner","Do robots dream of electric sheep?","some.jpg");
//		dao.createMovie(bladeRunner);
//		dao.deleteMovie("1");
//		Movie titanic=new Movie("tt0120338","Titanic","A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.","https://images-na.ssl-images-amazon.com/images/M/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_SX300.jpg");
//		dao.createMovie(titanic);
		
//		dao.updateMovie("IMDBID",titanic);
	}
}
