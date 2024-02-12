package com.sinerji.api.desafio.domain.cargo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private Double salarioBase;

    private Double salarioAnoServico;

    private Double porcentagemBeneficio;

    public Cargo(Integer id, String nome, Double salarioBase, Double salarioAnoServico, Double porcentagemBeneficio) {
        this.id = id;
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.salarioAnoServico = salarioAnoServico;
        this.porcentagemBeneficio = porcentagemBeneficio;
    }

    public Cargo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Double getSalarioAnoServico() {
        return salarioAnoServico;
    }

    public void setSalarioAnoServico(Double salarioAnoServico) {
        this.salarioAnoServico = salarioAnoServico;
    }

    public Double getPorcentagemBeneficio() {
        return porcentagemBeneficio;
    }

    public void setPorcentagemBeneficio(Double porcentagemBeneficio) {
        this.porcentagemBeneficio = porcentagemBeneficio;
    }
}
