package com.ebubekir;

import com.ebubekir.model.Movie;
import com.ebubekir.model.Rater;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstRatings {

    private static final String TEST_MOVIE = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratedmovies_short.csv";
    private static final String PROD_MOVIE = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratedmoviesfull.csv";
    private static final String TEST_RATER = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratings_short.csv";
    private static final String PROD_RATER = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratings.csv";

    public static void main(String[] args) throws IOException {

        ArrayList<Movie> movieList = loadMovies(TEST_MOVIE);
        for (Movie movie : movieList) {
            System.out.println("---------------");
            System.out.println();
            System.out.println("Id : " + movie.getId());
            System.out.println("Title : " + movie.getTitle());
            System.out.println("Year : " + movie.getYear());
            System.out.println("Country : " + movie.getCountry());
            System.out.println("---------------\n\n");
        }
        System.out.println("The size of written Movie Data: " + movieList.size());
        System.out.println("---------------\n\n");

        ArrayList<Rater> raterList = loadRaters(TEST_RATER);
        for (Rater rater : raterList) {
            System.out.println("Rater Id : " + rater.getID());
            System.out.println("Number of Ratings : " + rater.numRatings());
        }
        System.out.println("The size of written Rater Data: " + raterList.size());

    }

    private static ArrayList<Movie> loadMovies(String filename) throws IOException, NumberFormatException {

        ArrayList<Movie> list = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(filename));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader("id", "title", "year", "country", "genre", "director", "minutes", "poster")
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            List<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords.subList(1, csvRecords.size())) {
                list.add(new Movie(csvRecord.get("id"),
                        csvRecord.get("title"),
                        Integer.parseInt(csvRecord.get("year").trim()),
                        csvRecord.get("country"),
                        csvRecord.get("genre"),
                        csvRecord.get("director"),
                        Integer.parseInt(csvRecord.get("minutes").trim()),
                        csvRecord.get("poster")));
            }

        } catch (NumberFormatException ex) {
            System.out.println("There is something wrong with Integer.parseInt");
        }
        return list;
    }

    private static ArrayList<Rater> loadRaters(String filename) throws IOException {

        ArrayList<Rater> list = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(filename));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader("rater_id", "movie_id", "rating", "time")
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            List<CSVRecord> csvRecords = csvParser.getRecords();
            boolean existing = false;

            for (CSVRecord csvRecord : csvRecords.subList(1, csvRecords.size())) {
                if (!list.isEmpty()) {
                    for (Rater rater : list) {
                        if (rater.getID().equals(csvRecord.get("rater_id"))) {
                            rater.addRating(csvRecord.get("movie_id"), Double.parseDouble(csvRecord.get("rating")));
                            existing = true;
                        }
                    }
                    if (existing == false) {
                        Rater newRater = new Rater(csvRecord.get("rater_id"));
                        newRater.addRating(csvRecord.get("movie_id"), Double.parseDouble(csvRecord.get("rating")));
                        list.add(newRater);
                    }
                } else {
                    Rater newRater = new Rater(csvRecord.get("rater_id"));
                    newRater.addRating(csvRecord.get("movie_id"), Double.parseDouble(csvRecord.get("rating")));
                    list.add(newRater);
                }
                existing= false;
            }
        }
        return list;
    }


}
