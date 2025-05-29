package com.pruebatecnica.pruebatecnicasc.services;

import com.pruebatecnica.pruebatecnicasc.dto.MovieDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MovieService {
    ResponseEntity<MovieDto> findByID(@RequestParam Integer id);

    ResponseEntity<List<MovieDto>> getAll(
            @RequestParam(defaultValue = "10") Integer total,
            @RequestParam(defaultValue = "asc") String order
    );

    ResponseEntity<?> addMovie(@RequestBody MovieDto movie);
}
