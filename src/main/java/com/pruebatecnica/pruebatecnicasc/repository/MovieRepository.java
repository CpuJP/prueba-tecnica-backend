package com.pruebatecnica.pruebatecnicasc.repository;

import com.pruebatecnica.pruebatecnicasc.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query(value = "SELECT * FROM movies ORDER BY film ASC LIMIT :total", nativeQuery = true)
    List<Movie> findMoviesAscLimit(@Param("total") int total);


    @Query(value = "SELECT * FROM movies ORDER BY film DESC LIMIT :total", nativeQuery = true)
    List<Movie> findMoviesDescLimit(@Param("total") int total);
}
