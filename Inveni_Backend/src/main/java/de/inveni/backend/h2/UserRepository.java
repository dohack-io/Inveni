package de.inveni.backend.h2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getById(long id);
    void deleteById(long id);
}
