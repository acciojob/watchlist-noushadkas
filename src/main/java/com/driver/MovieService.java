package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    //   1. Add a movie: POST /movies/add-movie
    public String addMovie(Movie movie){

        String ans = movieRepository.addMovie(movie);

        return ans;
    }


    //   2. Add a director: POST /movies/add-director
    public String addDirector(Director director){

        String ans = movieRepository.addDirector(director);

        return ans;
    }

    //   3.  Pair an existing movie and director: PUT /movies/add-movie-director-pair
    public String addMovieDirectorPair(String movieName, String directorName){

        String ans = movieRepository.addMovieDirectorPair(movieName, directorName);

        return ans;
    }

    //   4.  Get Movie by movie name: GET /movies/get-movie-by-name/{name}
    public Movie getMovieByName(String name){

        List<Movie> movieList = movieRepository.getMovieList();

        Movie ans = null;
        for(Movie movie: movieList){

            if(movie.getName().equals(name)){
                ans = movie;
            }
        }
        return ans;
    }


    //   5.  Get Director by director name: GET /movies/get-director-by-name/{name}
    public Director getDirectorByName(String name){

        List<Director> directorList = movieRepository.getDirectorList();

        Director ans = null;
        for(Director director: directorList){

            if(director.getName().equals(name)){
                ans = director;
            }
        }

        return ans;
    }

    //   6.  Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
    public List<String> getMoviesByDirectorName(String name){

        List<String> list = movieRepository.getDirectorMovieList(name);

        return list;
    }

    //    7. Get List of all movies added: GET /movies/get-all-movies
    public List<String> findAllMovies(){

        List<Movie> movieList = movieRepository.getMovieList();

        List<String> movies = new ArrayList<>();

        for(Movie movie: movieList){
            movies.add(movie.getName());
        }

        return movies;
    }

    //   8.  Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
    public String deleteDirectorByName(String name){
        String ans = movieRepository.deleteDirectorAndMovies(name);
        return ans;
    }

    //   9.  Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
    public String deleteAllDirectors(){

        List<Director> directorList = movieRepository.getDirectorList();

        for(Director director: directorList){
            deleteDirectorByName(director.getName());
        }

        return "All the director and their movies has been deleted";
    }
}