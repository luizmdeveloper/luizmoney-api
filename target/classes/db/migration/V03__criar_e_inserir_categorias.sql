CREATE TABLE lancamentos(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(60) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria BIGINT(20) NOT NULL,
	codigo_pessoa BIGINT(20) NOT NULL,
	observacao VARCHAR(100),
	FOREIGN KEY (codigo_categoria) REFERENCES categorias(codigo),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoas(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamentos (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES('Salário mensal', '2017-08-21', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
INSERT INTO lancamentos (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA', 2, 2);