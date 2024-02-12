package com.sinerji.api.desafio.service;

import com.sinerji.api.desafio.domain.venda.Venda;
import com.sinerji.api.desafio.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public Venda salvarVenda(Venda venda) {
        return vendaRepository.save(venda);
    }

    public List<Venda> buscarTodasVendas() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> buscarVendaPorId(Integer id) {
        return vendaRepository.findById(id);
    }

    public void deletarVenda(Integer id) {
        vendaRepository.deleteById(id);
    }

    public Venda atualizarVenda(Integer id, Venda vendaAtualizada) {
        return vendaRepository.findById(id).map(venda -> {
            venda.setFuncionario(vendaAtualizada.getFuncionario());
            venda.setMes(vendaAtualizada.getMes());
            venda.setAno(vendaAtualizada.getAno());
            venda.setValorVenda(vendaAtualizada.getValorVenda());
            return vendaRepository.save(venda);
        }).orElseThrow(() -> new RuntimeException("Venda não encontrada com id " + id));
    }

}
