package com.sinerji.api.desafio.repository;

import com.sinerji.api.desafio.domain.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {
    @Query("SELECT v FROM Venda v " +
            "JOIN v.funcionario f " +
            "WHERE f.id = :id " +
            "AND MONTH(f.dataContratacao) = :mes " +
            "AND YEAR(f.dataContratacao) = :ano " +
            "AND v.ano = :ano " +
            "AND v.mes = :mes")
    List<Venda> findByIdEMesEAnoComVendas(@Param("id") Integer id, @Param("mes") Integer mes, @Param("ano") Integer ano);

    @Query("SELECT v.funcionario, SUM(v.valorVenda) " +
            "FROM Venda v " +
            "WHERE v.funcionario.id IN :ids " +
            "AND v.mes = :mes " +
            "AND v.ano = :ano " +
            "GROUP BY v.funcionario")
    List<Object[]> somarValorVendasPorVendedorEMesEAno(@Param("ids") List<Integer> ids, @Param("mes") Integer mes, @Param("ano") Integer ano);

}
