/*cria o produto */

INSERT INTO produto (descricao, valor) VALUES ('mouse optico', 49.90);
INSERT INTO produto (descricao, valor) VALUES ('mouse gamer', 199.90);
INSERT INTO produto (descricao, valor) VALUES ('mouse sem fio', 79.90);

INSERT INTO produto (descricao, valor) VALUES ('teclado mecanico', 299.90);
INSERT INTO produto (descricao, valor) VALUES ('teclado gamer', 399.90);
INSERT INTO produto (descricao, valor) VALUES ('teclado sem fio', 159.90);

INSERT INTO produto (descricao, valor) VALUES ('monitor led 24"', 899.00);
INSERT INTO produto (descricao, valor) VALUES ('monitor curvo 27"', 1499.00);
INSERT INTO produto (descricao, valor) VALUES ('monitor 21.5"', 699.00);

INSERT INTO produto (descricao, valor) VALUES ('fonte 500w', 259.90);

INSERT INTO produto (descricao, valor) VALUES ('mouse pad gamer', 49.90);
INSERT INTO produto (descricao, valor) VALUES ('mouse pad xxl', 79.90);
INSERT INTO produto (descricao, valor) VALUES ('mouse pad rgb', 99.90);

INSERT INTO produto (descricao, valor) VALUES ('suporte 1 monitor', 199.90);
INSERT INTO produto (descricao, valor) VALUES ('suporte 2 monitor', 89.90);

/*Pessoa fisica*/
insert into pessoa (nome)values ('Matheus');
insert into pessoafisica (id_pessoa,cpf)values (1,'11111111111');

insert into pessoa (nome)values ('root');
insert into pessoafisica (id_pessoa,cpf)values (2,'458468456488');

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



/*Usuario e roles*/


insert into Role (nome)values ('ROLE_USER');
insert into Role (nome)values ('ROLE_ADMIN');


insert into Usuario (login, password)values ('user', '$2a$10$oA6m2EUOluJNrqJEGMYRseH1CsvX4lOTywubmmZ15lGxxOii6Gp4S');
insert into Usuario (login, password)values ('admin', '$2a$10$4p2H2mA2LwPYKqJgJhftfuz2GJwTBWUgfyEzop7ikv9H25mNtmrOi');


insert into usuario_roles (roles_id, usuarios_id)values (1, 1);
insert into usuario_roles (roles_id, usuarios_id)values (2, 2);