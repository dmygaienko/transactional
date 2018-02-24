package com.mygaienko.repository;

import com.mygaienko.model.AlfaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlfaRepository extends CrudRepository<AlfaEntity, Long> {
}
