package com.ebubekir;

import java.io.IOException;

public class MovieRunnerAverage {

    private static final String TEST_MOVIE = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratedmovies_short.csv";
    private static final String PROD_MOVIE = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratedmoviesfull.csv";
    private static final String TEST_RATER = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratings_short.csv";
    private static final String PROD_RATER = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratings.csv";

    public static void main(String[] args) throws IOException {
        printAverageRatings();
    }


    private static void printAverageRatings() throws IOException {

        SecondRatings secondRatings = new SecondRatings(PROD_MOVIE, PROD_RATER);

        System.out.println("Number of movies : " + secondRatings.getMovieSize());
        System.out.println("Number of raters : " + secondRatings.getRaterSize());
    }
}
