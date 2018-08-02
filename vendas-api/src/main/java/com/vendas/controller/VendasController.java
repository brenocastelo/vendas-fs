package com.vendas.controller;

import com.vendas.model.Venda;
import com.vendas.repository.VendasRepository;
import com.vendas.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendasRepository vendasRepository;

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<Venda> listar() {
        return vendasRepository.findAll();
    }

    @PostMapping
    public Venda adicionar(@RequestBody @Valid Venda venda) {
        return vendaService.adicionar(venda);
    }
}
