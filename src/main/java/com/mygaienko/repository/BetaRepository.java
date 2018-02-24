package com.mygaienko.repository;

import com.mygaienko.model.BetaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetaRepository extends CrudRepository<BetaEntity, Long> {
}
