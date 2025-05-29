package com.pruebatecnica.pruebatecnicasc;

import com.pruebatecnica.pruebatecnicasc.dto.MovieDto;
import com.pruebatecnica.pruebatecnicasc.entities.Movie;
import com.pruebatecnica.pruebatecnicasc.exceptions.MovieConflictException;
import com.pruebatecnica.pruebatecnicasc.exceptions.MovieNotFoundException;
import com.pruebatecnica.pruebatecnicasc.repository.MovieRepository;
import com.pruebatecnica.pruebatecnicasc.services.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl movieService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ModelMapper modelMapper;

    private Movie movie;
    private MovieDto movieDto;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setId(90);
        movie.setFilm("Prueba Nombre");
        movie.setGenre("Prueba Genero");
        movie.setStudio("Prueba Estudio");
        movie.setScore(95);
        movie.setYear(2008);

        movieDto = new MovieDto(movie.getId(), movie.getFilm(), movie.getGenre(), movie.getStudio(), movie.getScore(), movie.getYear());
    }

    @Test
    void testFindByID_NotFound() {
        when(movieRepository.findById(80)).thenReturn(Optional.empty());
        assertThrows(MovieNotFoundException.class, () -> movieService.findByID(80));
    }

    @Test
    void testGetAllAsc_Success() {
        when(movieRepository.findMoviesAscLimit(1)).thenReturn(List.of(movie));
        when(modelMapper.map(any(Movie.class), eq(MovieDto.class))).thenReturn(movieDto);

        ResponseEntity<List<MovieDto>> response = movieService.getAll(1, "asc");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetAll_EmptyList() {
        when(movieRepository.findMoviesAscLimit(1)).thenReturn(List.of());

        assertThrows(MovieNotFoundException.class, () -> movieService.getAll(1, "asc"));
    }

    @Test
    void testAddMovie_Success() {
        when(movieRepository.existsById(90)).thenReturn(false);
        when(modelMapper.map(movieDto, Movie.class)).thenReturn(movie);

        ResponseEntity<?> response = movieService.addMovie(movieDto);

        assertEquals(200, response.getStatusCode().value());
        assertTrue(((Map<?, ?>) response.getBody()).containsKey("message"));
        verify(movieRepository).save(movie);
    }

    @Test
    void testAddMovie_Conflict() {
        when(movieRepository.existsById(90)).thenReturn(true);

        assertThrows(MovieConflictException.class, () -> movieService.addMovie(movieDto));
    }
}
