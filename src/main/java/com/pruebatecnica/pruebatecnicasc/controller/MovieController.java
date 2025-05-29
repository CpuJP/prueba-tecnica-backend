package com.pruebatecnica.pruebatecnicasc.controller;

import com.pruebatecnica.pruebatecnicasc.dto.MovieDto;
import com.pruebatecnica.pruebatecnicasc.exceptions.ApiErrorResponse;
import com.pruebatecnica.pruebatecnicasc.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Películas", description = "Operaciones REST sobre películas")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    @Operation(summary = "Obtener una película por su ID",
        description = "Devuelve una película específica mediante su identificador único.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Película encontrada",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MovieDto.class))),
            @ApiResponse(responseCode = "404", description = "Película no encontrada",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error Interno Servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
        })
    public ResponseEntity<MovieDto> findById(
            @Parameter(name = "id", description = "ID de la película a buscar",
                in = ParameterIn.QUERY, example = "1", schema = @Schema(type = "integer"))
            @Valid @RequestParam Integer id) {
        return movieService.findByID(id);
    }

    @GetMapping("/movies")
    @Operation(summary = "Listar películas",
        description = "Devuelve una lista limitada de películas ordenadas por Nombre.",
        responses = {
                @ApiResponse(responseCode = "200", description = "Lista de películas obtenida exitosamente",
                        content = @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = MovieDto.class)))),
                @ApiResponse(responseCode = "404", description = "Lista de películas vacía",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ApiErrorResponse.class))),
                @ApiResponse(responseCode = "500", description = "Error Interno Servidor",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ApiErrorResponse.class))),
        })
    public ResponseEntity<List<MovieDto>> getAll(
            @RequestParam(defaultValue = "10") Integer total,
            @RequestParam(defaultValue = "asc") String order
    ) {
        return movieService.getAll(total, order);
    }

    @PostMapping("/movie")
    @Operation(summary = "Agregar nueva película",
        description = "Crea una nueva película en el sistema",
        responses = {
            @ApiResponse(responseCode = "200", description = "Película creada exitosamente",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Datos inválidos de entrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "La película ya existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error Interno Servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
        })
    public ResponseEntity<?> addMovie(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                description = "Objeto película a crear",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = MovieDto.class),
                        examples = @ExampleObject(value = """
                              {
                                "id": 100,
                                "film": "Interstellar",
                                 "genre": "Sci-Fi",
                                "studio": "Warner Bros",
                                "score": 87,
                                "year": 2014
                            }
                                """)
                )
            )
            @Valid @RequestBody MovieDto movie
    ) {
        return movieService.addMovie(movie);
    }
}
