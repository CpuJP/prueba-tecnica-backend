package com.pruebatecnica.pruebatecnicasc.services;

import com.pruebatecnica.pruebatecnicasc.dto.MovieDto;
import com.pruebatecnica.pruebatecnicasc.entities.Movie;
import com.pruebatecnica.pruebatecnicasc.exceptions.MovieBadRequestException;
import com.pruebatecnica.pruebatecnicasc.exceptions.MovieConflictException;
import com.pruebatecnica.pruebatecnicasc.exceptions.MovieNotFoundException;
import com.pruebatecnica.pruebatecnicasc.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "movieService")
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<MovieDto> findByID(Integer id) {
        Optional<Movie> movie = Optional.ofNullable(movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(String.format("La película con el ID %d no existe", id))));
        return ResponseEntity.ok(modelMapper.map(movie, MovieDto.class));
    }

    @Override
    public ResponseEntity<List<MovieDto>> getAll(Integer total, String order) {
        List<Movie> movies = "asc".equalsIgnoreCase(order)
                ? movieRepository.findMoviesAscLimit(total)
                : movieRepository.findMoviesDescLimit(total);
        if (!movies.isEmpty()) {
            List<MovieDto> movieDtos = movies.stream()
                    .map(movie -> modelMapper.map(movie, MovieDto.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(movieDtos, HttpStatus.OK);
        } else {
            throw new MovieNotFoundException("Lista de películas vacía");
        }
    }

    @Override
    public ResponseEntity<?> addMovie(MovieDto movie) {
        if (movie.getId() == null || movie.getFilm().isBlank() || movie.getGenre().isBlank() || movie.getStudio().isBlank() || movie.getScore() == null || movie.getYear() == null) {
            throw new MovieBadRequestException("Todos los campos son obligatorios");
        }
        if (movieRepository.existsById(movie.getId())) {
            throw new MovieConflictException(String.format("La película con el ID %d ya existe", movie.getId()));
        }
        else {
            Movie newMovie = modelMapper.map(movie, Movie.class);
            movieRepository.save(newMovie);
            return ResponseEntity.ok(Map.of("message", "La película fue creada con éxito"));
        }
    }
}
