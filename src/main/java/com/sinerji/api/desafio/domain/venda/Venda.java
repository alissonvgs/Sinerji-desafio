package com.sinerji.api.desafio.domain.venda;

import com.sinerji.api.desafio.domain.funcionario.Funcionario;
import jakarta.persistence.*;

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    private Integer mes;

    private Integer ano;

    private Double valorVenda;

    public Venda(Integer id, Funcionario funcionario, Integer mes, Integer ano, Double valorVenda) {
        this.id = id;
        this.funcionario = funcionario;
        this.mes = mes;
        this.ano = ano;
        this.valorVenda = valorVenda;
    }

    public Venda() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }
}