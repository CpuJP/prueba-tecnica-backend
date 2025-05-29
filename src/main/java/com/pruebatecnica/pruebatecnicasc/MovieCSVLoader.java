package com.pruebatecnica.pruebatecnicasc;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.pruebatecnica.pruebatecnicasc.entities.Movie;
import com.pruebatecnica.pruebatecnicasc.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

@Component
@Slf4j
public class MovieCSVLoader implements CommandLineRunner {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieCSVLoader(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/movies.csv");
        if (inputStream == null) {
            throw new FileNotFoundException("No se encontró el archivo movies.csv en resources");
        }

        try (Reader reader = new InputStreamReader(inputStream);
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                try {
                    Movie movie = new Movie();
                    movie.setId(Integer.parseInt(line[0]));
                    movie.setFilm(line[1]);
                    movie.setGenre(line[2]);
                    movie.setStudio(line[3]);
                    movie.setScore(Integer.parseInt(line[4]));
                    movie.setYear(Integer.parseInt(line[5]));

                    movieRepository.save(movie);
                } catch (Exception e) {
                    System.err.println("Error procesando línea: " + Arrays.toString(line) + " -> " + e.getMessage());
                }
            }
            log.info("SE CARGÓ CORRECTAMENTE LA INFOR DEL ARCHIVO");
        }
    }
}
