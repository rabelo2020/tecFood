CREATE TABLE pedido(
 id BIGINT NOT NULL AUTO_INCREMENT,
 sub_total DECIMAL(10, 2) NOT NULL,
 taxa_frete DECIMAL(10, 2) NOT NULL,
 valor_total DECIMAL(10, 2) NOT NULL,
 data_criacao DATETIME NOT NULL,
 data_confirmacao DATETIME,
 data_cancelamento DATETIME,
 data_entrega DATETIME,
 
 endereco_cep VARCHAR(10),
 endereco_logradouro VARCHAR(60),
 endereco_numero VARCHAR(20),
 endereco_complemento VARCHAR(255),
 endereco_bairro VARCHAR(60),
 
  
 restaurante_id BIGINT NOT NULL,
 forma_pagamento_id BIGINT NOT NULL,
 usuario_cliente_id BIGINT NOT NULL,
 endereco_cidade_id BIGINT NOT NULL, 
 
PRIMARY KEY (id),

 CONSTRAINT fk_pedido_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante (id),
 CONSTRAINT fk_pedido_forma_pagamento FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento (id),
 CONSTRAINT fk_pedido_usuario FOREIGN KEY (usuario_cliente_id) REFERENCES usuario (id),
 CONSTRAINT fj_pedido_cidade FOREIGN KEY (endereco_cidade_id) REFERENCES cidade (id)
 
)ENGINE=INNODB DEFAULT CHARSET=utf8;
  
  CREATE TABLE item_pedido(
  id BIGINT NOT NULL AUTO_INCREMENT,
  quantidade SMALLINT(6) NOT NULL,
  preco_unitario DECIMAL(10, 2) NOT NULL,
  preco_total DECIMAL(10, 2) NOT NULL,
  observacao VARCHAR(255) NULL,
  
  pedido_id BIGINT NOT NULL,
  produto_id BIGINT NOT NULL,
  
  PRIMARY KEY(id) ,
  UNIQUE KEY uk_item_pedido_produto (pedido_id, produto_id),  
  
  CONSTRAINT fk_itemPedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (id),
  CONSTRAINT fk_itemPedido_produto FOREIGN KEY (produto_id) REFERENCES produto (id)
  )ENGINE=INNODB DEFAULT CHARSET=utf8;
  