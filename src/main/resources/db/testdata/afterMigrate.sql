set foreign_key_checks= 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks= 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

insert into Cozinha(nome) values ("Japonesa");
insert into Cozinha(nome) values ("Brasileira");
insert into Cozinha(nome) values ("Americana");
insert into Cozinha(nome) values ("Indiana");
insert into Cozinha(nome) values ("Argentina");
insert into Cozinha(nome) values ("Cearense");

insert  into Estado(nome) values ("Maranhão");
insert  into Estado(nome) values ("Rio de Janeiro");
insert  into Estado(nome) values ("Amazonas");
insert  into Estado(nome) values ("São Paulo");
insert  into Estado(nome) values ("Ceara");
insert  into Estado(nome) values ("Rio Grande do Sul");
insert  into Estado(nome) values ("Goias");
insert  into Estado(nome) values ("Bahia");
insert  into Estado(nome) values ("Pará");
insert  into Estado(nome) values ("Mato Grosso");

insert into forma_pagamento(nome) values("Cartão Debito");
insert into forma_pagamento(nome) values("Cartão de Credito");
insert into forma_pagamento(nome) values("Dinheiro");

insert into Cidade(nome, estado_id) values("Manaus", 3);
insert into Cidade(nome, estado_id) values("Goiania", 7);
insert into Cidade(nome, estado_id) values("Rio de Janeiro", 2);
insert into Cidade(nome, estado_id) values("São Luis", 1);
insert into Cidade(nome, estado_id) values("Santo André", 4); 
insert into Cidade(nome, estado_id) values("Porto Alegre", 6);
insert into Cidade(nome, estado_id) values("Fortaleza", 5);
insert into Cidade(nome, estado_id) values("São Gonçalo", 2);
insert into Cidade(nome, estado_id) values("Cedral", 1);
insert into Cidade(nome, estado_id) values("Campinas", 4);
insert into Cidade(nome, estado_id) values("Teresopolis", 2);
insert into Cidade(nome, estado_id) values("São Paulo", 4);
insert into Cidade(nome, estado_id) values("Cuiabá", 10);


insert into Restaurante(nome, taxa_frete, cozinha_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, data_cadastro, data_atualizacao) values ("Comida Goiana", 2.52, 2, "Campo Grande", "23073-500", "Dois Andares", "Rua Cristalandia", "35", 3, utc_timestamp, utc_timestamp);
insert into Restaurante(nome, taxa_frete, cozinha_id, endereco_bairro, endereco_cep, endereco_logradouro, endereco_numero, endereco_cidade_id, data_cadastro, data_atualizacao) values ("Restaurate Indiana", 4.82, 4, "São Bernado", "62892-827", "Rua Das Flores", "629", 12, utc_timestamp, utc_timestamp);
insert into Restaurante(nome, taxa_frete, cozinha_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, data_cadastro, data_atualizacao) values ("Restaurante do Japa", 13.87, 1, "Centro", "86301-380", "Fachada Verde", "Estrada chique", "76", 6, utc_timestamp, utc_timestamp);
insert into Restaurante(nome, taxa_frete, cozinha_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, data_cadastro, data_atualizacao) values ("Restaurante La Plata", 36.98, 5, "São Francisco", "83602-983", "Bandeira da Argentina", "Rua Altos", "7", 4, utc_timestamp, utc_timestamp);
insert into Restaurante(nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ("Restaurate Baiahna", 15.03, 2, utc_timestamp, utc_timestamp);
insert into Restaurante(nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ("Restaurate New York", 17.92, 3, utc_timestamp, utc_timestamp);
insert into Restaurante(nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ("Restaurate Carioca ", 2.98, 2, utc_timestamp, utc_timestamp);

insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);


insert into Restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(1, 3), (1, 1), (4, 2), (2, 1), (2, 3), (3, 1), (3, 2), (3, 3);
insert into Restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(4, 3), (5, 1), (5, 2), (5, 3), (6, 3), (6, 1), (7, 1), (7, 3);



