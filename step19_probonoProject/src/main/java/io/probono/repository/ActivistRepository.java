package io.probono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.probono.entity.Activist;

@Repository
public interface ActivistRepository extends JpaRepository<Activist, String> {

}
