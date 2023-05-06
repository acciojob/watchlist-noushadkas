package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {


    HashMap<String, Movie> movieDb = new HashMap<>();

    HashMap<String, Director> directorDb = new HashMap<>();

    HashMap<String, List<String>> directorMovieDb = new HashMap<>();


    //   1. Add a movie: POST /movies/add-movie
    public String addMovie(Movie movie){

        String key = movie.getName();
        movieDb.put(key,movie);
        return "Movie added successfully";
    }


    //   2. Add a director: POST /movies/add-director
    public String addDirector(Director director){

        String key = director.getName();
        directorDb.put(key, director);

        return "Director added successfully";
    }

    //   3.  Pair an existing movie and director: PUT /movies/add-movie-director-pair
    public String addMovieDirectorPair(String movieName, String directorName){

        if(directorMovieDb.containsKey(directorName)){
            directorMovieDb.get(directorName).add(movieName);
        }
        else{
            List<String> list = new ArrayList<>();
            list.add(movieName);
            directorMovieDb.put(directorName,list);
        }

//        String key = directorName;
//        List<String> list = directorMovieDb.get(key);
//        list.add(movieName);
//        directorMovieDb.put(key, list);

        return "director and movie pair added successfully";
    }

    //   Get MovieList from movieDb.
    public List<Movie> getMovieList(){

        List<Movie> movies = new ArrayList<Movie>(movieDb.values());
        return movies;
    }

    //    Get DirectorList from directorDb
    public List<Director> getDirectorList(){

        List<Director> directors = new ArrayList<Director>(directorDb.values());
        return directors;
    }

    //    Get DirectorMovieList from directorMovieDb
    public List<String> getDirectorMovieList(String name){

        return directorMovieDb.get(name);

    }

    //    Delete dirctor and its movies from all the database
    public String deleteDirectorAndMovies(String name){

        List<String> movies = directorMovieDb.get(name);

        for(String movie: movies){
            movieDb.remove(movie);
        }

        directorDb.remove(name);

        directorMovieDb.remove(name);

        return "Director and its movies has been deleted";
    }
}