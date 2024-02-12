package com.sinerji.api.desafio.repository;

import com.sinerji.api.desafio.domain.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    @Query("SELECT f FROM Funcionario f JOIN f.cargo c " +
            "WHERE c.porcentagemBeneficio > 0 AND f.id in ( :id )")
    List<Funcionario> findByIdEMesEAnoSomenteBeneficios(@Param("id") List<Integer> id);



}
