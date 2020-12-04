insert into Cozinha(nome) values ("Japonesa");
insert into Cozinha(nome) values ("Brasileira");
insert into Cozinha(nome) values ("Americana");
insert into Cozinha(nome) values ("Indiana");
insert into Cozinha(nome) values ("Argentina");

insert  into Estado(nome) values ("Maranhão");
insert  into Estado(nome) values ("Rio de Janeiro");
insert  into Estado(nome) values ("Amazonas");
insert  into Estado(nome) values ("São Paulo");
insert  into Estado(nome) values ("Ceara");
insert  into Estado(nome) values ("Rio Grande do Sul");
insert  into Estado(nome) values ("Goias");

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


insert into Restaurante(nome, taxa_frete, cozinha_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id) values ("Comida Goiana", 2.52, 2, "Campo Grande", "23073-500", "Dois Andares", "Rua Cristalandia", "35", 3);

insert into Restaurante(nome, taxa_frete, cozinha_id, endereco_bairro, endereco_cep, endereco_logradouro, endereco_numero, endereco_cidade_id) values ("Restaurate Indiana", 4.82, 4, "São Bernado", "62892-827", "Rua Das Flores", "629", 12);
insert into Restaurante(nome, taxa_frete, cozinha_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id) values ("Restaurante do Japa", 13.87, 1, "Centro", "86301-380", "Fachada Verde", "Estrada chique", "76", 6);
insert into Restaurante(nome, taxa_frete, cozinha_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id) values ("Restaurante La Plata", 36.98, 5, "São Francisco", "83602-983", "Bandeira da Argentina", "Rua Altos", "7", 4);

insert into Restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(1, 3), (1, 1), (4, 2), (2, 1), (2, 3), (3, 1), (3, 2), (3, 3);

