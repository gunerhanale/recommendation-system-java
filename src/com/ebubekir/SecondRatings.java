package com.ebubekir;
/**
 * Write a description of SecondRatings here.
 * 
 * @author Ebubekir
 * @version 16.05.2021
 */

import com.ebubekir.model.Movie;
import com.ebubekir.model.Rater;

import java.io.IOException;
import java.util.ArrayList;

public class SecondRatings {

    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings(String moviefile, String ratingsfile) throws IOException {
        myMovies = FirstRatings.loadMovies(moviefile);
        myRaters = FirstRatings.loadRaters(ratingsfile);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }


}
