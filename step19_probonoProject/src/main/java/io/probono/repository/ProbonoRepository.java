package io.probono.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.probono.entity.Probono;

@Repository
public interface ProbonoRepository extends CrudRepository<Probono, String> {

}
