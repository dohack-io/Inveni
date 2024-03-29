package de.inveni.backend.h2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {
    @Query(value = "FROM Property WHERE date BETWEEN :date01 AND :date02 " +
            "AND LOWER(description) LIKE LOWER(CONCAT('%', :description, '%')) OR LOWER(title) LIKE LOWER(CONCAT('%', :description, '%'))" +
            "AND NOT (finder.id = :userID)")
    List<Property> queryItemsWithoutFinder(long userID, long date01, long date02, String description);
    @Query(value = "FROM Property WHERE finder = :user")
    List<Property> queryItemsforFinder(User user);
    Property getById(long id);
    void deleteById(long id);
}
