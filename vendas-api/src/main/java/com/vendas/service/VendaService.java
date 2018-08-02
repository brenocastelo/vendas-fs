package com.vendas.service;

import com.vendas.model.Venda;
import com.vendas.repository.ProdutosRepository;
import com.vendas.repository.VendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class VendaService {

    @Autowired
    private VendasRepository vendasRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    public Venda adicionar(Venda venda) {
        venda.setDataVenda(LocalDateTime.now());
        venda.getItens().forEach(i -> {
            i.setVenda(venda);
            i.setProduto(produtosRepository.findById(i.getProduto().getId()).get());
        });

        BigDecimal totalItens = venda.getItens().stream()
                .map(i -> i.getProduto().getValor().multiply(new BigDecimal(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        venda.setValorTotal(totalItens.add(venda.getValorFrete()));

        return vendasRepository.save(venda);
    }
}
