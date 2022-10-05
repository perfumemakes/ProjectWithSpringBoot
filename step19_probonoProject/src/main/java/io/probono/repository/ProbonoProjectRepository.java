package io.probono.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.probono.entity.ProbonoProject;

@Repository
public interface ProbonoProjectRepository extends CrudRepository<ProbonoProject, Integer> {

}
