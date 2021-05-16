package com.ebubekir;

import com.ebubekir.model.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FirstRatings {

    private static final String SAMPLE_CSV_FILE_PATH = "E:\\work\\recommendation-system-java\\src\\com\\ebubekir\\data\\ratedmovies_short.csv";

    public static void main(String[] args) throws IOException {

        ArrayList<Movie> movieList  = loadMovies(SAMPLE_CSV_FILE_PATH);

        for (Movie movie: movieList) {
            System.out.println("---------------");
            System.out.println("Id : " + movie.getId());
            System.out.println("Title : " + movie.getTitle());
            System.out.println("Year : " + movie.getYear());
            System.out.println("Country : " + movie.getCountry());
            System.out.println("---------------\n\n");
        }
    }

    private static ArrayList<Movie> loadMovies(String filename) throws IOException, NumberFormatException{

        ArrayList<Movie> list = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(filename));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader("id", "title", "year", "country","genre","director","minutes","poster")
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            List<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords.subList(1,csvRecords.size())) {
                    list.add(new Movie(csvRecord.get("id"),
                            csvRecord.get("title"),
                            Integer.parseInt(csvRecord.get("year").trim()),
                            csvRecord.get("country"),
                            csvRecord.get("genre"),
                            csvRecord.get("director"),
                            Integer.parseInt(csvRecord.get("minutes").trim()),
                            csvRecord.get("poster")));
                }

        } catch (NumberFormatException ex){
            System.out.println("There is something wrong with Integer.parseInt");
        }
        return list;
    }

}
