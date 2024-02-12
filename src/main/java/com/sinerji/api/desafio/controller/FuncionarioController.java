package com.sinerji.api.desafio.controller;

import com.sinerji.api.desafio.domain.funcionario.FuncionarioSalarioDto;
import com.sinerji.api.desafio.domain.venda.VendaDto;
import com.sinerji.api.desafio.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("funcionarios")
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/valor-total-pago")
    public ResponseEntity<List<FuncionarioSalarioDto>> obterValorTotalPago(@RequestParam List<Integer> ids, @RequestParam Integer mes, @RequestParam Integer ano) {
        return ResponseEntity.ok().body(funcionarioService.obterFuncionariosValorTotalPago(ids, mes, ano, false));
    }

    @GetMapping("/valor-total-pago-sem-beneficio")
    public ResponseEntity<List<FuncionarioSalarioDto>> obterValorTotalPagoSemBeneficio(@RequestParam List<Integer> ids, @RequestParam Integer mes, @RequestParam Integer ano) {
        return ResponseEntity.ok().body(funcionarioService.obterFuncionariosSalarioMensalPagoSemBeneficio(ids, mes, ano));
    }

    @GetMapping("/valor-total-pago-somente-beneficio")
    public ResponseEntity<List<FuncionarioSalarioDto>> obterFuncionariosValorSalarioSomenteBeneficios(@RequestParam List<Integer> ids, @RequestParam Integer mes, @RequestParam Integer ano) {
        return ResponseEntity.ok().body(funcionarioService.obterFuncionariosValorTotalPago(ids, mes, ano, true));
    }

    @GetMapping("/funcionario-maior-salario")
    public ResponseEntity<FuncionarioSalarioDto> obterFuncionarioMaiorSalarioNoMesAno(@RequestParam List<Integer> ids, @RequestParam Integer mes, @RequestParam Integer ano) {
        return ResponseEntity.ok().body(funcionarioService.obterFuncionarioComMaiorSalarioNoMes(ids, mes, ano));
    }

    @GetMapping("/funcionario-maior-beneficio")
    public ResponseEntity<FuncionarioSalarioDto> obterFuncionarioMaiorBeneficioNoMes(@RequestParam List<Integer> ids, @RequestParam Integer mes, @RequestParam Integer ano) {
        return ResponseEntity.ok().body(funcionarioService.obterFuncionarioComMaiorBeneficioNoMes(ids, mes, ano));
    }


    @GetMapping("/funcionario-maior-venda")
    public ResponseEntity<VendaDto> obterFuncionarioMaiorVendaNoMes(@RequestParam List<Integer> ids, @RequestParam Integer mes, @RequestParam Integer ano) {
        return ResponseEntity.ok().body(funcionarioService.obterVendedorMaisVendido(ids, mes, ano));
    }

}
