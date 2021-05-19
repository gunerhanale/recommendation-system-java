package com.ebubekir;
/**
 * Write a description of SecondRatings here.
 * 
 * @author Ebubekir
 * @version 16.05.2021
 */

import com.ebubekir.model.Movie;
import com.ebubekir.model.Rater;
import com.ebubekir.model.Rating;

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

    private double getAverageByID(String id, int minimalRaters){
        double average = 0.0;
        double sumRating = 0;
        int raters = 0;

        for (Rater rater : myRaters) {
            for (String movieId : rater.getItemsRated()) {
                if(movieId.equals(id)){
                    sumRating += rater.getRating(id);
                    raters += 1;
                }
            }
        }

        if(raters > minimalRaters){
            average = sumRating / raters;
         }

        return average;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){

        ArrayList<Rating> ratingInfo = new ArrayList<>();

        for (Movie movie : myMovies ) {
            double averageRating = getAverageByID(movie.getId(), minimalRaters);
            ratingInfo.add(new Rating(movie.getId(), averageRating));
        }

        return ratingInfo;
    }

    public String getTitle(String id){
        for (Movie movie: myMovies ) {
            if(movie.getId().equals(id)){
                return movie.getTitle();
            }
        }
        return "The ID was not found.";
    }

    private String getID(String title){
        for (Movie movie: myMovies) {
            if(movie.getTitle().equals(title)){
                return movie.getId();
            }
        }
        return "There is no such title inside data!";
    }

}
