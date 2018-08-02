import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { VendasService } from '../vendas/vendas.service';
import { FormGroup } from '@angular/forms';
import { MessageService } from 'primeng/components/common/messageservice';

@Component({
  selector: 'app-vendas-cadastro',
  templateUrl: './vendas-cadastro.component.html',
  styleUrls: ['./vendas-cadastro.component.css']
})
export class VendasCadastroComponent implements OnInit {

  venda: any;
  item: any;
  clientes: Array<any>;
  produtos: Array<any>;
  @Output() vendaSalva = new EventEmitter();

  constructor(private vendaService: VendasService,
    private messageService: MessageService) { }

  ngOnInit() {
    this.vendaService.listarClientes()
      .subscribe(response => this.clientes = response);

    this.vendaService.listarProdutos()
      .subscribe(response => this.produtos = response);

    this.novaVenda();
  }

  novaVenda() {
    this.venda = {itens: [], valorFrete: 0.0, valorTotal: 0.0};
    this.item = {};
  }

  incluirItem() {
    this.item.total = this.item.produto.valor * this.item.quantidade;

    this.venda.itens.push(this.item);
    this.item = {};

    this.calcularTotal();
  }

  calcularTotal() {
    const totalItens = this.venda.itens
      .map(i => (i.produto.valor * i.quantidade))
      .reduce((total, v) => total + v, 0);

      this.venda.valorTotal = totalItens + this.venda.valorFrete;
  }

  adicionar(frm: FormGroup) {
    this.vendaService.adicionar(this.venda).subscribe(response => {
      frm.reset();
      this.novaVenda();
      this.messageService.add({ severity: 'success', detail: 'Venda realizada com sucesso!' });
      this.vendaSalva.emit(response);
    });
  }

}
