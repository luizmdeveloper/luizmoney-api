CREATE TABLE pessoas(
	codigo BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(60) NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(60),
	bairro VARCHAR(45),
	cidade VARCHAR(60),
	uf VARCHAR(2),
	numero VARCHAR(10),
	cep VARCHAR(8),
	complemento VARCHAR(60)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoas (nome, ativo, logradouro, bairro, cidade, uf, numero, cep, complemento) VALUES('Luiz Mário', true, 'Avenida souza filho', 'centro', 'Petrolina', 'PE', '151 B', '56304360', null);

INSERT INTO pessoas (nome, ativo, logradouro, bairro, cidade, uf, numero, cep, complemento) VALUES('Felié', false, 'Rua 22', 'cohab', 'Petrolina', 'PE', '192', '56360304', null);
