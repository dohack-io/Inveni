package de.inveni.backend.h2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Long> {
    @Query(value = "FROM Country WHERE id = :id")
    Country getById(long id);
    @Query(value = "FROM Country ")
    List<Country> getAll();
}
