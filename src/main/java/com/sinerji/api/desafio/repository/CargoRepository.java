package com.sinerji.api.desafio.repository;

import com.sinerji.api.desafio.domain.cargo.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}
