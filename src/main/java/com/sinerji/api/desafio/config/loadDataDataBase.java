package com.sinerji.api.desafio.config;

import com.sinerji.api.desafio.domain.cargo.Cargo;
import com.sinerji.api.desafio.domain.funcionario.Funcionario;
import com.sinerji.api.desafio.domain.venda.Venda;
import com.sinerji.api.desafio.repository.CargoRepository;
import com.sinerji.api.desafio.repository.FuncionarioRepository;
import com.sinerji.api.desafio.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class loadDataDataBase implements CommandLineRunner {

    private FuncionarioRepository funcionarioRepository;
    private CargoRepository cargoRepository;
    private VendaRepository vendaRepository;

    @Autowired
    public loadDataDataBase(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, VendaRepository vendaRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.vendaRepository = vendaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataBase persistida:" + (cargoRepository.count() > 0 ? true : false));
        if (cargoRepository.count() <= 0) {


            // Cadastrar cargos
            Cargo secretario = new Cargo(1, "Secretário", 7000.00, 1000.00, 20.0);
            Cargo vendedor = new Cargo(2, "Vendedor", 12000.00, 1800.00, 30.0);
            Cargo gerente = new Cargo(3, "Gerente", 20000.00, 3000.00, 0.0);
            cargoRepository.saveAll(Arrays.asList(secretario, vendedor, gerente));

            // Cadastrar funcionários
            Funcionario jorge = new Funcionario(1, "Jorge Carvalho", secretario, new Date(118, 0, 1));
            Funcionario maria = new Funcionario(2, "Maria Souza", secretario, new Date(115, 11, 1));
            Funcionario ana = new Funcionario(3, "Ana Silva", vendedor, new Date(121, 11, 1));
            Funcionario joao = new Funcionario(4, "João Mendes", vendedor, new Date(121, 11, 1));
            Funcionario juliana = new Funcionario(5, "Juliana Alves", gerente, new Date(117, 6, 1));
            Funcionario bento = new Funcionario(6, "Bento Albino", gerente, new Date(114, 2, 1));
            funcionarioRepository.saveAll(Arrays.asList(jorge, maria, ana, joao, juliana, bento));

            // Cadastrar vendas
            Venda anaVenda1 = new Venda(1, ana, 12, 2021, 5200.00);
            Venda anaVenda2 = new Venda(2, ana, 1, 2022, 4000.00);
            Venda anaVenda3 = new Venda(3, ana, 2, 2022, 4200.00);
            Venda anaVenda4 = new Venda(4, ana, 3, 2022, 5850.00);
            Venda anaVenda5 = new Venda(5, ana, 4, 2022, 7000.00);
            Venda joaoVenda1 = new Venda(6, joao, 12, 2021, 3400.00);
            Venda joaoVenda2 = new Venda(7, joao, 1, 2022, 7700.00);
            Venda joaoVenda3 = new Venda(8, joao, 2, 2022, 5000.00);
            Venda joaoVenda4 = new Venda(9, joao, 3, 2022, 5900.00);
            Venda joaoVenda5 = new Venda(10, joao, 4, 2022, 6500.00);
            vendaRepository.saveAll(Arrays.asList(anaVenda1, anaVenda2, anaVenda3, anaVenda4, anaVenda5, joaoVenda1, joaoVenda2, joaoVenda3, joaoVenda4, joaoVenda5));
        }
    }
}
