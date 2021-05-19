package com.ebubekir;

import com.ebubekir.model.Movie;
import com.ebubekir.model.Rater;
import com.ebubekir.model.Rating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieRunnerAverage {

    private static final String TEST_MOVIE = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratedmovies_short.csv";
    private static final String PROD_MOVIE = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratedmoviesfull.csv";
    private static final String TEST_RATER = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratings_short.csv";
    private static final String PROD_RATER = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratings.csv";

    public static void main(String[] args) throws IOException {

        getAverageRatingOneMovie();
        printAverageRatings(3);
        //printLoadMovies();
        //printLoadRaters();

    }


    private static void printAverageRatings(int minRaters) throws IOException {

        SecondRatings secondRatings = new SecondRatings(TEST_MOVIE, TEST_RATER);

//        System.out.println("-------TOTAL--------");
//        System.out.println("Number of movies : " + secondRatings.getMovieSize());
//        System.out.println("Number of raters : " + secondRatings.getRaterSize());
//        System.out.println("--------END---------\n");

        ArrayList<Rating> list = secondRatings.getAverageRatings(minRaters);

        Collections.sort(list, new Comparator<Rating>() {
            @Override
            public int compare(Rating c1, Rating c2) {
                return Double.compare(c1.getValue(), c2.getValue());
            }
        });

        for(Rating rating : list){
            if(rating.getValue() > 0){
                    System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
            }
        }
    }

    private static void getAverageRatingOneMovie() throws IOException {
        SecondRatings secondRatings = new SecondRatings(TEST_MOVIE, TEST_RATER);

        String title = "The Godfather";
        String movieId = secondRatings.getID(title);

        ArrayList<Rating> list = secondRatings.getAverageRatings(0);

        for (Rating rating: list ) {
            if(rating.getItem().equals(movieId)){
                System.out.println("The average of " + title + " : " + rating.getValue());
            }
        }

    }

    private static void printLoadMovies() throws IOException {

        ArrayList<Movie> movieList = FirstRatings.loadMovies(TEST_MOVIE);
        for (Movie movie : movieList) {
            System.out.println("---------------");
            System.out.println();
            System.out.println("Id : " + movie.getId());
            System.out.println("Title : " + movie.getTitle());
            System.out.println("Year : " + movie.getYear());
            System.out.println("Country : " + movie.getCountry());
            System.out.println("---------------\n");
        }
        System.out.println("The size of written Movie Data: " + movieList.size());
        System.out.println("\n\n");
    }

    private static void printLoadRaters() throws IOException {

        ArrayList<Rater> raterList = FirstRatings.loadRaters(TEST_RATER);
        for (Rater rater : raterList) {
            System.out.println("---------------");
            System.out.println("Rater Id : " + rater.getID());
            System.out.println("Number of Ratings : " + rater.numRatings());
            System.out.println("movie ID and the rating given : " + rater.getItemsRated());
            System.out.println("---------------\n");
        }
        System.out.println("The size of written Rater Data: " + raterList.size());
        System.out.println("\n\n");
    }

}
