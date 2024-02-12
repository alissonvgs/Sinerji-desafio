package com.sinerji.api.desafio.service;

import com.sinerji.api.desafio.domain.cargo.TipoCargo;
import com.sinerji.api.desafio.domain.funcionario.Funcionario;
import com.sinerji.api.desafio.domain.funcionario.FuncionarioSalarioDto;
import com.sinerji.api.desafio.domain.venda.Venda;
import com.sinerji.api.desafio.domain.venda.VendaDto;
import com.sinerji.api.desafio.repository.FuncionarioRepository;
import com.sinerji.api.desafio.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    private VendaRepository vendaRepository;


    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository, VendaRepository vendaRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.vendaRepository = vendaRepository;
    }

    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> buscarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Integer id) {
        return funcionarioRepository.findById(id);
    }

    public void deletarFuncionario(Integer id) {
        funcionarioRepository.deleteById(id);
    }

    public Funcionario atualizarFuncionario(Integer id, Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionario.setNome(funcionarioAtualizado.getNome());
            funcionario.setCargo(funcionarioAtualizado.getCargo());
            funcionario.setDataContratacao(funcionarioAtualizado.getDataContratacao());
            return funcionarioRepository.save(funcionario);
        }).orElseThrow(() -> new RuntimeException("Funcionario não encontrado com id " + id));
    }

    public List<FuncionarioSalarioDto> obterFuncionariosValorTotalPago(List<Integer> funcionariosId, int mes, int ano, Boolean somenteComBeneficios) {

        List<FuncionarioSalarioDto> valorTotalPorFuncionario = new ArrayList<FuncionarioSalarioDto>();
        List<Funcionario> funcionarioList = new ArrayList<Funcionario>();
        if (somenteComBeneficios) {
            funcionarioList = funcionarioRepository.findByIdEMesEAnoSomenteBeneficios(funcionariosId);
        } else {
            funcionarioList = funcionarioRepository.findAllById(funcionariosId);
        }

        for (Funcionario funcionarioIndex : funcionarioList) {
            LocalDate localDate = funcionarioIndex.getDataContratacao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (localDate.getMonthValue() >= mes && localDate.getYear() >= ano) {

                Double valorTotal = 0.0;

                Double valorDoSalario = calulaSalarioSemBeneficios(funcionarioIndex, ano);
                Double beneficioPorPorcentagem = funcionarioIndex.getCargo().getPorcentagemBeneficio() / 100;

                Double valorTotalPorVendas = 0.0;
                Double valorPorcentagemNaVenda = 0.0;
                Double valorBeneficioSalario = 0.0;

                // Verifica Beneficios
                if (funcionarioIndex.getCargo().getId().equals(TipoCargo.SECRETARIO.ordinal())) {
                    valorTotal = (valorDoSalario * beneficioPorPorcentagem) + valorDoSalario;
                } else if (funcionarioIndex.getCargo().getId().equals(TipoCargo.VENDENDOR.ordinal() + 1)) {
                    List<Venda> vendaList = vendaRepository.findByIdEMesEAnoComVendas(funcionarioIndex.getId(), mes, ano);

                    for (Venda vendaIndex : vendaList) {
                        valorPorcentagemNaVenda = vendaIndex.getValorVenda() * beneficioPorPorcentagem;
                        valorTotalPorVendas += valorPorcentagemNaVenda;
                        System.out.println("==Pessoa==\n" + funcionarioIndex.getNome() + " Valor da venda: " + vendaIndex.getValorVenda() + "\n valor com porcentagem: " + valorPorcentagemNaVenda + "\n valor total de vendas" + valorTotalPorVendas);
                    }
                }
                valorTotal = valorTotalPorVendas + (valorDoSalario * beneficioPorPorcentagem) + valorDoSalario;
                valorTotalPorFuncionario.add(new FuncionarioSalarioDto(
                        funcionarioIndex.getId(), funcionarioIndex.getNome(),
                        somenteComBeneficios ? (valorDoSalario * beneficioPorPorcentagem) : valorTotal,
                        0.0, valorTotalPorVendas == 0.0 ? valorBeneficioSalario : valorTotalPorVendas));
            }
        }
        return valorTotalPorFuncionario;
    }

    public List<FuncionarioSalarioDto> obterFuncionariosSalarioMensalPagoSemBeneficio(List<Integer> funcionariosId, int mes, int ano) {

        List<FuncionarioSalarioDto> valorTotalPorFuncionario = new ArrayList<FuncionarioSalarioDto>();
        List<Funcionario> funcionarioList = funcionarioRepository.findAllById(funcionariosId);

        for (Funcionario funcionarioIndex : funcionarioList) {
            LocalDate localDate = funcionarioIndex.getDataContratacao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (localDate.getMonthValue() >= mes && localDate.getYear() >= ano) {

                Double salarioSemPorcentagem = calulaSalarioSemBeneficios(funcionarioIndex, ano);

                System.out.println(salarioSemPorcentagem);
                valorTotalPorFuncionario.add(new FuncionarioSalarioDto(funcionarioIndex.getId(), funcionarioIndex.getNome(), salarioSemPorcentagem, 0.0, 0.0));

            }
        }

        return valorTotalPorFuncionario;
    }

    private Double calulaSalarioSemBeneficios(Funcionario funcionarioIndex, Integer ano) {
        LocalDate localDate = funcionarioIndex.getDataContratacao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // Calcula o salário base
        Double salarioBase = funcionarioIndex.getCargo().getSalarioBase();
        Double beneficioPorAnoServico = 0.0;
        // Calcula o benefício fixo
        if ((ano - localDate.getYear()) > 0) {
            beneficioPorAnoServico = funcionarioIndex.getCargo().getSalarioAnoServico() * (ano - localDate.getYear());
        }

        // Calcula o benefício por ano de serviço
        Double beneficioPorPorcentagem = funcionarioIndex.getCargo().getPorcentagemBeneficio() / 100;
        return salarioBase + beneficioPorAnoServico;

    }

    public FuncionarioSalarioDto obterFuncionarioComMaiorSalarioNoMes(List<Integer> funcionariosId, int mes, int ano) {
        List<FuncionarioSalarioDto> funcionarioSalarioList = obterFuncionariosValorTotalPago(funcionariosId, mes, ano, false);
        FuncionarioSalarioDto funcionarioSalarioMaior = funcionarioSalarioList.get(0);
        for (FuncionarioSalarioDto funcionarioSalarioIndex : funcionarioSalarioList) {
            if (funcionarioSalarioIndex.valorTotalSalario() > funcionarioSalarioMaior.valorTotalSalario()) {
                funcionarioSalarioMaior = funcionarioSalarioIndex;
            }
        }
        return funcionarioSalarioMaior;
    }

    public FuncionarioSalarioDto obterFuncionarioComMaiorBeneficioNoMes(List<Integer> funcionariosId, int mes, int ano) {
        List<FuncionarioSalarioDto> funcionarioSalarioList = obterFuncionariosValorTotalPago(funcionariosId, mes, ano, true);
        FuncionarioSalarioDto funcionarioBeneficioMaior = funcionarioSalarioList.get(0);
        for (FuncionarioSalarioDto funcionarioSalarioIndex : funcionarioSalarioList) {
            if (funcionarioSalarioIndex.beneficioNoMes() > funcionarioBeneficioMaior.beneficioNoMes()) {
                funcionarioBeneficioMaior = funcionarioSalarioIndex;
            }
        }
        return funcionarioBeneficioMaior;
    }

    public VendaDto obterVendedorMaisVendido(List<Integer> vendedoresId, int mes, int ano) {
        Funcionario vendedorMaisVendas = new Funcionario();
        List<Object[]> vendaList = (List<Object[]>) vendaRepository.somarValorVendasPorVendedorEMesEAno(vendedoresId, mes, ano);
        vendedorMaisVendas = (Funcionario) vendaList.get(0)[0];
        Double valorVendasVendedor = (Double) vendaList.get(0)[1];
        Double maiorValorVendas = 0.0;

        for (Object[] vendedor : vendaList) {
            if (valorVendasVendedor <= (Double) vendedor[1]) {
                vendedorMaisVendas = (Funcionario) vendedor[0];
                maiorValorVendas = (Double) vendedor[1];
            }
        }

        return new VendaDto(vendedorMaisVendas.getId(), vendedorMaisVendas.getNome(), valorVendasVendedor, mes, ano);
    }

}
