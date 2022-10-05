package io.probono.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.probono.entity.Recipient;

@Repository
public interface RecipientRepository extends CrudRepository<Recipient, String> {

}
