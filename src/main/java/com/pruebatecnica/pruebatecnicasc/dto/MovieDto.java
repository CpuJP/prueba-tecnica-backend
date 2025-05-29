package com.pruebatecnica.pruebatecnicasc.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto implements Serializable {

    @Valid
    @Min(1)
    @NotNull
    private Integer id;

    @Valid
    @NotBlank
    private String film;

    @Valid
    @NotBlank
    private String genre;

    @Valid
    @NotBlank
    private String studio;

    @Valid
    @Max(100)
    @Min(0)
    @NotNull
    private Integer score;

    @Valid
    @NotNull
    @Min(1)
    private Integer year;
}
