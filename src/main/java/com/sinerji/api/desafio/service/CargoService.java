package com.sinerji.api.desafio.service;

import com.sinerji.api.desafio.domain.cargo.Cargo;
import com.sinerji.api.desafio.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    private CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Cargo salvarCargo(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public List<Cargo> buscarTodosCargos() {
        return cargoRepository.findAll();
    }

    public Optional<Cargo> buscarCargoPorId(Integer id) {
        return cargoRepository.findById(id);
    }

    public void deletarCargo(Integer id) {
        cargoRepository.deleteById(id);
    }





}
