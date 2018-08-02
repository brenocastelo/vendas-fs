insert into cliente (id, nome) values (1, 'Eletrônicos Eletro');
insert into cliente (id, nome) values (2, 'Smart Compras');

insert into produto (id, nome, valor) values (1, 'Notebook', 2899.90);
insert into produto (id, nome, valor) values (2, 'Smartphone', 1789.90);
insert into produto (id, nome, valor) values (3, 'Tablet', 949.90);

insert into venda (id, cliente_id, valor_frete, valor_total, data_venda) values  (1, 1, 12.0, 2966.0, sysdate());

insert into item (id, venda_id, produto_id, quantidade) values (1, 1, 1, 1);