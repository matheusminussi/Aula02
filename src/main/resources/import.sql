/*cria o produto */
insert into produto (descricao,valor)values ('Camisa', 10.00);
insert into produto (descricao,valor)values ('Relogio', 20.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);

/*TESTES DE PRODUTOS PARA AUMENTAR PAGINA */

insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);
insert into produto (descricao,valor)values ('Maionese', 9.00);



/*Pessoa fisica*/
insert into pessoa (nome)values ('Matheus');
insert into pessoafisica (id_pessoa,cpf)values (1,'11111111111');

insert into pessoa (nome)values ('root');
insert into pessoafisica (id_pessoa,cpf)values (2,'00000000000');

insert into pessoa (nome)values ('Felipe');
insert into pessoafisica (id_pessoa,cpf)values (3,'22222222222');

insert into pessoa (nome)values ('Matheus');
insert into pessoafisica (id_pessoa,cpf)values (4,'66666666666');


/*Pessoa juridica*/
insert into pessoa (nome)values ('Djoko');
insert into pessoajuridica (id_pessoa,cnpj)values (5,'123123000110');

insert into pessoa (nome)values ('Yetz');
insert into pessoajuridica (id_pessoa,cnpj)values (6,'321321000111');



/*cria as vendas*/
insert into venda (data,pessoa_id)values ('2024-04-12',1);
insert into venda (data,pessoa_id)values ('2024-04-11',4);
insert into venda (data,pessoa_id)values ('2024-04-10',3);
insert into venda (data,pessoa_id)values ('2024-04-10',3);


/*insert de vendas*/
insert into item_venda (quantidade,produto_id,venda_id)values (2,1,1);
insert into item_venda (quantidade,produto_id,venda_id)values (1,2,2);
insert into item_venda (quantidade,produto_id,venda_id)values (2,3,3);
insert into item_venda (quantidade,produto_id,venda_id)values (3,3,3);
